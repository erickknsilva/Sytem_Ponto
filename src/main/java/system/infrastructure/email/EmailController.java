/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.infrastructure.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eric
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("sendEmail")
    public String getEmail() {

        String to = "erickk.nunes100@gmail.com";
        String cc = "erick.nunessilva10@gmail.com";
        String subject = "Teste de email assincrono";
        String text = "Este é um e-mail de teste enviado pelo Spring Boot de forma assíncrona.";

        emailService.sendEmail(to, subject, text);
        return "E-mail sendo enviado de forma assíncrona!";

    }

}
