package com.example.ksat.web.rest;

import com.example.ksat.service.InstallmentService;
import com.example.ksat.service.dto.RequestPayInstallment;
import com.example.ksat.service.dto.ResponsePayInstallment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class InstallmentController {

    private final InstallmentService installmentService;

    @PostMapping("/installments/pay")
    ResponseEntity<ResponsePayInstallment> payInstallment(@RequestBody RequestPayInstallment requestPayInstallment) {
        log.debug("REST request to pay installment, requestPayInstallment: {}", requestPayInstallment);
        return ResponseEntity.ok().body(installmentService.payInstallment(requestPayInstallment));
    }
}
