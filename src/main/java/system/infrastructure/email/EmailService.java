/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.infrastructure.email;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("Ponto<coloque o email responsavel por enviar aqui>");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            this.emailSender.send(message);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MailException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

}
