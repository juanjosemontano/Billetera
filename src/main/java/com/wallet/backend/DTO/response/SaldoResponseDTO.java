package com.wallet.backend.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SaldoResponseDTO {
    private BigDecimal saldo;
    private String nombres;
}