package com.pst.notification.service.service;

import java.util.Objects;

import com.pst.notification.service.domain.entity.SmsHistory;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.domain.model.history.SmsHistoryRequest;
import com.pst.notification.service.exception.NotificationException;
import com.pst.notification.service.framework.INotificationService;
import com.pst.notification.service.repository.SmsHistoryRepository;
import com.pst.notification.service.service.pinpoint.SmsPinpointNotification;
import com.pst.notification.service.utility.CommonConstants;
import com.pst.notification.service.utility.NotificationHistoryUtility;
import com.pst.notification.service.utility.ObjectMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class SmsNotificationService implements INotificationService {

  @Autowired
  SmsPinpointNotification pinpointNotification;

  @Autowired
  SmsHistoryRepository repository;

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response) {
    pinpointNotification.sendNotification(request, response);
  }

  public void createHistory(BaseNotificationHistoryRequest request) {
    SmsHistoryRequest smsHistoryRequest = (SmsHistoryRequest) request;
    SmsHistory smsHistory = NotificationHistoryUtility.buildSmsHistory(smsHistoryRequest);
    //repository.save(smsHistory);
  }

  public void updateHistory(BaseNotificationHistoryRequest request) {
    String messageId = request.getMessageId();
    SmsHistory smsHistory = getSmsHistory(messageId);
   // repository.save(Objects.requireNonNull(ObjectMapperUtility.updateObject(request, smsHistory)));
  }

  public SmsHistory getSmsHistory(String messageId) {
    return repository.findByMessageId(messageId).orElseThrow(
        () -> new NotificationException(CommonConstants.ErrorMessage.NOT_FOUND.getMessage(),
            HttpStatus.NOT_FOUND));
  }

}
