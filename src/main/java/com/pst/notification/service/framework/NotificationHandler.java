package com.pst.notification.service.framework;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.exception.NotificationException;
import com.pst.notification.service.utility.CommonConstants;
import com.pst.notification.service.utility.ObjectMapperUtility;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class NotificationHandler {

  INotificationService notificationService;

  protected NotificationHandler(INotificationService notificationService) {
    this.notificationService = notificationService;
  }

  protected abstract BaseNotificationHistoryRequest buildHistoryRequest(String messageId,
                                                                        NotificationPayload payload,
                                                                        NotificationHandlerResponse response);

  public NotificationHandlerResponse dispatch(NotificationPayload payload)
      throws NotificationException {
    log.info("[{}] Starting dispatch for request: {}", this.getClass().getSimpleName(), payload);
    String messageId = UUID.randomUUID().toString();
    NotificationHandlerResponse response = buildPendingResponse(messageId);
    try {
      validate(payload);
      createHistory(buildHistoryRequest(response.getMessageId(), payload, response));
      CompletableFuture.runAsync(() -> {
        NotificationHandlerResponse processResponse = process(payload);
        updateHistory(buildHistoryRequest(response.getMessageId(), payload, processResponse));
      });
      return response;
    } catch (NotificationException e) {
      response.setStatus(CommonConstants.Status.VALIDATION_FAILED);
      createHistory(buildHistoryRequest(response.getMessageId(), payload, response));
      log.error("[{}] Dispatch failed due to invalid payload: {} with error: {}",
          this.getClass().getSimpleName(), payload,
          e.getMessage());
    } catch (Exception e) {
      response.setStatus(CommonConstants.Status.FAILED);
      log.error("[{}] Dispatch failed for payload: {} with error: {}",
          this.getClass().getSimpleName(), payload, e.getMessage());
    }
    return response;
  }


  protected void validate(NotificationPayload payload) throws NotificationException {
    // TODO: Add any validations via notificationService
  }


  protected void createHistory(BaseNotificationHistoryRequest request) {
    notificationService.createHistory(request);
  }


  protected void updateHistory(BaseNotificationHistoryRequest request) {
    notificationService.updateHistory(request);
  }

  protected NotificationHandlerResponse process(NotificationPayload payload) {
    return notificationService.notify(payload);
  }

  protected <T> T buildWithBaseRequest(String messageId,
      NotificationPayload payload, NotificationHandlerResponse response, Class<T> clazz) {
    BaseNotificationHistoryRequest baseRequest = new BaseNotificationHistoryRequest();
    baseRequest.setMessageId(messageId);
    baseRequest.setContent(payload.getMessage());
    Optional.ofNullable(payload.getTemplatePayload()).ifPresent(templatePayload -> {
      baseRequest.setTemplateName(templatePayload.getTemplateName());
      baseRequest.setTemplateData(
          ObjectMapperUtility.writeValueAsString(templatePayload.getData()));
    });
    baseRequest.setAppId(payload.getAppId());
    baseRequest.setNotificationRequest(ObjectMapperUtility.writeValueAsString(payload));
    baseRequest.setStatus(response.getStatus());
    baseRequest.setErrorMessage(response.getErrorMessage());
    baseRequest.setThirdPartyResponse(response.getThirdPartyResponse());
    return ObjectMapperUtility.convertValue(baseRequest, clazz);
  }

  private NotificationHandlerResponse buildPendingResponse(String messageId) {
    NotificationHandlerResponse response = new NotificationHandlerResponse();
    response.setMessageId(messageId);
    response.setStatus(CommonConstants.Status.PENDING);
    return response;
  }

}
