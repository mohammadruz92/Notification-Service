package com.pst.notification.service.domain.model.history;

import lombok.Data;

@Data
public class WhatsappHistoryRequest extends BaseNotificationHistoryRequest {

  String source;
  String destination;
  String templateName;

}
