/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import system.model.resources.services.DBservices.PontoService;
import system.infrastructure.exceptions.advice.PontoControllerException;

/**
 * @author eric
 */
@RestController
@RequestMapping("pontos")
@PontoControllerException
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping("{matricula}")
    public ResponseEntity<String> registrarPonto(@PathVariable String matricula) {
        try {

            Integer matriculaInt = Integer.parseInt(matricula);

            String mensagem = this.pontoService.registrarPonto(matriculaInt);
            if (mensagem != null) {
                return ResponseEntity.ok(mensagem);
            }

            return ResponseEntity.noContent().build();
        } catch (NumberFormatException | MethodArgumentTypeMismatchException e) {
            /*A excecao lancada pelo Spring de tentativa de conversao de uma String para um inteiro
             é NumberFormatException Exemplo ocorrido nessa linha Integer matriculaInt = Integer.parseInt(matricula);
             */
            throw new NumberFormatException("Insira o numero da sua matricula.");
        }


    }
}