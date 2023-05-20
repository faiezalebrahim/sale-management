package com.example.challenge2.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailsResponse {
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
