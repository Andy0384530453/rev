package com.example.demo.service;

import static java.util.UUID.randomUUID;

import com.example.demo.entity.Image;
import com.example.demo.file.bucket.BucketComponent;
import com.example.demo.file.zip.FileTyper;
import com.example.demo.repository.ImageRepository;
import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageUploadService {

  private final BucketComponent bucketComponent;
  private final ImageRepository imageRepository;
  private final FileTyper fileTyper;

  @SneakyThrows
  public List<Image> upload(MultipartFile[] files) {
    var images = new ArrayList<Image>();
    for (var file : files) {
      var bucketKey = buildBucketKey(file.getOriginalFilename());
      var tmpFile = toTmpFile(file);
      bucketComponent.upload(tmpFile, bucketKey);

      var image = new Image();
      image.setId(randomUUID().toString());
      image.setFilename(file.getOriginalFilename());
      image.setBucketKey(bucketKey);
      image.setMimeType(String.valueOf(fileTyper.apply(tmpFile)));
      image.setSize(file.getSize());
      image.setCreatedAt(Instant.now());
      imageRepository.save(image);
      images.add(image);
    }
    return images;
  }

  private String buildBucketKey(String filename) {
    var sanitized = filename == null ? "image" : filename.replaceAll("\\s+", "_");
    return "images/" + randomUUID() + "_" + sanitized;
  }

  @SneakyThrows
  private File toTmpFile(MultipartFile file) {
    var tmp = File.createTempFile("upload-", safeSuffix(file.getOriginalFilename()));
    file.transferTo(tmp);
    return tmp;
  }

  private String safeSuffix(String filename) {
    if (filename == null || !filename.contains(".")) {
      return ".img";
    }
    return filename.substring(filename.lastIndexOf('.'));
  }
}
