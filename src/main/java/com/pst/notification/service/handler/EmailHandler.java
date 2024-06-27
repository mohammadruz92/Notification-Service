package com.pst.notification.service.handler;

import com.pst.notification.service.domain.model.EmailNotificationPayload;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.domain.model.history.EmailHistoryRequest;
import com.pst.notification.service.framework.NotificationHandler;
import com.pst.notification.service.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
;

@Component
public class EmailHandler extends NotificationHandler {

  @Autowired
  public EmailHandler(EmailNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected BaseNotificationHistoryRequest buildHistoryRequest(
          String messageId, NotificationPayload payload, NotificationHandlerResponse response) {
    EmailNotificationPayload emailNotificationPayload = (EmailNotificationPayload) payload;
    EmailHistoryRequest request =
        super.buildWithBaseRequest(messageId, payload, response, EmailHistoryRequest.class);
    request.setSubject(emailNotificationPayload.getSubject());
    return request;
  }
}
