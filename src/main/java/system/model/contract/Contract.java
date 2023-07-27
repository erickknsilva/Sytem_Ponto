/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.contract;

import java.math.BigDecimal;
import java.time.LocalTime;

import system.model.entity.Ponto;

/**
 * @author eric
 */
public interface Contract {

    public abstract BigDecimal calcularSalarioPorDia(BigDecimal salario, int cargaMensal, LocalTime horasTrabalhadas);

    public abstract Ponto abrirPonto(Integer matricula);

    public abstract Ponto fecharPonto(Integer matricula);

}
