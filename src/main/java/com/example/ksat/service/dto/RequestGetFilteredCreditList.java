package com.example.ksat.service.dto;

import com.example.ksat.domain.enumration.CreditStatus;
import com.sun.istack.NotNull;

import java.time.LocalDateTime;

public record RequestGetFilteredCreditList(@NotNull Long userId, CreditStatus creditStatus, LocalDateTime createDate) {
}
