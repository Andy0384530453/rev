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

@Table(name = "image")

public class Image {
  @Id private String id;
  private String filename;
  private String bucketKey;
  private String mimeType;
  private Long size;
  private Instant createdAt;
}
