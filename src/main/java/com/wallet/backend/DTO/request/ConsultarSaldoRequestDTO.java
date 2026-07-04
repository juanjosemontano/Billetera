package com.wallet.backend.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultarSaldoRequestDTO {

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "El celular es obligatorio")
    private String celular;
}