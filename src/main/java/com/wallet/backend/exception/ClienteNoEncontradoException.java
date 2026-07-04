package com.wallet.backend.exception;

public class ClienteNoEncontradoException extends WalletException {

    public ClienteNoEncontradoException() {
        super("02", "Cliente no encontrado");
    }
}
