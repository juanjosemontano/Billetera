package com.wallet.backend.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteResponseDTO {
    private String documento;
    private String nombres;
    private String email;
}