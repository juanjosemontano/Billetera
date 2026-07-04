package com.wallet.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void enviarTokenConfirmacion(String destinatario, String token) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Código de confirmación de pago");
        mensaje.setText("Su código de confirmación es: " + token +
                ". Este código expira en 15 minutos.");

        mailSender.send(mensaje);
        log.info("Token de confirmación enviado a {}", destinatario);
    }
}