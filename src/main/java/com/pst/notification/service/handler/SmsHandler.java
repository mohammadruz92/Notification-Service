package com.pst.notification.service.handler;

import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.history.SmsHistoryRequest;
import com.pst.notification.service.framework.NotificationHandler;
import com.pst.notification.service.service.SmsNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SmsHandler extends NotificationHandler {

  @Autowired
  public SmsHandler(SmsNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected SmsHistoryRequest buildHistoryRequest(String messageId,
                                                  NotificationPayload payload, NotificationHandlerResponse response) {
    SmsHistoryRequest request = super.buildWithBaseRequest(messageId, payload, response,
        SmsHistoryRequest.class);
    request.setDestinations(payload.getDestinations());
    return request;
  }

}
