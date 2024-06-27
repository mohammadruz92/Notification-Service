package com.pst.notification.service.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.pst.notification.service.utility.CommonConstants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseNotificationHistoryEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @EqualsAndHashCode.Include
  private UUID id;

  String appId;
  String messageId;

  @Column(columnDefinition = "TEXT")
  String content;

  @Column(columnDefinition = "TEXT")
  String notificationRequest;

  @Enumerated(EnumType.STRING)
  CommonConstants.Status status;

  @Column(columnDefinition = "TEXT")
  String errorMessage;

  @Column(columnDefinition = "TEXT")
  String thirdPartyResponse;

  @CreatedBy
  protected String createdBy;
  @CreatedDate
  protected Date createdDate;
  @LastModifiedBy
  protected String lastModifiedBy;
  @LastModifiedDate
  protected Date lastModifiedDate;

  @Version
  private Long version;


}
