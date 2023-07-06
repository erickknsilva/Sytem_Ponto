/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.resources.services.DBservices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import system.model.contract.ContratoFuncionario;
import system.model.contract.FuncionarioHorista;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.PontoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author eric
 */
@RequiredArgsConstructor
@Service
public class PontoService {

    private final FuncionarioService funcionarioService;
    private final FuncionarioHorista funcionarioHorista;
    private final PontoRepository pontoRepository;

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

        if (funcionario != null && funcionario.getTipoContrato().equalsIgnoreCase("mensalista")) {
            ContratoFuncionario contrato = this.funcionarioHorista;
            Ponto pontoAnterior = pontoRepository.findFirstByFuncionarioOrderByDataDesc(funcionario);

            if (seAbrirPonto(pontoAnterior)) {
                return abrirPonto(contrato, matricula, funcionario, pontoAnterior);
            } else if (seFecharPonto(pontoAnterior)) {
                return "Não é possível bater o ponto novamente no mesmo dia após fechá-lo.";
            } else {
                return fecharPonto(contrato, matricula, funcionario, pontoAnterior);
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

    private String abrirPonto(ContratoFuncionario contrato, Integer matricula,
                              Funcionario funcionario, Ponto pontoAnterior) {

        Ponto ponto = contrato.abrirPonto(matricula);
        if (ponto != null) {
            return "Ponto registrado com sucesso.";
        }
        return null;

    }

    /**
     * Fecha o ponto do funcionário, calcula o salário diário e acumulado do mês.
     *
     * @param contrato      O contrato do funcionário
     * @param matricula     A matrícula do funcionário
     * @param funcionario   O objeto do funcionário
     * @param pontoAnterior O ponto anterior do funcionário
     * @return Uma mensagem de sucesso ou null se o ponto fechado não for encontrado.
     * @author Erick Nunes da Silva
     */
    private String fecharPonto(ContratoFuncionario contrato, Integer matricula, Funcionario funcionario, Ponto pontoAnterior) {

        Ponto pontoFechado = contrato.fecharPonto(matricula);

        if (pontoFechado != null) {

            // Calcula o salário diário do ponto fechado
            pontoFechado.setSalarioDia(contrato.calcularSalarioPorDia(funcionario.getSalario(),
                    funcionario.getCargaMensal(), pontoAnterior.getHorasTrabalhada()));

            // Calcula o salário acumulado do mês
            BigDecimal salarioMesAcumulado = calcularSalarioMesAcumulado(funcionario, pontoFechado);

            // Calcula o novo salário acumulado do mês
            BigDecimal novoSalarioMes = salarioMesAcumulado.add(pontoFechado.getSalarioDia());

            //pega o salario do dia e adiciona na soma do salario acumulado do mês
            pontoFechado.setSalarioMes(novoSalarioMes);
            pontoRepository.save(pontoFechado);
            return "Ponto fechado com sucesso.";
        }
        return null;
    }

    /**
     * Calcula o valor acumulado do salário do mês para um funcionário horista, excluindo o ponto fechado.
     * A lógica do método calcularSalarioMesAcumulado é iterar sobre todos os pontos anteriores do funcionário,
     * somando os valores dos salários diários desses pontos. O ponto fechado = ponto do dia não entra nesse calculo.
     *
     * @param funcionario  O funcionário para o qual o cálculo está sendo feito.
     * @param pontoFechado O ponto fechado que será excluído do cálculo.
     * @return O valor acumulado do salário do mês para o funcionário.
     * @author Erick Nunes da Silva
     */
    private BigDecimal calcularSalarioMesAcumulado(Funcionario funcionario, Ponto pontoFechado) {
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