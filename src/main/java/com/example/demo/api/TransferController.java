package com.example.demo.api;

import com.example.demo.security.JwtUtil;
import com.example.demo.service.TransferService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;
    private final JwtUtil jwtUtil;

    @PatchMapping(path = "/transfer")
    public String send(@RequestHeader(name="Authorization") String token,
                       @RequestParam Long recipientId,
                       @RequestParam @Positive BigDecimal value) {

        var senderId = Long.parseLong(
                jwtUtil.getUserIdFromToken(
                        token.substring(7)));

        return transferService.send(senderId, recipientId, value);
    }
}
