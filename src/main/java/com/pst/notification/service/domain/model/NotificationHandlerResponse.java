package com.pst.notification.service.domain.model;


import com.pst.notification.service.utility.CommonConstants;
import com.pst.notification.service.utility.ObjectMapperUtility;
import lombok.Data;



public class NotificationHandlerResponse {

  String messageId;
  CommonConstants.Status status;
  String errorMessage;
  String thirdPartyResponse;

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
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

  public void addThirdPartyResponse(Object thirdPartyResponseObj) {
    this.thirdPartyResponse = ObjectMapperUtility.writeValueAsString(thirdPartyResponseObj);
    if ("{}".equalsIgnoreCase(this.thirdPartyResponse)) {
      this.thirdPartyResponse = thirdPartyResponseObj.toString();
    }
  }

}
