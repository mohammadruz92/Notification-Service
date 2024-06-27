package com.pst.notification.service.domain.entity;


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
public class WhatsappHistory extends BaseNotificationHistoryEntity {

  private String source;
  private String destination;
  private String templateName;

}
