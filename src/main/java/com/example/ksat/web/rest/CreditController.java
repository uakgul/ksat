package com.example.ksat.web.rest;

import com.example.ksat.domain.enumration.CreditStatus;
import com.example.ksat.service.CreditService;
import com.example.ksat.service.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Slf4j
public class CreditController {

    CreditService creditService;

    @PostMapping("/credits")
    ResponseEntity<ResponseCreateCredit> createCredit(@RequestBody @Valid RequestCreateCredit requestCredit) {
        log.debug("REST request to create credit: {}", requestCredit);
        return ResponseEntity.ok().body(creditService.createCredit(requestCredit));
    }

    @GetMapping("/credits/{userId}")
    ResponseEntity<ResponseCreditList> getAllCreditList(@PathVariable Long userId) {
        log.debug("REST request to get all credit list, UserId: {}", userId);
        return ResponseEntity.ok().body(creditService.getAllCreditList(userId));
    }

    @GetMapping("/credits/paged/{userId}")
    ResponseEntity<Page<CreditDTO>> getFilteredCreditList(@PathVariable Long userId,
                                                          @RequestParam(required = false) CreditStatus creditStatus,
                                                          @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                          LocalDateTime createDate,
                                                          @RequestParam(required = false) Integer page,
                                                          @RequestParam(required = false) Integer size) {

        log.debug("REST request to get filtered credit list, UserId: {}, CreditStatus: {}, CreateDate: {}" +
                        " page: {}, size: {}",
                userId, creditStatus, createDate, page, size);

        var request = new RequestGetFilteredCreditList(userId, creditStatus, createDate);
        return ResponseEntity.ok()
                .body(creditService.getFilteredCreditList(request, ControllerUtil.validatePageable(page, size)));
    }
}
