/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import system.infrastructure.exceptions.ValidationException;
import system.infrastructure.exceptions.advice.FuncionarioControllerException;
import system.model.entity.Funcionario;
import system.model.resources.services.DBservices.FuncionarioService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@FuncionarioControllerException
@RestController
@RequestMapping("funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {

        List<Funcionario> listaFuncionarios = this.funcionarioService.findAll();
        if (listaFuncionarios != null && !listaFuncionarios.isEmpty()) {
            return ResponseEntity.ok(listaFuncionarios);
        }
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<Funcionario>> findAll(@PathVariable int page,
                                                     @PathVariable int size) {

        Page<Funcionario> listaFuncionarios = this.funcionarioService.findAll(page, size);
        if (listaFuncionarios != null && !listaFuncionarios.isEmpty()) {
            return ResponseEntity.ok(listaFuncionarios);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer matricula) {
        Funcionario funcionario = this.funcionarioService.findById(matricula);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {

        if (funcionario != null) {
            funcionario = this.funcionarioService.save(funcionario);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(funcionario.getMatricula()).toUri();
            return ResponseEntity.created(uri).body(funcionario);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/excluir/{matricula}")
    public ResponseEntity<String> delete(@PathVariable Integer matricula) {

        String mensagem = this.funcionarioService.delete(matricula);
        if (mensagem != null) {
            return ResponseEntity.ok(mensagem);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/atualizar/{matricula}")
    public ResponseEntity<Funcionario> update(@PathVariable Integer matricula,
                                              @RequestBody Funcionario funcionario) {
        try {
            Funcionario funcUpdate = this.funcionarioService.update(matricula, funcionario);
            if (funcUpdate != null) {
                return ResponseEntity.ok(funcUpdate);
            }
            return ResponseEntity.noContent().build();
        } catch (ConstraintViolationException ex) {
            List<String> errorMessages = new ArrayList<>();

            for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                String errorMessage = violation.getMessage();
                errorMessages.add(errorMessage);
            }

            throw new ValidationException(errorMessages);
        }
    }

}
