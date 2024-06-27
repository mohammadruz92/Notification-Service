package com.pst.notification.service.domain.model;

import lombok.Data;
import lombok.ToString;


@ToString(callSuper= true)
public class WhatsappNotificationPayload extends NotificationPayload {

  String destination;

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }
}
