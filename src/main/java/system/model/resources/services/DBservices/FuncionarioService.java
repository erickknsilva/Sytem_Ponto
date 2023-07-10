/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.resources.services.DBservices;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import system.model.entity.Funcionario;
import system.model.repositorys.FuncionarioRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.model.entity.Departamento;

@RequiredArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        List<Funcionario> listaFuncionarios = this.funcionarioRepository.findAll();
        if (!listaFuncionarios.isEmpty()) {
            return listaFuncionarios;
        }
        return null;
    }

    public Funcionario findById(Integer matricula) {

        return funcionarioRepository.findById(matricula).orElse(null);

    }

    public Funcionario save(@Valid Funcionario funcionario) {

        if (funcionario != null) {
            return this.funcionarioRepository.save(funcionario);
        }
        return null;
    }

    public Funcionario update(Integer id, @RequestBody @Valid Funcionario funcionario) {
        Funcionario funcUpdate = this.findById(id);

        if (funcUpdate != null) {
            funcUpdate.setNome(funcionario.getNome());
            funcUpdate.setSobrenome(funcionario.getSobrenome());
            funcUpdate.setSalario(funcionario.getSalario());
            funcUpdate.setCargaDiaria(funcionario.getCargaDiaria());
            funcUpdate.setCargaSemanal(funcionario.getCargaSemanal());
            funcUpdate.setCargaMensal(funcionario.getCargaMensal());
            funcUpdate.setTipoContrato(funcionario.getTipoContrato());
            funcUpdate.setDataEntrada(funcionario.getDataEntrada());
            funcUpdate.setDepartamento(funcionario.getDepartamento());
            return this.funcionarioRepository.save(funcUpdate);
        }
        return null;
    }

    @Transactional
    public String delete(Integer matricula) {

        Funcionario funcionario = this.findById(matricula);
        if (funcionario != null) {

            Departamento departamento = funcionario.getDepartamento();
            departamento.getFuncionario().remove(funcionario);

            funcionarioRepository.delete(funcionario);
            return funcionario.toString() + ", excluido com sucesso.";
        }
        return null;
    }

}
