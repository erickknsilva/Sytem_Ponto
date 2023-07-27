/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import system.infrastructure.exceptions.ValidationException;
import system.model.entity.Departamento;
import system.model.entity.Funcionario;
import system.model.resources.services.DBservices.DepartamentoService;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<Departamento>> findAll() {

        List<Departamento> listDepartamento = departamentoService.findAll();
        if (listDepartamento != null && !listDepartamento.isEmpty()) {

            return ResponseEntity.ok(listDepartamento);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<Departamento>> findAllPage(@PathVariable int page, @PathVariable int size) {

        Page<Departamento> departamentoPageable = this.departamentoService.findAll(page, size);

        if (departamentoPageable != null && !departamentoPageable.isEmpty()) {
            return ResponseEntity.ok(departamentoPageable);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> findById(@PathVariable Integer id) {
        Departamento departamento = departamentoService.findById(id);
        if (departamento != null) {
            return ResponseEntity.ok().body(departamento);
        }
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/atualizar/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Integer id, @RequestBody @Valid Departamento departamento) {
        try {
            Departamento update = departamentoService.update(id, departamento);
            if (update != null) {
                return ResponseEntity.ok(update);
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

    @PostMapping("/salvar")
    public ResponseEntity<Departamento> save(@RequestBody Departamento departamento) {

        if (departamento != null) {
            departamento = departamentoService.save(departamento);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(departamento.getId()).toUri();
            return ResponseEntity.created(uri).body(departamento);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        String mensagem = departamentoService.delete(id);

        if (mensagem != null) {
            return ResponseEntity.ok(mensagem);
        }

        return ResponseEntity.noContent().build();
    }


}
