package com.example.ksat.service.impl;

import com.example.ksat.domain.Credit;
import com.example.ksat.domain.Installment;
import com.example.ksat.domain.enumration.InstallmentStatus;
import com.example.ksat.exception.InstallmentNotFoundException;
import com.example.ksat.repository.InstallmentRepository;
import com.example.ksat.service.InstallmentService;
import com.example.ksat.service.dto.*;
import com.example.ksat.service.mapper.InstallmentMapper;
import com.example.ksat.service.util.InstallmentUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;

    @Override
    @Transactional
    public ResponsePayInstallment payInstallment(@NotNull RequestPayInstallment requestPayInstallment) {
        Installment installment = installmentRepository.findByIdAndStatus(
                        requestPayInstallment.installmentId(), InstallmentStatus.ACTIVE)
                .orElseThrow(InstallmentNotFoundException::new);

        BigDecimal remainingAmount;
        int compare = installment.getAmount().compareTo(requestPayInstallment.amount());

        if (compare > 0) {
            BigDecimal newDebt = installment.getAmount().subtract(requestPayInstallment.amount());
            installment.setAmount(newDebt);
            remainingAmount = BigDecimal.ZERO;
        } else if (compare == 0) {
            installment.setAmount(BigDecimal.ZERO);
            installment.setStatus(InstallmentStatus.PAID);
            remainingAmount = BigDecimal.ZERO;
        } else {
            remainingAmount = requestPayInstallment.amount().subtract(installment.getAmount());
            installment.setAmount(BigDecimal.ZERO);
            installment.setStatus(InstallmentStatus.PAID);
        }

        InstallmentDTO installmentDTO = InstallmentMapper.INSTANCE.installmentToInstallmentDTO(installment);
        return new ResponsePayInstallment(remainingAmount, installmentDTO);
    }

    @Override
    @Transactional
    public List<InstallmentDTO> createInstallment(@NotNull Credit credit, @NotNull RequestCreateCredit requestCreateCredit) {
        BigDecimal firstInstallmentAmount = InstallmentUtil.calculateFirstInstallment(requestCreateCredit);
        BigDecimal lastInstallmentAmount = InstallmentUtil.calculateLastInstallment(requestCreateCredit, firstInstallmentAmount);

        LocalDate today = LocalDate.now();
        List<Installment> installmentList = IntStream.range(1, requestCreateCredit.installmentCount())
                .mapToObj(value -> {
                    Installment installment = new Installment();
                    installment.setCredit(credit);
                    installment.setStatus(InstallmentStatus.ACTIVE);
                    installment.setAmount(firstInstallmentAmount);
                    installment.setDueDate(InstallmentUtil.calculateInstallmentDueDate(value, today));
                    return installment;
                }).collect(Collectors.toList());

        Installment lastInstallment = new Installment();
        lastInstallment.setCredit(credit);
        lastInstallment.setStatus(InstallmentStatus.ACTIVE);
        lastInstallment.setAmount(lastInstallmentAmount);
        lastInstallment.setDueDate(InstallmentUtil.calculateInstallmentDueDate(requestCreateCredit.installmentCount(), today));

        installmentList.add(lastInstallment);
        return installmentRepository.saveAll(installmentList).stream()
                .map(installment -> new InstallmentDTO(installment.getId(),
                        installment.getAmount(),
                        installment.getStatus(),
                        installment.getDueDate()))
                .toList();
    }
}
