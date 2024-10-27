package com.pst.notification.service.controller;

import com.pst.notification.service.constants.StatusMessages;
import com.pst.notification.service.domain.dto.EmailNotificationRequest;
import com.pst.notification.service.payload.ResponseDto;
import com.pst.notification.service.service.RabbitMqService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

  private final RabbitMqService rabbitMqService;

  public EmailController(RabbitMqService rabbitMqService) {
    this.rabbitMqService = rabbitMqService;
  }

  @PostMapping("/send")
  public ResponseEntity<ResponseDto> sendEmail(@RequestBody EmailNotificationRequest request) {
    rabbitMqService.processEmail(request);
    return new ResponseEntity<>(
        ResponseDto.builder()
            .statusCode(HttpStatus.CREATED.toString())
            .statusMsg(StatusMessages.EMAIL_TRANSITION_SUCCESS)
            .build(),
        HttpStatus.CREATED);
  }
}
