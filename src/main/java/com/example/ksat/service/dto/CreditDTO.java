package com.example.ksat.service.dto;

import com.example.ksat.domain.enumration.CreditStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditDTO(Long id, CreditStatus creditStatus, BigDecimal amount, LocalDateTime createDate) {
}
