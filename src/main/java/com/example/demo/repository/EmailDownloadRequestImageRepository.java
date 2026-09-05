package com.example.demo.repository;

import com.example.demo.entity.EmailDownloadRequestImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDownloadRequestImageRepository
    extends JpaRepository<EmailDownloadRequestImage, String> {

  List<EmailDownloadRequestImage> findByRequestId(String requestId);
}
