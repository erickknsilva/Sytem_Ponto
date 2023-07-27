/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.resources.services.ponto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.FuncionarioRepository;
import system.model.repositorys.PontoRepository;

/**
 * @author erick Nunes da Silva
 */
//@RequiredArgsConstructor
//@Service
public class RegistryPontoService {

//    private final FuncionarioRepository funcRepository;
//    private final PontoRepository pontoRepository;
//
//    private Ponto salvarPonto(LocalTime entrada, LocalTime saida,
//                              Funcionario funcionario, LocalTime horasTrabalhada) {
//
//        Ponto ponto = new Ponto();
//
//        ponto.setData(LocalDate.now());
//        ponto.setHoraEntrada(entrada);
//        ponto.setHoraSaida(saida);
//        ponto.setDiaTrabalhado(true);
//        ponto.setFuncionario(funcionario);
//        ponto.setNomeFuncionario(funcionario.getNome().concat(" " + funcionario.getSobrenome()));
//        ponto.setHorasTrabalhada(horasTrabalhada);
//
//        return pontoRepository.save(ponto);
//
//    }
//
//    private LocalTime calcularHoraTrabalhada(LocalTime horaEntrada, LocalTime horaSaida) {
//        return horaSaida.minusHours(horaEntrada.getHour())
//                .minusMinutes(horaEntrada.getMinute());
//    }
//
//    public Ponto abrirPonto(Integer matricula) {
//
//        LocalDateTime hora = LocalDateTime.now();
//        Funcionario funcionario = funcRepository.findByMatricula(matricula);
//
//        return pontoRepository.save(salvarPonto(LocalTime.of(8, hora.getMinute()),
//                null, funcionario, null));
//    }
//
//    public Ponto fecharPonto(Integer matricula) {
//
//        // Recupera o primeiro ponto registrado para o funcionário
//        Funcionario funcionario = this.funcRepository.findByMatricula(matricula);
//        if (funcionario != null) {
//            Ponto pontoAnterior = this.pontoRepository.findFirstByFuncionarioOrderByDataDesc(funcionario);
//
//            if (pontoAnterior != null && pontoAnterior.getHoraSaida() == null) {
//
//                atualizarPonto(pontoAnterior, LocalTime.of(17, LocalTime.now().getMinute()));
//                return this.pontoRepository.save(pontoAnterior);
//            }
//        }
//        return null;
//    }
//
//    private void atualizarPonto(Ponto ponto, LocalTime horaSaida) {
//        ponto.setHoraSaida(horaSaida);
//        ponto.setHorasTrabalhada(calcularHoraTrabalhada(ponto.getHoraEntrada(), horaSaida));
//    }


}
