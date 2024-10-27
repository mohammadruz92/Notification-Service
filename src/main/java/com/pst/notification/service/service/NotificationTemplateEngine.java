package com.pst.notification.service.service;

import com.pst.notification.service.constants.SystemConstants;
import com.pst.notification.service.exception.NotificationException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Slf4j
@Service
public class NotificationTemplateEngine {

  private final Configuration freemarkerConfiguration;

  public NotificationTemplateEngine(Configuration freemarkerConfiguration) {
    this.freemarkerConfiguration = freemarkerConfiguration;
  }

  public String process(String templateName, Map<String, Object> data) {
    String templateLocation = constructTemplateLocation(templateName);

    try {
      Template template = loadTemplate(templateLocation);
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
    } catch (Exception e) {
      throw createNotificationException(templateName, e);
    }
  }

  private String constructTemplateLocation(String templateName) {
    return Paths.get(templateName + SystemConstants.TEMPLATE_FILE_EXTENSION).toString();
  }

  private Template loadTemplate(String templateLocation) throws IOException {
    return freemarkerConfiguration.getTemplate(templateLocation);
  }

  private NotificationException createNotificationException(String templateName, Exception e) {
    String message =
        String.format(
            "%s: Failed to process template with templateName: %s with error: %s",
            this.getClass().getSimpleName(), templateName, e.getMessage());
    return new NotificationException(message);
  }
}
