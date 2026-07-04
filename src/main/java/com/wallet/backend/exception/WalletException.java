package com.wallet.backend.exception;

import lombok.Getter;

@Getter
public abstract class WalletException extends RuntimeException {

    private final String codError;

    protected WalletException(String codError, String message) {
        super(message);
        this.codError = codError;
    }

}