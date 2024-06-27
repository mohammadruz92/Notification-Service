package com.pst.notification.service.service.pinpoint;

import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.domain.model.SmsNotificationPayload;
import com.pst.notification.service.exception.NotificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.pinpoint.model.ChannelType;
import software.amazon.awssdk.services.pinpoint.model.DirectMessageConfiguration;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
import software.amazon.awssdk.services.pinpoint.model.SMSMessage;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesResponse;


@Slf4j
@Service
public class SmsPinpointNotification extends BasePinpointNotification {

  public static final String MESSAGE_TYPE = "TRANSACTIONAL";
  public static final String REGISTERED_KEYWORD = "registeredKeyword";
  public static final String SENDER_ID = "senderId";

  @Override
  protected ChannelType getChannelType() {
    return ChannelType.SMS;
  }

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response)
      throws NotificationException {
    SmsNotificationPayload smsNotificationRequest = (SmsNotificationPayload) request;

    try {
      SMSMessage smsMessage = SMSMessage.builder()
          .body(smsNotificationRequest.getMessage())
          .messageType(MESSAGE_TYPE)
//          .originationNumber(smsNotificationRequest.getSource())
          .senderId(SENDER_ID)
          .keyword(REGISTERED_KEYWORD)
          .build();

      DirectMessageConfiguration directMessageConfiguration = DirectMessageConfiguration.builder()
          .smsMessage(smsMessage)
          .build();

      SendMessagesResponse sendMessagesResponse = super.sendMessage(request,
          directMessageConfiguration);

      super.postRequestOperation(sendMessagesResponse, response);

    } catch (PinpointException e) {
      throw new NotificationException(
          String.format("%s: failed due to error: %s", this.getClass().getSimpleName(),
              e.awsErrorDetails().errorMessage()));
    }

  }
}
