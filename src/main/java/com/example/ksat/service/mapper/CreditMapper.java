package com.example.ksat.service.mapper;

import com.example.ksat.domain.Credit;
import com.example.ksat.service.dto.CreditDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(source = "status", target = "creditStatus")
    CreditDTO creditToCreditDTO(Credit credit);
}

