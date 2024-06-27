package com.pst.notification.service.domain.model.thirdparty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public class WhatsappText {

  @JsonProperty("preview_url")
  boolean previewUrl;
  String body;

  public boolean isPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(boolean previewUrl) {
    this.previewUrl = previewUrl;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
