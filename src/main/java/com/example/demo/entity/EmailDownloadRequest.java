package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmailDownloadRequest {
  @Id private String id;
  private String recipientEmail;
  private String subject;
  private String body;
  private String downloadToken;
  private Instant createdAt;
}
