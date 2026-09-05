package com.example.demo.repository;

import com.example.demo.entity.EmailDownloadRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDownloadRequestRepository
    extends JpaRepository<EmailDownloadRequest, String> {

  Optional<EmailDownloadRequest> findByDownloadToken(String downloadToken);
}
