package com.pst.notification.service.domain.model.history;



import com.pst.notification.service.utility.CommonConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



public class BaseNotificationHistoryRequest {

  String appId;

  @NotBlank(message = "message id is mandatory.")
  String messageId;

  String content;
  String templateName;
  String templateData;

  String notificationRequest;
  CommonConstants.Status status;
  String errorMessage;
  String thirdPartyResponse;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public String getTemplateData() {
    return templateData;
  }

  public void setTemplateData(String templateData) {
    this.templateData = templateData;
  }

  public String getNotificationRequest() {
    return notificationRequest;
  }

  public void setNotificationRequest(String notificationRequest) {
    this.notificationRequest = notificationRequest;
  }

  public CommonConstants.Status getStatus() {
    return status;
  }

  public void setStatus(CommonConstants.Status status) {
    this.status = status;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getThirdPartyResponse() {
    return thirdPartyResponse;
  }

  public void setThirdPartyResponse(String thirdPartyResponse) {
    this.thirdPartyResponse = thirdPartyResponse;
  }
}
