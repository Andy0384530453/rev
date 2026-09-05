package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "email_download_request_image")

public class EmailDownloadRequestImage {
  @Id private String id;
  private String requestId;
  private String imageId;
}
