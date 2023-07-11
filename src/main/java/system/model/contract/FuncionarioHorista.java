/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.contract;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.PontoRepository;
import system.model.resources.services.ponto.RegistryPontoService;

/**
 * @author eric
 */
@Service
public class FuncionarioHorista extends ContratoFuncionario {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private RegistryPontoService registryPontoService;

    public FuncionarioHorista() {
    }

    public FuncionarioHorista(Funcionario funcionario) {
        super(funcionario);
    }

    @Override
    public BigDecimal calcularSalarioPorDia(BigDecimal salario, int cargaMensal, LocalTime horasTrabalhadas) {
        BigDecimal cargaHorariaMensal = new BigDecimal(cargaMensal);

        BigDecimal salarioPorHora = salario.divide(cargaHorariaMensal, MathContext.DECIMAL32)
                .setScale(2, RoundingMode.DOWN);
        System.out.println("salario por hora: " + salarioPorHora);

        BigDecimal minutosDecimal = new BigDecimal(horasTrabalhadas.getMinute()).divide(new BigDecimal(60), MathContext.DECIMAL32);
        BigDecimal horasTrabalhadasDecimal = new BigDecimal(horasTrabalhadas.getHour()).add(minutosDecimal);
        System.out.println("horas trabalhadas: " + horasTrabalhadasDecimal);

        BigDecimal salarioPorDia = salarioPorHora.multiply(horasTrabalhadasDecimal);

        return salarioPorDia.setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public Ponto abrirPonto(Integer matricula) {
        return this.registryPontoService.abrirPonto(matricula);
    }

    @Override
    public Ponto fecharPonto(Integer matricula) {
        return this.registryPontoService.fecharPonto(matricula);
    }

}
