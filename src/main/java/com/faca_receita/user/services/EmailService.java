package com.faca_receita.user.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(String to, String token) {
        String subject = "Confirmação de cadastro";
        String confirmationURL =  "http://localhost:8080/api/user/confirm?token=" + token;
        String message = "Bem-vindo ao Faça Receita! Clique no link para confirmar seu cadastro:\n" + confirmationURL;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);

        this.mailSender.send(email);
    }
}
