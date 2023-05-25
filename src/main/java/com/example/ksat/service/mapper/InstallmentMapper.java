package com.example.ksat.service.mapper;

import com.example.ksat.domain.Installment;
import com.example.ksat.service.dto.InstallmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstallmentMapper {
    InstallmentMapper INSTANCE = Mappers.getMapper(InstallmentMapper.class);
    @Mapping(source = "status", target = "installmentStatus")
    InstallmentDTO installmentToInstallmentDTO(Installment installment);

}
