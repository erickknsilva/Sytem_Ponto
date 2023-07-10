/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.resources;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.FuncionarioRepository;
import system.model.repositorys.PontoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import system.model.entity.Departamento;
import system.model.repositorys.DepartamentoRepository;

@RequiredArgsConstructor
@Service
public class PontoServiceTest {

    private final FuncionarioRepository funcRepository;
    private final PontoRepository pontoRepository;
    private final DepartamentoRepository depRepository;

    @Bean
    public void instanciarFuncionario() {
        List<Funcionario> listaFuncionario = new ArrayList<>();
        List<Departamento> listaDepartamento = new ArrayList<>();

        listaDepartamento.add(new Departamento("Administracao", "adm2ksoft@gmail.com",
                "(11)98823-3217", "Erick"));
        listaDepartamento.add(new Departamento("Recurso Humanos", "rh2ksoft@gmail.com",
                "(11)98867-7667", "Amauri"));

        listaDepartamento.add(new Departamento("Contabilidade", "contabil2ksoft@gmail.com",
                "(11)98867-7667", "Erick"));

        depRepository.saveAll(listaDepartamento);

        listaFuncionario.add(new Funcionario("Erick", "Silva", new BigDecimal("3343.23"),
                8, 44, 160, "Mensalista",
                LocalDate.of(2023, 06, 02), depRepository.getReferenceById(1)));

        listaFuncionario.add(new Funcionario("Amauri", "Ferreira", new BigDecimal("3543.23"),
                8, 44, 160, "Mensalista", LocalDate.now(), depRepository.getReferenceById(1)));

        funcRepository.saveAll(listaFuncionario);
    }

    @Bean
    public void baterPonto() {

        LocalDateTime hora = LocalDateTime.now();
        LocalTime horaEntrada = LocalTime.of(8, hora.getMinute());
        LocalTime horaSaida = LocalTime.of(17, hora.getMinute());

        LocalTime horasTrabalhada = calcularHoraTrabalhada(horaEntrada, horaSaida);

        Funcionario funcionario = funcRepository.findByMatricula(1);

        Ponto ponto = criarPonto(horaEntrada, horaSaida, funcionario, horasTrabalhada);

        pontoRepository.save(ponto);
    }

    private Ponto criarPonto(LocalTime entrada, LocalTime saida,
            Funcionario funcionario, LocalTime horasTrabalhada) {

        Ponto ponto = new Ponto();

        ponto.setData(LocalDate.of(2023, 06, 12));
        ponto.setHoraEntrada(entrada);
        ponto.setHoraSaida(saida);
        ponto.setDiaTrabalhado(true);
        ponto.setFuncionario(funcionario);
        ponto.setNomeFuncionario(funcionario.getNome().concat(" " + funcionario.getSobrenome()));
        ponto.setHorasTrabalhada(horasTrabalhada);

        return ponto;
    }

    private LocalTime calcularHoraTrabalhada(LocalTime horaEntrada, LocalTime horaSaida) {
        return horaSaida.minusHours(horaEntrada.getHour())
                .minusMinutes(horaEntrada.getMinute());
    }

}
