package com.wallet.backend.service;

import com.wallet.backend.config.WalletProperties;
import com.wallet.backend.DTO.request.*;
import com.wallet.backend.DTO.response.*;
import com.wallet.backend.entity.*;
import com.wallet.backend.exception.*;
import com.wallet.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class BilleteraService {

    private final ClienteRepository clienteRepository;
    private final BilleteraRepository billeteraRepository;
    private final TransaccionPendienteRepository transaccionRepository;
    private final TokenGenerator tokenGenerator;
    private final MailService mailService;
    private final WalletProperties walletProperties;

    @Transactional
    public void recargar(RecargarBilleteraRequestDTO request) {
        Cliente cliente = obtenerClienteValido(request.getDocumento(), request.getCelular());
        Billetera billetera = obtenerBilletera(cliente);

        billetera.setSaldo(billetera.getSaldo().add(request.getValor()));
        billeteraRepository.save(billetera);

        log.info("Recarga aplicada: documento={}, valor={}",
                cliente.getDocumento(), request.getValor());
    }

    @Transactional
    public PagoIniciadoResponseDTO iniciarPago(IniciarPagoRequestDTO request) {
        Cliente cliente = obtenerClienteValido(request.getDocumento(), request.getCelular());
        Billetera billetera = obtenerBilletera(cliente);

        if (billetera.getSaldo().compareTo(request.getValor()) < 0) {
            throw new SaldoInsuficienteException();
        }

        String token = tokenGenerator.generarToken();
        String sessionId = tokenGenerator.generarSessionId();

        TransaccionPendiente transaccion = new TransaccionPendiente();
        transaccion.setSessionId(sessionId);
        transaccion.setToken(token);
        transaccion.setValor(request.getValor());
        transaccion.setCliente(cliente);
        transaccion.setEstado(EstadoTransaccion.PENDIENTE);
        transaccion.setExpiracion(
                LocalDateTime.now().plusMinutes(walletProperties.getMinutosExpiracion()));

        transaccionRepository.save(transaccion);

        try {
            mailService.enviarTokenConfirmacion(cliente.getEmail(), token);
        } catch (MailException ex) {
            log.error("No se pudo enviar el correo de confirmación a {}", cliente.getEmail(), ex);
            throw new EnvioCorreoFallidoException();
        }

        log.info("Pago iniciado: sessionId={}, documento={}", sessionId, cliente.getDocumento());

        return new PagoIniciadoResponseDTO(sessionId,
                "Se ha enviado un código de confirmación a su email");
    }

    @Transactional
    public void confirmarPago(ConfirmarPagoRequestDTO request) {
        TransaccionPendiente transaccion = transaccionRepository
                .findBySessionIdAndEstado(request.getSessionId(), EstadoTransaccion.PENDIENTE)
                .orElseThrow(TokenInvalidoException::new);

        if (transaccion.isExpirada()) {
            transaccion.setEstado(EstadoTransaccion.EXPIRADA);
            transaccionRepository.save(transaccion);
            throw new TransaccionExpiradaException();
        }

        if (!transaccion.getToken().equals(request.getToken())) {
            throw new TokenInvalidoException();
        }

        Billetera billetera = obtenerBilletera(transaccion.getCliente());

        if (billetera.getSaldo().compareTo(transaccion.getValor()) < 0) {
            throw new SaldoInsuficienteException();
        }

        billetera.setSaldo(billetera.getSaldo().subtract(transaccion.getValor()));
        billeteraRepository.save(billetera);

        transaccion.setEstado(EstadoTransaccion.COMPLETADA);
        transaccionRepository.save(transaccion);

        log.info("Pago confirmado: sessionId={}", transaccion.getSessionId());
    }

    @Transactional(readOnly = true)
    public SaldoResponseDTO consultarSaldo(ConsultarSaldoRequestDTO request) {
        Cliente cliente = obtenerClienteValido(request.getDocumento(), request.getCelular());
        Billetera billetera = obtenerBilletera(cliente);

        return new SaldoResponseDTO(billetera.getSaldo(), cliente.getNombres());
    }

    private Cliente obtenerClienteValido(String documento, String celular) {
        return clienteRepository.findByDocumentoAndCelular(documento, celular)
                .orElseThrow(ClienteNoEncontradoException::new);
    }

    private Billetera obtenerBilletera(Cliente cliente) {
        return billeteraRepository.findByClienteId(cliente.getId())
                .orElseThrow(ClienteNoEncontradoException::new);
    }
}