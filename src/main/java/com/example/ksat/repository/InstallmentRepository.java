package com.example.ksat.repository;

import com.example.ksat.domain.Installment;
import com.example.ksat.domain.enumration.InstallmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    Optional<Installment> findByIdAndStatus(Long id, InstallmentStatus installmentStatus);
    List<Installment> findAllByDueDateBeforeAndStatus(LocalDate localDate, InstallmentStatus status);
}
