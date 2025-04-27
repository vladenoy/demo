package com.example.demo.dto;

import com.example.demo.dao.entity.Account;
import com.example.demo.dao.entity.EmailData;
import com.example.demo.dao.entity.PhoneData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDTO {
    Long id;
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate dateOfBirth;
    Account account;
    List<PhoneData> phones;
    List<EmailData> emails;
}
