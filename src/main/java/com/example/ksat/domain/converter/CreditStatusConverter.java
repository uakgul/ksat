package com.example.ksat.domain.converter;

import com.example.ksat.domain.enumration.CreditStatus;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

public class CreditStatusConverter implements AttributeConverter<CreditStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CreditStatus creditStatus) {
        if (creditStatus == null) {
            return null;
        }
        return creditStatus.getCode();
    }

    @Override
    public CreditStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(CreditStatus.values())
                .filter(creditStatus -> creditStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
