package com.pst.notification.service.service.pinpoint;

import java.util.HashMap;
import java.util.Map;

import com.pst.notification.service.config.AwsPinpointClient;
import com.pst.notification.service.domain.model.NotificationHandlerResponse;
import com.pst.notification.service.domain.model.NotificationPayload;
import com.pst.notification.service.exception.NotificationException;
import com.pst.notification.service.utility.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.pinpoint.PinpointClient;
import software.amazon.awssdk.services.pinpoint.model.AddressConfiguration;
import software.amazon.awssdk.services.pinpoint.model.ChannelType;
import software.amazon.awssdk.services.pinpoint.model.DeliveryStatus;
import software.amazon.awssdk.services.pinpoint.model.DirectMessageConfiguration;
import software.amazon.awssdk.services.pinpoint.model.MessageRequest;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesRequest;
import software.amazon.awssdk.services.pinpoint.model.SendMessagesResponse;
;

@Service
public abstract class BasePinpointNotification {

  @Autowired
  protected AwsPinpointClient pinpointClient;

  public abstract void sendNotification(NotificationPayload request,
                                        NotificationHandlerResponse response) throws NotificationException;

  abstract ChannelType getChannelType();

  protected SendMessagesResponse sendMessage(NotificationPayload request,
      DirectMessageConfiguration directMessageConfiguration) {
    PinpointClient pinpoint = getPinpointClient();
    MessageRequest messageRequest = MessageRequest.builder()
        .addresses(getAddressMap(request))
        .messageConfiguration(directMessageConfiguration)
        .build();
    SendMessagesRequest messagesRequest = SendMessagesRequest.builder()
        .applicationId(pinpointClient.getProjectId())
        .messageRequest(messageRequest)
        .build();

    return pinpoint.sendMessages(messagesRequest);
  }

  protected void postRequestOperation(SendMessagesResponse sendMessagesResponse,
      NotificationHandlerResponse response) {
    response.addThirdPartyResponse(sendMessagesResponse);
    sendMessagesResponse.messageResponse().result().forEach((destination, messageResult) -> {
      if (DeliveryStatus.SUCCESSFUL != messageResult.deliveryStatus()) {
        response.setStatus(CommonConstants.Status.FAILED_AT_THIRD_PARTY);
      }
    });
  }

  private PinpointClient getPinpointClient() {
    return pinpointClient.getPinpointClient();
  }

  private Map<String, AddressConfiguration> getAddressMap(NotificationPayload request) {
    Map<String, AddressConfiguration> addressMap = new HashMap<>();
    request.getDestinations()
        .forEach(toAddress -> addressMap.put(toAddress, getAddressConfiguration()));
    return addressMap;
  }


  private AddressConfiguration getAddressConfiguration() {
    return AddressConfiguration.builder()
        .channelType(getChannelType())
        .build();
  }

}
