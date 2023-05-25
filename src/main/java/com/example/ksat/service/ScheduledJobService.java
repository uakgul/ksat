package com.example.ksat.service;

import com.example.ksat.domain.enumration.InstallmentStatus;
import com.example.ksat.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledJobService {
    private static final BigDecimal NUM_DAY_YEAR = new BigDecimal("360");

    @Value("${ksat.installment.rate: 8.33}")
    private BigDecimal interestRate;
    private BigDecimal dailyInterestFactor;

    private final InstallmentRepository installmentRepository;

    @PostConstruct
    public void dailyInterestFactorInit() {
        dailyInterestFactor = interestRate.divide(BigDecimal.valueOf(100), 5, RoundingMode.HALF_UP)
                .divide(NUM_DAY_YEAR, 5, RoundingMode.HALF_UP);
        log.debug("interestRate: {}, dailyInterestFactor: {}", interestRate, dailyInterestFactor);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Istanbul")
    @Transactional
    public void addInterest() {
        var outstandingInstallmentList = installmentRepository
                .findAllByDueDateBeforeAndStatus(LocalDate.now(), InstallmentStatus.ACTIVE);

        if (outstandingInstallmentList == null || outstandingInstallmentList.isEmpty()) {
            return;
        }

        var now = LocalDate.now();
        var changed = outstandingInstallmentList.stream()
                .filter(Objects::nonNull)
                .map(installment -> {
                    long daysBetween = ChronoUnit.DAYS.between(installment.getDueDate(), now);

                    var interestAmount = installment.getAmount()
                            .multiply(dailyInterestFactor)
                            .multiply(new BigDecimal(daysBetween))
                            .setScale(2, RoundingMode.HALF_UP);

                    installment.setAmount(installment.getAmount().add(interestAmount));

                    log.debug("Interest added amount new amount: {}, interestAmount: {}" +
                                    " number of days late: {}, interestRate: {}, dailyInterestFactor: {}",
                            installment.getAmount(), interestAmount,
                            daysBetween, interestRate, dailyInterestFactor);

                    return installment;
                })
                .toList();

        installmentRepository.saveAll(changed);
    }

}
