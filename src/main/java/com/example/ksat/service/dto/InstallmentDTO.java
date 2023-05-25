package com.example.ksat.service.dto;

import com.example.ksat.domain.enumration.InstallmentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InstallmentDTO(Long id, BigDecimal amount, InstallmentStatus installmentStatus, LocalDate dueDate) {
}
