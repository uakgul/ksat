package com.example.ksat.repository;

import com.example.ksat.domain.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long>, JpaSpecificationExecutor<Credit> {
    List<Credit> findAllByUserId(Long userId);
}
