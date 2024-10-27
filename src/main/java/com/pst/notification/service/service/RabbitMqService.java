package com.pst.notification.service.service;

import com.pst.notification.service.domain.dto.EmailNotificationRequest;
import com.pst.notification.service.payload.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMqService {

  private final RabbitTemplate rabbitTemplate;

  private final NotificationTemplateEngine templateEngine;

  @Value("${rabbitmq.exchange.email.name}")
  private String emailExchange;

  @Value("${rabbitmq.binding.email.name}")
  private String emailRoutingKey;

  public RabbitMqService(RabbitTemplate rabbitTemplate, NotificationTemplateEngine templateEngine) {
    this.rabbitTemplate = rabbitTemplate;
    this.templateEngine = templateEngine;
  }

  public void processEmail(EmailNotificationRequest request) {

    String content = templateEngine.process(request.templateName(), request.data());

    rabbitTemplate.convertAndSend(
        emailExchange,
        emailRoutingKey,
        EmailDetails.builder()
            .messageBody(content)
            .recipient(request.recipient())
            .subject(request.subject())
            .build());
  }
}
