package com.wallet.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.backend.DTO.request.RegistrarClienteRequestDTO;
import com.wallet.backend.DTO.response.ClienteResponseDTO;
import com.wallet.backend.entity.Billetera;
import com.wallet.backend.entity.Cliente;
import com.wallet.backend.exception.DocumentoDuplicadoException;
import com.wallet.backend.repository.BilleteraRepository;
import com.wallet.backend.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    private final BilleteraRepository billeteraRepository;

    @Transactional
    public ClienteResponseDTO registrar(RegistrarClienteRequestDTO request) {
        if (clienteRepository.existsByDocumento(request.getDocumento())) {
            throw new DocumentoDuplicadoException();
        }

        Cliente cliente = Cliente.builder()
                .documento(request.getDocumento())
                .nombres(request.getNombres())
                .email(request.getEmail())
                .celular(request.getCelular())
                .build();

        cliente = clienteRepository.save(cliente);

        Billetera billetera = new Billetera();
        billetera.setCliente(cliente);
        billeteraRepository.save(billetera);

        log.info("Cliente registrado: documento={}", cliente.getDocumento());

        return new ClienteResponseDTO(cliente.getDocumento(), cliente.getNombres(), cliente.getEmail());
    }

}
