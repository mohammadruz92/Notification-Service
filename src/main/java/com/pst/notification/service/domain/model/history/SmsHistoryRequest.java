package com.pst.notification.service.domain.model.history;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


public class SmsHistoryRequest extends BaseNotificationHistoryRequest {

  String source;
  List<String> destinations = new ArrayList<>();
  String templateName;

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public List<String> getDestinations() {
    return destinations;
  }

  public void setDestinations(List<String> destinations) {
    this.destinations = destinations;
  }

  @Override
  public String getTemplateName() {
    return templateName;
  }

  @Override
  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }
}
