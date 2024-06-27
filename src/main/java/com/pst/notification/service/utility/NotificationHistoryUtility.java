package com.pst.notification.service.utility;


import com.pst.notification.service.domain.entity.EmailHistory;
import com.pst.notification.service.domain.entity.SmsHistory;
import com.pst.notification.service.domain.entity.WhatsappHistory;
import com.pst.notification.service.domain.model.history.EmailHistoryRequest;
import com.pst.notification.service.domain.model.history.SmsHistoryRequest;
import com.pst.notification.service.domain.model.history.WhatsappHistoryRequest;

public class NotificationHistoryUtility {

  public static SmsHistory buildSmsHistory(SmsHistoryRequest smsHistoryRequest) {
    return ObjectMapperUtility.convertValue(smsHistoryRequest, SmsHistory.class);
  }

  public static EmailHistory buildEmailHistory(
      EmailHistoryRequest emailHistoryRequest) {
    return ObjectMapperUtility.convertValue(emailHistoryRequest, EmailHistory.class);
  }

  public static WhatsappHistory buildWhatsappHistory(
      WhatsappHistoryRequest whatsappHistoryRequest) {
    return ObjectMapperUtility.convertValue(whatsappHistoryRequest, WhatsappHistory.class);
  }

  private NotificationHistoryUtility() {

  }

}
