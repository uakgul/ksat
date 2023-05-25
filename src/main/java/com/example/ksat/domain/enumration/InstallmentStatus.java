package com.example.ksat.domain.enumration;

public enum InstallmentStatus {
    ACTIVE(0), PAID(1);

    InstallmentStatus(Integer code) {
        this.code = code;
    }

    private final Integer code;

    public Integer getCode() {
        return code;
    }
}
