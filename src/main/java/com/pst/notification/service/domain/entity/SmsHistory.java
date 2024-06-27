package com.pst.notification.service.domain.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(
    indexes = {
        @Index(name = "index_message_id", columnList = "messageId", unique = true),
        @Index(name = "index_status", columnList = "status")
    })
public class SmsHistory extends BaseNotificationHistoryEntity {

  private String source;
  @ElementCollection
  private List<String> destinations;
  private String templateName;

}
