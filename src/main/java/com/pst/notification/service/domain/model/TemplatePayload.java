package com.pst.notification.service.domain.model;

import java.util.Map;
import lombok.Data;


public class TemplatePayload {

  String templateName;
  Map<String, Object> data;

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }
}
