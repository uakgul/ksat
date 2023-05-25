package com.example.ksat.service.util;

import com.example.ksat.service.dto.RequestCreateCredit;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public final class InstallmentUtil {
    private InstallmentUtil() {
    }

    public static BigDecimal calculateFirstInstallment(@NotNull RequestCreateCredit requestCreateCredit) {
        BigDecimal totalAmount = requestCreateCredit.amount();
        Integer installmentCount = requestCreateCredit.installmentCount();

        return totalAmount.setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(installmentCount), RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateLastInstallment(@NotNull RequestCreateCredit requestCreateCredit,
                                                      @NotNull BigDecimal firstInstallmentAmount) {
        BigDecimal totalAmount = requestCreateCredit.amount();
        Integer installmentCount = requestCreateCredit.installmentCount();
        return totalAmount.subtract(firstInstallmentAmount.multiply(BigDecimal.valueOf(installmentCount - 1L)));
    }

    public static LocalDate calculateInstallmentDueDate(@NotNull Integer installmentNumber, @NotNull LocalDate today) {
        LocalDate dueDate = today.plusDays(installmentNumber * 30L);
        DayOfWeek dayOfWeek = DayOfWeek.of(dueDate.get(ChronoField.DAY_OF_WEEK));

        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return dueDate.plusDays(2);
        }

        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return dueDate.plusDays(1);
        }

        return dueDate;
    }

}
