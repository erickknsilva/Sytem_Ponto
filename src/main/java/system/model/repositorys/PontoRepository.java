/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.model.repositorys;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;

/**
 *
 * @author eric
 */
@Repository
@EnableJpaRepositories
public interface PontoRepository extends JpaRepository<Ponto, Integer> {

    Ponto findLastByFuncionarioOrderByDataDesc(Funcionario funcionario);

    Ponto findFirstByFuncionarioOrderByDataDesc(Funcionario funcionario);

    Ponto findLastByFuncionario(Funcionario funcionario);

    List<Ponto> findByFuncionarioAndData(Funcionario funcionario, LocalDate pontoAnterior);

    List<Ponto> findByFuncionarioAndDataBefore(Funcionario funcionario, LocalDate pontoAnterior);

    List<Ponto> findByFuncionario(Funcionario funcionario);

    List<Ponto> findByFuncionarioOrderByData(Funcionario funcionario);

}
