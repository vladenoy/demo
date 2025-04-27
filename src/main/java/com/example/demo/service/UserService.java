package com.example.demo.service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.demo.dao.Specifications.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException("User not found"));
    }

    public List<UserDTO> findBy(LocalDate dateOfBirth, String phone, String name,
                                String email, int pageNumber, int pageSize) {

        Specification<User> spec = Specification.where(null);

        if(Objects.nonNull(dateOfBirth)) spec = spec.and(isDateOfBirthGreater(dateOfBirth));
        if(Objects.nonNull(phone)) spec = spec.and(isPhoneEquals(phone));
        if(Objects.nonNull(name)) spec = spec.and(isNameStartBy(name));
        if(Objects.nonNull(email)) spec = spec.and(isEmailEquals(email));

        return userRepository.findAll(spec, PageRequest.of(pageNumber, pageSize)).stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .dateOfBirth(user.getDateOfBirth())
                        .account(user.getAccount())
                        .phones(user.getPhones())
                        .emails(user.getEmails())
                        .build())
                .toList();
    }

    public long count() {
        return userRepository.count();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
