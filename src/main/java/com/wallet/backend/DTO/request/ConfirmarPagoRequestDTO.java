package com.wallet.backend.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmarPagoRequestDTO {

    @NotBlank(message = "El sessionId es obligatorio")
    private String sessionId;

    @NotBlank(message = "El token es obligatorio")
    private String token;
}