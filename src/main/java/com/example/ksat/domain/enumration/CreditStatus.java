package com.example.ksat.domain.enumration;

import java.util.Arrays;

public enum CreditStatus {
    ACTIVE(0), PAID(1);

    CreditStatus(Integer code) {
        this.code = code;
    }

    private final Integer code;

    public Integer getCode() {
        return code;
    }

    public static CreditStatus getStatusId(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(CreditStatus.values())
                .filter(creditStatus -> creditStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
