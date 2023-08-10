/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import system.model.resources.services.DBservices.PontoService;
import system.infrastructure.exceptions.advice.PontoControllerException;

/**
 * @author eric
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("pontos")
@PontoControllerException
public class PontoController {

    private final PontoService pontoService;

    public PontoController(PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @PostMapping
    public ResponseEntity<String> registrarPonto(@RequestBody Integer matricula) {

        try {
            Integer matriculaInt = Integer.valueOf(matricula);
            String mensagem = this.pontoService.registrarPonto(matriculaInt);

            if (mensagem != null) {
                return ResponseEntity.ok(mensagem);
            }

            return ResponseEntity.notFound().build();
        } catch (NumberFormatException | MethodArgumentTypeMismatchException e) {
            /*A excecao lancada pelo Spring de tentativa de conversao de uma String para um inteiro
             é NumberFormatException Exemplo ocorrido nessa linha Integer matriculaInt = Integer.parseInt(matricula);
             */
            throw new NumberFormatException("Insira o numero da sua matricula.");
        }

    }
}
