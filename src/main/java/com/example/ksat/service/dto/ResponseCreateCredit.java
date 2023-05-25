package com.example.ksat.service.dto;

import java.util.List;

public record ResponseCreateCredit(Long userId, Long creditId, List<InstallmentDTO> installmentList) {
}
