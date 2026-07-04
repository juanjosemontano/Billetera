package com.wallet.backend.service;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
    
    private final SecureRandom random = new SecureRandom();

    public String generarToken() {
        int numero = random.nextInt(1_000_000);
        return String.format("%06d", numero);
    }

    public String generarSessionId() {
        return UUID.randomUUID().toString();
    }
}
