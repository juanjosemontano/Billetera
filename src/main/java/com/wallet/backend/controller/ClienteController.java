package com.wallet.backend.controller;

import com.wallet.backend.DTO.request.RegistrarClienteRequestDTO;
import com.wallet.backend.DTO.response.ApiResponse;
import com.wallet.backend.DTO.response.ClienteResponseDTO;
import com.wallet.backend.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Registro de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar un nuevo cliente")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> registrar(
            @Valid @RequestBody RegistrarClienteRequestDTO request) {

        ClienteResponseDTO response = clienteService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(response));
    }
}
