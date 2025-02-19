package com.ollama.backend.specification;

import com.ollama.backend.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSearchSpecification {

    public static Specification<User> searchUser(String firstName, String lastName, String ssn, String userId, String email) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), firstName)));
            }
            if (lastName != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), lastName)));
            }
            if (ssn != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("ssn"), ssn)));
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userId"), userId)));
            }
            if (email != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email)));
            }
            Predicate[] predicatesArray = new Predicate[predicates.size()];

            return criteriaBuilder.and(predicates.toArray(predicatesArray));
        };
    }
}
