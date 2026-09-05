package com.example.demo.service;

import static java.time.Duration.ofMinutes;
import static java.util.UUID.randomUUID;

import com.example.demo.endpoint.rest.controller.dto.EmailDownloadRequestDto;
import com.example.demo.entity.EmailDownloadRequest;
import com.example.demo.entity.EmailDownloadRequestImage;
import com.example.demo.entity.Image;
import com.example.demo.file.bucket.BucketComponent;
import com.example.demo.mail.Email;
import com.example.demo.mail.Mailer;
import com.example.demo.repository.EmailDownloadRequestImageRepository;
import com.example.demo.repository.EmailDownloadRequestRepository;
import com.example.demo.repository.ImageRepository;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailImageService {

  private final ImageRepository imageRepository;
  private final EmailDownloadRequestRepository requestRepository;
  private final EmailDownloadRequestImageRepository requestImageRepository;
  private final BucketComponent bucketComponent;
  private final Mailer mailer;

  @SneakyThrows
  public EmailDownloadRequest sendDownloadLinks(EmailDownloadRequestDto dto) {
    var images = imageRepository.findAllById(dto.imageIds());
    var links = presignImageLinks(images);
    var body = buildHtmlBody(dto.subject(), links);
    var downloadToken = randomUUID().toString();

    mailer.accept(
        new Email(
            new InternetAddress(dto.recipientEmail()),
            List.of(),
            List.of(),
            dto.subject(),
            body,
            List.of()));

    return persistRequest(dto, downloadToken, images);
  }

  private List<String> presignImageLinks(List<Image> images) {
    return images.stream()
        .map(Image::getBucketKey)
        .map(key -> bucketComponent.presign(key, ofMinutes(5)).toString())
        .toList();
  }

  private String buildHtmlBody(String subject, List<String> links) {
    var listItems = new StringBuilder();
    for (int i = 0; i < links.size(); i++) {
      listItems
          .append("<li><a href=\"")
          .append(links.get(i))
          .append("\">T&eacute;l&eacute;charger l'image ")
          .append(i + 1)
          .append("</a></li>");
    }
    return "<html><body><h1>"
        + subject
        + "</h1><p>Cliquez sur les liens ci-dessous pour t&eacute;l&eacute;charger vos"
        + " images.</p><ul>"
        + listItems
        + "</ul></body></html>";
  }

  private EmailDownloadRequest persistRequest(
      EmailDownloadRequestDto dto, String downloadToken, List<Image> images) {
    var request = new EmailDownloadRequest();
    request.setId(randomUUID().toString());
    request.setRecipientEmail(dto.recipientEmail());
    request.setSubject(dto.subject());
    request.setDownloadToken(downloadToken);
    request.setCreatedAt(java.time.Instant.now());
    requestRepository.save(request);

    images.forEach(
        image -> {
          var link = new EmailDownloadRequestImage();
          link.setId(randomUUID().toString());
          link.setRequestId(request.getId());
          link.setImageId(image.getId());
          requestImageRepository.save(link);
        });
    return request;
  }
}
