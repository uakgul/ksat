package com.example.ksat.service;

import com.example.ksat.domain.Credit;
import com.example.ksat.service.dto.InstallmentDTO;
import com.example.ksat.service.dto.RequestCreateCredit;
import com.example.ksat.service.dto.RequestPayInstallment;
import com.example.ksat.service.dto.ResponsePayInstallment;

import java.util.List;

public interface InstallmentService {
    ResponsePayInstallment payInstallment(RequestPayInstallment requestPayInstallment);

    List<InstallmentDTO> createInstallment(Credit credit, RequestCreateCredit requestCreateCredit);
}
