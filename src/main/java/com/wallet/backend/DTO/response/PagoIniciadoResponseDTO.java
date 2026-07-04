package com.wallet.backend.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagoIniciadoResponseDTO {
    private String sessionId;
    private String mensaje;
}