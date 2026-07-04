package com.wallet.backend.exception;

public class TransaccionExpiradaException extends WalletException{

    public TransaccionExpiradaException() {
        super("04","La transacion ha expirado, por favor inicie el pago nuevamente");
    }
    
}
