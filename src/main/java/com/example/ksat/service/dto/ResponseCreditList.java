package com.example.ksat.service.dto;

import java.util.List;

public record ResponseCreditList(Long userId, List<CreditDTO> creditDTOList) {
}
