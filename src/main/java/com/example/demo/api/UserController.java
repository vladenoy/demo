package com.example.demo.api;

import com.example.demo.dao.entity.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findByExample(@RequestParam(required = false) LocalDate dateOfBirth,
                                       @RequestParam(required = false) String phone,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String email,
                                       @RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "5") int pageSize) {
        return userService.findBy(dateOfBirth, phone, name, email, pageNumber, pageSize);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long count() {
        return userService.count();
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody User user) {
        userService.save(user);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
    }
}
