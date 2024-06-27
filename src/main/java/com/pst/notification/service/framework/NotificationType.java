package com.pst.notification.service.framework;


import com.pst.notification.service.domain.model.EmailNotificationPayload;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.SmsNotificationPayload;
import com.pst.notification.service.domain.model.WhatsappNotificationPayload;
import com.pst.notification.service.handler.EmailHandler;
import com.pst.notification.service.handler.SmsHandler;
import com.pst.notification.service.handler.WhatsappHandler;

public enum NotificationType {

  SMS(SmsHandler.class, SmsNotificationPayload.class, "sms", "Notification"),
  EMAIL(EmailHandler.class, EmailNotificationPayload.class, "email", "Notification"),
  WHATSAPP(WhatsappHandler.class, WhatsappNotificationPayload.class, null, "Notification");

  private final Class<? extends NotificationHandler> handlerClass;
  private final Class<? extends NotificationPayload> payloadClass;
  private final String templateDirectory;
  private final String topicName;

  NotificationType(Class<? extends NotificationHandler> handlerClass,
      Class<? extends NotificationPayload> payloadClass, String templateDirectory,
      String topicName) {
    this.handlerClass = handlerClass;
    this.payloadClass = payloadClass;
    this.templateDirectory = templateDirectory;
    this.topicName = topicName;
  }

  public Class<? extends NotificationHandler> getHandlerClass() {
    return handlerClass;
  }

  public String getTopicName() {
    return topicName;
  }

  public String getTemplateDirectory() {
    return templateDirectory;
  }

  public Class<? extends NotificationPayload> getPayloadClass() {
    return payloadClass;
  }
}
