package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
  @Id private String id;
  private String filename;
  private String bucketKey;
  private String mimeType;
  private Long size;
  private Instant createdAt;
}
