package com.pst.notification.service.handler;

import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.WhatsappNotificationPayload;
import com.pst.notification.service.domain.model.history.BaseNotificationHistoryRequest;
import com.pst.notification.service.domain.model.history.WhatsappHistoryRequest;
import com.pst.notification.service.framework.NotificationHandler;
import com.pst.notification.service.service.WhatsappNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WhatsappHandler extends NotificationHandler {

  @Autowired
  public WhatsappHandler(WhatsappNotificationService notificationService) {
    super(notificationService);
  }

  @Override
  protected BaseNotificationHistoryRequest buildHistoryRequest(String messageId,
                                                               NotificationPayload payload, NotificationHandlerResponse response) {
    WhatsappNotificationPayload whatsappNotificationPayload = (WhatsappNotificationPayload) payload;
    WhatsappHistoryRequest request = super.buildWithBaseRequest(messageId, payload, response,
        WhatsappHistoryRequest.class);
    request.setDestination(whatsappNotificationPayload.getDestination());
    return request;
  }

}