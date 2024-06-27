package com.pst.notification.service.service;

import java.util.Objects;
import java.util.Optional;

import com.pst.notification.service.domain.entity.EmailHistory;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.domain.model.history.EmailHistoryRequest;
import com.pst.notification.service.exception.NotificationException;
import com.pst.notification.service.framework.INotificationService;
import com.pst.notification.service.repository.EmailHistoryRepository;
import com.pst.notification.service.service.pinpoint.EmailPinpointNotification;
import com.pst.notification.service.utility.CommonConstants;
import com.pst.notification.service.utility.NotificationHistoryUtility;
import com.pst.notification.service.utility.ObjectMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements INotificationService {

  @Autowired NotificationTemplateEngine templateEngine;

  @Autowired EmailPinpointNotification pinpointNotification;

  @Autowired EmailHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response) {
    Optional.ofNullable(request.getTemplatePayload())
        .ifPresent(
            templatePayload -> {
              String content =
                  templateEngine.process(
                      templatePayload.getTemplateName(),
                      request.getNotificationType().getTemplateDirectory(),
                      templatePayload.getData());
              request.setMessage(content);
            });
    pinpointNotification.sendNotification(request, response);
  }

  public void createHistory(BaseNotificationHistoryRequest request) {
    EmailHistoryRequest emailHistoryRequest = (EmailHistoryRequest) request;
    EmailHistory emailHistory = NotificationHistoryUtility.buildEmailHistory(emailHistoryRequest);
   // repository.save(emailHistory);
  }

  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    EmailHistory emailHistory = getEmailHistory(messageId);
   // repository.save(
    //    Objects.requireNonNull(ObjectMapperUtility.updateObject(request, emailHistory)));
  }

  public EmailHistory getEmailHistory(String messageId) {
    return repository
        .findByMessageId(messageId)
        .orElseThrow(
            () ->
                new NotificationException(
                    CommonConstants.ErrorMessage.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND));
  }
}
