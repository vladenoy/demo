package com.example.demo.service;

import com.example.demo.config.TestContainerConfiguration;
import com.example.demo.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestContainerConfiguration.class)
class TransferServiceTest {

    @Autowired
    TransferService transferService;

    @Test
    void send_ok() {

        var result = transferService.send(1L, 2L, BigDecimal.ONE);

        assertThat(result).isEqualTo("sent");

    }

    @Test
    void sendNotEnoughBalance_thanError() {

        var result = assertThrows(CustomException.class, () -> {
            transferService.send(1L, 2L, BigDecimal.valueOf(500000));
        });

        assertThat(result.getMessage()).isEqualTo("Not enough balance");

    }
}