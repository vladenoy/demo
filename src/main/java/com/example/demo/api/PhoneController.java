package com.example.demo.api;

import com.example.demo.dao.entity.PhoneData;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneController {

    private final UserService userService;

    @Transactional
    @PutMapping(path = "/phone")
    public void add(@RequestParam String phone,
                    @RequestParam Long userId) {
        var user = userService.findById(userId);

        user.getPhones().add(new PhoneData(null, user, phone));
        userService.save(user);
    }

    @Transactional
    @DeleteMapping(path = "/phone")
    public void delete(@RequestParam Long phoneId,
                    @RequestParam Long userId) {

        var user = userService.findById(userId);

        var phoneForDelete = user.getPhones().stream()
                .filter(phoneData -> phoneData.getId().equals(phoneId))
                .findFirst()
                .orElseThrow();

        user.getPhones().remove(phoneForDelete);

        userService.save(user);
    }
}
