package com.example.ksat.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RequestPayInstallment(@NotNull Long installmentId,
                                    @NotNull @JsonDeserialize(using = MoneyDeserialize.class) BigDecimal amount) {
}
