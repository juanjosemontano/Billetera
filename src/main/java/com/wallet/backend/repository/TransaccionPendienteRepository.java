package com.wallet.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.backend.entity.EstadoTransaccion;
import com.wallet.backend.entity.TransaccionPendiente;

public interface TransaccionPendienteRepository extends JpaRepository<TransaccionPendiente, Long> {
    
    Optional<TransaccionPendiente> findBySessionIdAndEstado(String sessionId, EstadoTransaccion estado);

    Optional<TransaccionPendiente> findBySessionId(String sessionId);
}
