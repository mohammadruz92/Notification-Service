package com.pst.notification.service.framework;


import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.utility.ApplicationContextUtility;
import com.pst.notification.service.utility.ObjectMapperUtility;

public class NotificationHandlerFactory {

  public static NotificationHandler getHandler(NotificationPayload request) {
    return ApplicationContextUtility.getBean(request.getNotificationType().getHandlerClass());
  }

  public static NotificationPayload getPayload(String payload, NotificationType notificationType) {
    return ObjectMapperUtility.readValue(payload, notificationType.getPayloadClass());
  }

  NotificationHandlerFactory() {

  }

}
