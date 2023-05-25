package com.example.ksat.domain;

import com.example.ksat.domain.converter.CreditStatusConverter;
import com.example.ksat.domain.enumration.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credits")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Convert(converter = CreditStatusConverter.class)
    @Column(nullable = false)
    private CreditStatus status;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(mappedBy = "credit")
    private Set<Installment> installmentSet = new HashSet<>();

    @ManyToOne(optional = false)
    private User user;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;
}
