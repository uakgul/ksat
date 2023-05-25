package com.example.ksat.service.dto;

import java.math.BigDecimal;

public record ResponsePayInstallment(BigDecimal remainingAmount, InstallmentDTO installmentDTO) {
}
