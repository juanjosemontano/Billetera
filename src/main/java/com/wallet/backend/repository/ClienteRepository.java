package com.wallet.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.backend.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    boolean existsByDocumento(String documento);

    Optional<Cliente> findByDocumentoAndCelular(String documento, String celular);
}
