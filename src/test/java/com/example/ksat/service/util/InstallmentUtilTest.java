package com.example.ksat.service.util;

import com.example.ksat.service.dto.RequestCreateCredit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstallmentUtilTest {
    private static final RequestCreateCredit REQUEST_CREATE_CREDIT = new RequestCreateCredit(1L, new BigDecimal("100.00"), 3);
    private static final BigDecimal EXPECTED_FIRST_INSTALLMENT = new BigDecimal("33.33");
    private static final BigDecimal EXPECTED_LAST_INSTALLMENT = new BigDecimal("33.34");

    @Test
    void calculateFirstInstallment() {
        var firstInstallment = InstallmentUtil.calculateFirstInstallment(REQUEST_CREATE_CREDIT);
        assertEquals(EXPECTED_FIRST_INSTALLMENT, firstInstallment);
    }

    @Test
    void calculateLastInstallment() {
        var firstInstallment = InstallmentUtil.calculateFirstInstallment(REQUEST_CREATE_CREDIT);
        var lastInstallment = InstallmentUtil.calculateLastInstallment(REQUEST_CREATE_CREDIT, firstInstallment);

        assertEquals(EXPECTED_LAST_INSTALLMENT, lastInstallment);
    }

    @Test
    void calculateInstallmentDueDateForSaturday() {
        var localDate = LocalDate.of(2023, 5, 25);
        var dueDate = InstallmentUtil.calculateInstallmentDueDate(1, localDate);
        var expectedDate = LocalDate.of(2023, 6, 26);

        assertEquals(dueDate, expectedDate);
    }

    @Test
    void calculateInstallmentDueDateForSunday() {
        var localDate = LocalDate.of(2023, 5, 26);
        var dueDate = InstallmentUtil.calculateInstallmentDueDate(1, localDate);
        var expectedDate = LocalDate.of(2023, 6, 26);

        assertEquals(expectedDate, dueDate);
    }

    @Test
    void calculateInstallmentDueDateForWeekOfDay() {
        var localDate = LocalDate.of(2023, 5, 27);
        var dueDate = InstallmentUtil.calculateInstallmentDueDate(1, localDate);
        var expectedDate = LocalDate.of(2023, 6, 26);

        assertEquals(expectedDate, dueDate);
    }
}
