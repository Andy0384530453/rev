package com.example.demo.endpoint.rest.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageUploadService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageUploadController {

  private final ImageUploadService imageUploadService;

  @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public List<Image> uploadImages(@RequestParam("files") MultipartFile[] files) {
    return imageUploadService.upload(files);
  }
}
