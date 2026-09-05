package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "email_download_request")
public class EmailDownloadRequest {
  @Id private String id;
  private String recipientEmail;
  private String subject;
  private String body;
  private String downloadToken;
  private Instant createdAt;
}
