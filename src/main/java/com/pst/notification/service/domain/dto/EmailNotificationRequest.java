package com.pst.notification.service.domain.dto;

import java.util.Map;

public record EmailNotificationRequest(
    String appId,
    String notificationType,
    String subject,
    String templateName,
    String recipient,
    Map<String, Object> data) {}
