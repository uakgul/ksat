package com.example.ksat.domain.converter;

import com.example.ksat.domain.enumration.InstallmentStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class InstallmentStatusConverter implements AttributeConverter<InstallmentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InstallmentStatus installmentStatus) {
        if (installmentStatus == null) {
            return null;
        }
        return installmentStatus.getCode();
    }

    @Override
    public InstallmentStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(InstallmentStatus.values())
                .filter(installmentStatus -> installmentStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
