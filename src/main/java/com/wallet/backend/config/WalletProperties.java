package com.wallet.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wallet.transaccion")
public class WalletProperties {
    private int minutosExpiracion = 15;
}