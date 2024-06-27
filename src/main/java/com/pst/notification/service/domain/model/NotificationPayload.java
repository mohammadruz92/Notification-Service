package com.pst.notification.service.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.pst.notification.service.framework.NotificationType;
import com.pst.notification.service.utility.CommonConstants;
import lombok.Data;


public class NotificationPayload {

  NotificationType notificationType;
  String appId;
  List<String> destinations = new ArrayList<>(); // For Whatsapp comm, destination will be used.
  String message;

  TemplatePayload templatePayload;

  CommonConstants.Language language = CommonConstants.Language.ENGLISH;

  public NotificationType getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(NotificationType notificationType) {
    this.notificationType = notificationType;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public List<String> getDestinations() {
    return destinations;
  }

  public void setDestinations(List<String> destinations) {
    this.destinations = destinations;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public TemplatePayload getTemplatePayload() {
    return templatePayload;
  }

  public void setTemplatePayload(TemplatePayload templatePayload) {
    this.templatePayload = templatePayload;
  }

  public CommonConstants.Language getLanguage() {
    return language;
  }

  public void setLanguage(CommonConstants.Language language) {
    this.language = language;
  }
}
