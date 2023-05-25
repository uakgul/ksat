package com.example.ksat.service.impl;

import com.example.ksat.domain.Credit;
import com.example.ksat.domain.User;
import com.example.ksat.domain.enumration.CreditStatus;
import com.example.ksat.repository.CreditRepository;
import com.example.ksat.service.CreditService;
import com.example.ksat.service.InstallmentService;
import com.example.ksat.service.UserService;
import com.example.ksat.service.dto.*;
import com.example.ksat.service.mapper.CreditMapper;
import com.example.ksat.service.util.CreditUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final UserService userService;
    private final InstallmentService installmentService;

    @Override
    @Transactional
    public ResponseCreateCredit createCredit(@NotNull RequestCreateCredit requestCreateCredit) {
        User user = userService.getUserOrThrow(requestCreateCredit.userId());

        Credit credit = this.saveCredit(user, requestCreateCredit.amount());
        List<InstallmentDTO> savedList = installmentService.createInstallment(credit, requestCreateCredit);

        return new ResponseCreateCredit(user.getId(), credit.getId(), savedList);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCreditList getAllCreditList(@NotNull Long userId) {
        userService.checkUserExistOrThrow(userId);

        var creditDTOList = creditRepository.findAllByUserId(userId).stream()
                .map(CreditMapper.INSTANCE::creditToCreditDTO)
                .toList();

        return new ResponseCreditList(userId, creditDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CreditDTO> getFilteredCreditList(@NotNull RequestGetFilteredCreditList requestGetFilteredCreditList,
                                                 @NotNull Pageable pageable) {
        var specification = CreditUtil.createSpecification(requestGetFilteredCreditList);
        return creditRepository.findAll(specification, pageable).map(CreditMapper.INSTANCE::creditToCreditDTO);
    }

    private Credit saveCredit(@NotNull User user, @NotNull BigDecimal amount) {
        Credit credit = new Credit();
        credit.setUser(user);
        credit.setAmount(amount);
        credit.setStatus(CreditStatus.ACTIVE);
        creditRepository.save(credit);
        return credit;
    }
}
