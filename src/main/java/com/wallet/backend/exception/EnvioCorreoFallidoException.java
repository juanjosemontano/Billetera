package com.wallet.backend.exception;

public class EnvioCorreoFallidoException extends WalletException {

    public EnvioCorreoFallidoException() {
        super("99", "No se pudo enviar el correo electrónico, por favor intente nuevamente");
    }
    
}
