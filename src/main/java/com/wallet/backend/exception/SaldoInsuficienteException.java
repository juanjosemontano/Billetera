package com.wallet.backend.exception;

public class SaldoInsuficienteException extends WalletException {

    public SaldoInsuficienteException() {
        super("03", "Saldo insuficiente para realizar la operacion");
    }
}
