package com.pst.notification.service.service;

import java.util.Objects;

import com.pst.notification.service.connector.WhatsappHttpConnector;
import com.pst.notification.service.domain.entity.WhatsappHistory;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.WhatsappNotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.domain.model.history.WhatsappHistoryRequest;
import com.pst.notification.service.domain.model.thirdparty.WhatsappText;
import com.pst.notification.service.domain.model.thirdparty.WhatsappTextMessageRequest;
import com.pst.notification.service.domain.model.thirdparty.response.WhatsappMessageResponse;
import com.pst.notification.service.exception.NotificationException;
import com.pst.notification.service.framework.INotificationService;
import com.pst.notification.service.repository.WhatsappHistoryRepository;
import com.pst.notification.service.utility.CommonConstants;
import com.pst.notification.service.utility.NotificationHistoryUtility;
import com.pst.notification.service.utility.ObjectMapperUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;


@Service
@Slf4j
public class WhatsappNotificationService implements INotificationService {

  @Autowired
  WhatsappHttpConnector connector;

  @Autowired
  WhatsappHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload payload, NotificationHandlerResponse response) {
    try {
      ResponseEntity<WhatsappMessageResponse> responseEntity = connector.sendTextMessage(
          buildWhatsappTextMessageRequest(payload));
      response.addThirdPartyResponse(responseEntity.getBody());
    } catch (
        HttpStatusCodeException e) {
      response.addThirdPartyResponse(
          ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
              .body(e.getResponseBodyAsString()));
      throw new NotificationException(
          String.format("WhatsappHttpConnector failed for request %s", payload));
    }
  }

  @Override
  public void createHistory(BaseNotificationHistoryRequest request) {
    WhatsappHistoryRequest whatsappHistoryRequest = (WhatsappHistoryRequest) request;
    WhatsappHistory whatsappHistory = NotificationHistoryUtility.buildWhatsappHistory(
        whatsappHistoryRequest);
   // repository.save(whatsappHistory);
  }

  @Override
  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    WhatsappHistory whatsappHistory = getWhatsappHistory(messageId);
   // repository.save(
    //    Objects.requireNonNull(ObjectMapperUtility.updateObject(request, whatsappHistory)));
  }

  public WhatsappHistory getWhatsappHistory(String messageId) {
    return repository.findByMessageId(messageId).orElseThrow(
        () -> new NotificationException(CommonConstants.ErrorMessage.NOT_FOUND.getMessage(),
            HttpStatus.NOT_FOUND));
  }

  private WhatsappTextMessageRequest buildWhatsappTextMessageRequest(NotificationPayload payload) {
    WhatsappNotificationPayload whatsappPayload = (WhatsappNotificationPayload) payload;
    WhatsappTextMessageRequest request = new WhatsappTextMessageRequest();
    request.setTo(whatsappPayload.getDestination());
    WhatsappText whatsappText = new WhatsappText();
    whatsappText.setPreviewUrl(false);
    whatsappText.setBody(payload.getMessage());
    request.setText(whatsappText);
    return request;
  }


}
