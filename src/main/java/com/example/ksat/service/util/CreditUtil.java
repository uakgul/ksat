package com.example.ksat.service.util;

import com.example.ksat.domain.Credit;
import com.example.ksat.domain.User;
import com.example.ksat.service.dto.RequestGetFilteredCreditList;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public final class CreditUtil {
    private CreditUtil() {
    }

    public static Specification<Credit> createSpecification(@NotNull RequestGetFilteredCreditList requestGetFilteredCreditList) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Join<Credit, User> user = root.join("user");
            list.add(cb.equal(user.get("id"), requestGetFilteredCreditList.userId()));

            if (requestGetFilteredCreditList.creditStatus() != null) {
                list.add(cb.equal(root.get("status"), requestGetFilteredCreditList.creditStatus()));
            }

            if (requestGetFilteredCreditList.createDate() != null) {
                list.add(cb.greaterThan(root.get("createDate"), requestGetFilteredCreditList.createDate()));
            }

            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
    }
}
