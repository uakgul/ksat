package com.example.ksat.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record RequestCreateCredit(@NotNull Long userId,
                                  @NotNull @JsonDeserialize(using = MoneyDeserialize.class) BigDecimal amount,
                                  @NotNull Integer installmentCount) {
}
