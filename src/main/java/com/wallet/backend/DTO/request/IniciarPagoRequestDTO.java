package com.wallet.backend.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IniciarPagoRequestDTO {

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "El celular es obligatorio")
    private String celular;

    @NotNull(message = "El valor es obligatorio")
    @Positive(message = "El valor debe ser mayor a 0")
    private BigDecimal valor;
}