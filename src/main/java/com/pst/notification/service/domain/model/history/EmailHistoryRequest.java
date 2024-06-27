package com.pst.notification.service.domain.model.history;

import lombok.Data;

@Data
public class EmailHistoryRequest extends BaseNotificationHistoryRequest {

  String subject;
  String htmlBody;

}
