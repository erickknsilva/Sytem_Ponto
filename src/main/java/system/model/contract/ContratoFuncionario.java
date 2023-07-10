/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.contract;

import java.math.BigDecimal;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;

/**
 * @author eric
 */
@Getter
@Setter
@NoArgsConstructor
@Component
@Service
public abstract class ContratoFuncionario {

    private Funcionario funcionario;

    public ContratoFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public abstract BigDecimal calcularSalarioPorDia(BigDecimal salario, int cargaMensal, LocalTime horasTrabalhadas);

    public abstract Ponto abrirPonto(Integer matricula);

    public abstract Ponto fecharPonto(Integer matricula);


}
