package com.example.demo.service;

import com.example.demo.dao.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceUpdaterService {

    private final AccountRepository accountRepository;
    private final JdbcTemplate jdbcTemplate;


    @Scheduled(fixedRate = 30000) // every 30 second
    public void updateBalances() {
        accountRepository.increaseBalanceByTenPercent();
    }
}
