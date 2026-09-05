package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.controller.dto.EmailDownloadRequestDto;
import com.example.demo.entity.EmailDownloadRequest;
import com.example.demo.service.EmailImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmailImageController {

  private final EmailImageService emailImageService;

  @PostMapping("/email-images")
  public EmailDownloadRequest emailImages(@RequestBody EmailDownloadRequestDto dto) {
    return emailImageService.sendDownloadLinks(dto);
  }
}
