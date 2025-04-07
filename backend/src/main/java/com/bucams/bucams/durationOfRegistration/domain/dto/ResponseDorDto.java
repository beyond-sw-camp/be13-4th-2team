package com.bucams.bucams.durationOfRegistration.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseDorDto {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
