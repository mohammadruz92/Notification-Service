package com.pst.notification.service.domain.model.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public class WhatsappTextMessageRequest {

  @JsonProperty("messaging_product")
  String messagingProduct = "whatsapp";
  @JsonProperty("recipient_type")
  String recipientType = "individual";
  String to;
  String type = "text";
  WhatsappText text;

  public String getMessagingProduct() {
    return messagingProduct;
  }

  public void setMessagingProduct(String messagingProduct) {
    this.messagingProduct = messagingProduct;
  }

  public String getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(String recipientType) {
    this.recipientType = recipientType;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public WhatsappText getText() {
    return text;
  }

  public void setText(WhatsappText text) {
    this.text = text;
  }
}
