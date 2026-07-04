package com.wallet.backend.exception;

public class TokenInvalidoException extends WalletException{

    public TokenInvalidoException() {
        super("04", "Token invalido o expirado");
    }
}
