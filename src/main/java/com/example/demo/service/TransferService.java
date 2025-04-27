package com.example.demo.service;

import com.example.demo.dao.repository.AccountRepository;
import com.example.demo.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String send(Long senderId, Long recipientId, BigDecimal value) {
        var senderAccount = accountRepository.findById(senderId).orElseThrow();
        var recipientAccount = accountRepository.findById(recipientId).orElseThrow();

        if (senderAccount.getBalance().subtract(value).compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomException("Not enough balance");
        }

        var newSenderBalance = senderAccount.getBalance().subtract(value);
        var newRecipientBalance = recipientAccount.getBalance().add(value);

        senderAccount.setBalance(newSenderBalance);
        recipientAccount.setBalance(newRecipientBalance);

        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);

        return "sent";
    }
}
