package com.example.demo.api;

import com.example.demo.dao.entity.EmailData;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final UserService userService;

    @Transactional
    @PutMapping(path = "/email")
    public void add(@RequestParam String email,
                    @RequestParam Long userId) {
        var user = userService.findById(userId);

        user.getEmails().add(new EmailData(null, user, email));
        userService.save(user);
    }

    @Transactional
    @DeleteMapping(path = "/email")
    public void delete(@RequestParam Long emailId,
                       @RequestParam Long userId) {

        var user = userService.findById(userId);

        var emailForDelete = user.getEmails().stream()
                .filter(emailData -> emailData.getId().equals(emailId))
                .findFirst()
                .orElseThrow();

        user.getEmails().remove(emailForDelete);

        userService.save(user);
    }
}
