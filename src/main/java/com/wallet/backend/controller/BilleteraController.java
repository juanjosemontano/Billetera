package com.wallet.backend.controller;

import com.wallet.backend.DTO.request.*;
import com.wallet.backend.DTO.response.*;
import com.wallet.backend.service.BilleteraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billetera")
@RequiredArgsConstructor
@Validated
@Tag(name = "Billetera", description = "Operaciones sobre la billetera virtual")
public class BilleteraController {

    private final BilleteraService billeteraService;

    @PostMapping("/recargar")
    @Operation(summary = "Recargar saldo a la billetera")
    public ResponseEntity<ApiResponse<Void>> recargar(
            @Valid @RequestBody RecargarBilleteraRequestDTO request) {

        billeteraService.recargar(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Recarga realizada exitosamente", null));
    }

    @PostMapping("/pagar")
    @Operation(summary = "Iniciar una compra (envía token de confirmación por email)")
    public ResponseEntity<ApiResponse<PagoIniciadoResponseDTO>> pagar(
            @Valid @RequestBody IniciarPagoRequestDTO request) {

        PagoIniciadoResponseDTO response = billeteraService.iniciarPago(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(response));
    }

    @PostMapping("/confirmar-pago")
    @Operation(summary = "Confirmar el pago con el token recibido por email")
    public ResponseEntity<ApiResponse<Void>> confirmarPago(
            @Valid @RequestBody ConfirmarPagoRequestDTO request) {

        billeteraService.confirmarPago(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok("Pago confirmado exitosamente", null));
    }

    @GetMapping("/saldo")
    @Operation(summary = "Consultar el saldo actual de la billetera")
    public ResponseEntity<ApiResponse<SaldoResponseDTO>> consultarSaldo(
            @RequestParam @NotBlank(message = "El documento es obligatorio")
            @Parameter(description = "Documento del cliente", example = "123456789")
            String documento,

            @RequestParam @NotBlank(message = "El celular es obligatorio")
            @Parameter(description = "Celular del cliente", example = "3001234567")
            String celular) {

        ConsultarSaldoRequestDTO request = new ConsultarSaldoRequestDTO();
        request.setDocumento(documento);
        request.setCelular(celular);

        SaldoResponseDTO response = billeteraService.consultarSaldo(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(response));
    }
}