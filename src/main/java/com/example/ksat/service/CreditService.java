package com.example.ksat.service;

import com.example.ksat.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CreditService {
    ResponseCreateCredit createCredit(RequestCreateCredit requestCreateCredit);

    ResponseCreditList getAllCreditList(Long userId);

    Page<CreditDTO> getFilteredCreditList(RequestGetFilteredCreditList requestGetFilteredCreditList, Pageable pageable);
}
