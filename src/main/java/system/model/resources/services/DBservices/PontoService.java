/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.resources.services.DBservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import system.model.contract.Employee;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.PontoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author eric
 */
@Service
public class PontoService {

    private final FuncionarioService funcionarioService;
    private final Employee employee;
    private final PontoRepository pontoRepository;

    public PontoService(FuncionarioService funcionarioService, Employee employee, PontoRepository pontoRepository) {
        this.funcionarioService = funcionarioService;
        this.employee = employee;
        this.pontoRepository = pontoRepository;
    }

    public Ponto findById(Integer id) {
        return pontoRepository.findById(id).orElse(null);
    }

    public Ponto save(@RequestBody Ponto ponto) {

        if (ponto != null) {
            return this.pontoRepository.save(ponto);
        }
        return null;
    }

    public Ponto update(Integer id, @RequestBody Ponto ponto) {

        Ponto pontoUpdate = this.findById(id);

        if (pontoUpdate != null) {
            pontoUpdate.setData(ponto.getData());
            pontoUpdate.setHoraEntrada(ponto.getHoraSaida());
            pontoUpdate.setHoraSaida(ponto.getHoraSaida());
            pontoUpdate.setDiaTrabalhado(true);
            pontoUpdate.setHorasTrabalhada(ponto.getHorasTrabalhada());
            pontoUpdate.setNomeFuncionario(ponto.getNomeFuncionario());
            this.pontoRepository.save(pontoUpdate);
        }

        return null;
    }

    public String registrarPonto(Integer matricula) {

        Funcionario funcionario = funcionarioService.findById(matricula);

        if (funcionario != null) {
//            ContratoFuncionario contrato = this.employee;
            Ponto pontoAnterior = pontoRepository.findFirstByFuncionarioOrderByDataDesc(funcionario);

            if (seAbrirPonto(pontoAnterior)) {
                return abrirPonto(this.employee, matricula, funcionario);
            } else if (seFecharPonto(pontoAnterior)) {
                return "Não é possível bater o ponto novamente,\nno mesmo dia após fechá-lo.";
            } else {
                return fecharPonto(this.employee, matricula, funcionario, pontoAnterior);
            }
        }
        return null;

    }

    private boolean seAbrirPonto(Ponto pontoAnterior) {
        return pontoAnterior == null || pontoAnterior.getData().isBefore(LocalDate.now());
    }

    private boolean seFecharPonto(Ponto pontoAnterior) {
        return pontoAnterior.getHoraSaida() != null && pontoAnterior.getData().isEqual(LocalDate.now());
    }

    private String abrirPonto(Employee employee, Integer matricula,
                              Funcionario funcionario) {

        Ponto ponto = employee.abrirPonto(matricula);
        if (ponto != null) {
            employee.mensagemEmailAbrirPonto(funcionario, ponto);
            return "Ponto registrado com sucesso.";
        }
        return null;

    }

    /**
     * Fecha o ponto do funcionário, calcula o salário diário e acumulado do
     * mês.
     *
     * @param employee      O contrato do funcionário
     * @param matricula     A matrícula do funcionário
     * @param funcionario   O objeto do funcionário
     * @param pontoAnterior O ponto anterior do funcionário
     * @return Uma mensagem de sucesso ou null se o ponto fechado não for
     * encontrado.
     * @author Erick Nunes da Silva
     */
    private String fecharPonto(Employee employee, Integer matricula, system.model.entity.Funcionario funcionario, Ponto pontoAnterior) {
        Ponto pontoFechado = employee.fecharPonto(matricula);

        if (pontoFechado != null) {
            // Verifica se o funcionário é do tipo "horista"
            if (funcionario.getTipoContrato().equalsIgnoreCase("horista")) {
                // Calcula o salário diário do ponto fechado
                pontoFechado.setSalarioDia(employee.calcularSalarioPorDia(funcionario.getSalario(),
                        funcionario.getCargaMensal(), pontoAnterior.getHorasTrabalhada()));

                // Calcula o salário acumulado do mês
                BigDecimal salarioMesAcumulado = calcularSalarioMesAcumulado(funcionario, pontoFechado);

                // Calcula o novo salário acumulado do mês
                BigDecimal novoSalarioMes = salarioMesAcumulado.add(pontoFechado.getSalarioDia());

                // Atualiza o salário acumulado do mês
                pontoFechado.setSalarioMes(novoSalarioMes);
            } else {
                // Para funcionários do tipo "mensalista", apenas insere a hora de entrada e saída
                pontoFechado.setHoraEntrada(pontoAnterior.getHoraEntrada());
                pontoFechado.setHoraSaida(pontoAnterior.getHoraSaida());
            }

            pontoRepository.save(pontoFechado);
            this.employee.mensagemEmailFecharPonto(funcionario, pontoFechado);
            return "Ponto fechado com sucesso.";
        }
        return null;
    }

    /**
     * Calcula o valor acumulado do salário do mês para um funcionário horista,
     * excluindo o ponto fechado. A lógica do método calcularSalarioMesAcumulado
     * é iterar sobre todos os pontos anteriores do funcionário, somando os
     * valores dos salários diários desses pontos. O ponto fechado = ponto do
     * dia não entra nesse calculo.
     *
     * @param funcionario  O funcionário para o qual o cálculo está sendo feito.
     * @param pontoFechado O ponto fechado que será excluído do cálculo.
     * @return O valor acumulado do salário do mês para o funcionário.
     * @author Erick Nunes da Silva
     */
    private BigDecimal calcularSalarioMesAcumulado(system.model.entity.Funcionario funcionario, Ponto pontoFechado) {
        //recuperar uma lista de pontos anteriores relacionados a um determinado funcionário, ordenados por data.
        List<Ponto> pontosAnteriores = pontoRepository.findByFuncionarioOrderByData(funcionario);

        BigDecimal salarioMesAcumulado = BigDecimal.ZERO;
        for (Ponto ponto : pontosAnteriores) {
            // Verifica se o ponto é o pontoFechado
            if (pontoFechado != null && ponto.getId().equals(pontoFechado.getId())) {
                continue; // Pular o pontoFechado
            }

            // Verifica se o salarioDia é nulo e insere o valor zero
            BigDecimal salarioDia = ponto.getSalarioDia() != null ? ponto.getSalarioDia() : BigDecimal.ZERO;

            salarioMesAcumulado = salarioMesAcumulado.add(salarioDia);
        }

        return salarioMesAcumulado;
    }

}
