package com.example.demo.endpoint.rest.controller.dto;

import java.util.List;

public record EmailDownloadRequestDto(
    String recipientEmail, String subject, List<String> imageIds) {}
