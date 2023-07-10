/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.controller;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.model.entity.Departamento;
import system.model.resources.services.DBservices.DepartamentoService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> findById(@PathVariable Integer id) {
        Departamento departamento = departamentoService.findById(id);
        if (departamento != null) {
            return ResponseEntity.ok().body(departamento);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Integer id, @RequestBody @Valid Departamento departamento) {

        Departamento update = departamentoService.update(id, departamento);
        if (update != null) {
            return ResponseEntity.ok(update);
        }

        return ResponseEntity.noContent().build();
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
