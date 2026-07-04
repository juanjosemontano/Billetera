package com.wallet.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.wallet.backend.entity.Billetera;

import jakarta.persistence.LockModeType;

public interface BilleteraRepository extends JpaRepository<Billetera, Long> {
    
    Optional<Billetera> findByClienteId(Long clienteId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT b FROM Billetera b WHERE b.cliente.id = :clienteId")
    Optional<Billetera> findByClienteIdWithLock(Long clienteId);
}
