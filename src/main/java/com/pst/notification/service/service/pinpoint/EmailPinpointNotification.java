package com.pst.notification.service.service.pinpoint;

import com.pst.notification.service.domain.model.EmailNotificationPayload;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.exception.NotificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.pinpoint.model.ChannelType;
import software.amazon.awssdk.services.pinpoint.model.DirectMessageConfiguration;
import software.amazon.awssdk.services.pinpoint.model.EmailMessage;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesResponse;
import software.amazon.awssdk.services.pinpoint.model.SimpleEmail;
import software.amazon.awssdk.services.pinpoint.model.SimpleEmailPart;


@Slf4j
@Service
public class EmailPinpointNotification extends BasePinpointNotification {

  private static final String CHARSET = "UTF-8";
  private static final String EMAIL_SOURCE = "XX@XX.com";

  @Override
  protected ChannelType getChannelType() {
    return ChannelType.EMAIL;
  }

  @Override
  public void sendNotification(NotificationPayload request, NotificationHandlerResponse response) {
    EmailNotificationPayload emailNotificationRequest = (EmailNotificationPayload) request;

    try {

      SimpleEmailPart emailPart = SimpleEmailPart.builder()
          .data(emailNotificationRequest.getMessage())
          .charset(CHARSET)
          .build();

      SimpleEmailPart subjectPart = SimpleEmailPart.builder()
          .data(emailNotificationRequest.getSubject())
          .charset(CHARSET)
          .build();

      SimpleEmail simpleEmail = SimpleEmail.builder()
          .htmlPart(emailPart)
          .subject(subjectPart)
          .build();

      EmailMessage emailMessage = EmailMessage.builder()
          .body(emailNotificationRequest.getMessage())
          .fromAddress(EMAIL_SOURCE)
          .simpleEmail(simpleEmail)
          .build();

      DirectMessageConfiguration directMessageConfiguration = DirectMessageConfiguration.builder()
          .emailMessage(emailMessage)
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
