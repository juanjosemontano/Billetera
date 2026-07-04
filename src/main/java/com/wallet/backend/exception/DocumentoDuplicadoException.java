package com.wallet.backend.exception;

public class DocumentoDuplicadoException extends WalletException {

    public DocumentoDuplicadoException() {
        super("01", "El documento ya se encuentra registrado");
    }
}
