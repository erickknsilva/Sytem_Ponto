/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.contract;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import system.infrastructure.email.EmailService;
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

    @Autowired
    private EmailService emailService;

    public ContratoFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public abstract BigDecimal calcularSalarioPorDia(BigDecimal salario, int cargaMensal, LocalTime horasTrabalhadas);

    public abstract Ponto abrirPonto(Integer matricula);

    public abstract Ponto fecharPonto(Integer matricula);

    public void mensagemEmailAbrirPonto(Funcionario funcionario, Ponto ponto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String menssagem = "Nome: " + funcionario.getNome().concat(" " + funcionario.getSobrenome())
                + "\nDepartamento: " + funcionario.getDepartamento().getNome()
                + "\nTipo contrato: " + funcionario.getTipoContrato()
                + "\nHora da entrada: " + ponto.getHoraEntrada()
                + "\n" + calcularHoraAlmocoAndSaida(funcionario, ponto)
                + "\nData: " + ponto.getData().format(formatter);

        this.emailService.sendEmail(funcionario.getEmail(),
                "Este é um email do registro do ponto",
                menssagem);
    }

    public String calcularHoraAlmocoAndSaida(Funcionario funcionario, Ponto ponto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        BiFunction<Funcionario, Ponto, String> horaAlmocoAndSaida = (Funcionario func, Ponto pon) -> {
            if (func.getTipoContrato().equalsIgnoreCase("mensalista")) {

                //Calcula a hora da saida
                LocalTime saida = ponto.getHoraEntrada().
                        plusHours(func.getCargaDiaria().getHour());

                //Calcular o horario do almoço
                Duration duration = Duration.between(LocalTime.MIN,
                        ponto.getHoraEntrada());

                LocalTime almoco = ponto.getHoraEntrada().plus(duration.dividedBy(2));

                String horaAlmoçoAndSaida = "Horário de almoço: " + formatter.format(almoco)
                        + "\nRetorno almoço: " + formatter.format(almoco.plusHours(1))
                        + "\nHora de saida: " + saida;
                return horaAlmoçoAndSaida;
            }
            return "Funcionario horista, não contém horário de almoço e saida definida.";
        };
        return horaAlmocoAndSaida.apply(funcionario, ponto);
    }

}
