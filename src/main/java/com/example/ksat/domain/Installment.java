package com.example.ksat.domain;

import com.example.ksat.domain.converter.InstallmentStatusConverter;
import com.example.ksat.domain.enumration.InstallmentStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "installments")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Installment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Convert(converter = InstallmentStatusConverter.class)
    @Column(nullable = false)
    private InstallmentStatus status;

    private LocalDate dueDate;

    @ManyToOne(optional = false)
    private Credit credit;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}
