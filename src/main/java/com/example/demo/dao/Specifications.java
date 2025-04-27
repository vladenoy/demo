package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@UtilityClass
public class Specifications {

    public static Specification<User> isPhoneEquals(String phone) {
        return (root, cq, cb)
                -> cb.equal(root.join("phones").get("phone"), phone);
    }

    public static Specification<User> isDateOfBirthGreater(LocalDate dateOfBirth) {
        return (root, cq, cb)
                -> cb.greaterThan(root.get("dateOfBirth"), dateOfBirth);
    }

    public static Specification<User> isNameStartBy(String name) {
        return (root, cq, cb)
                -> cb.like(root.get("name"), name + "%");
    }

    public static Specification<User> isEmailEquals(String email) {
        return (root, cq, cb)
                -> cb.equal(root.join("emails").get("email"), email);
    }
}
