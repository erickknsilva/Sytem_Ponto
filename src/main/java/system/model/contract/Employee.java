/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.contract;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.infrastructure.email.EmailService;
import system.model.entity.Funcionario;
import system.model.entity.Ponto;
import system.model.repositorys.FuncionarioRepository;
import system.model.repositorys.PontoRepository;

/**
 * @author eric
 */
@Service
public class Employee implements Contract {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private EmailService emailService;


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

        LocalDateTime hora = LocalDateTime.now();
        system.model.entity.Funcionario funcionario = funcRepository.findByMatricula(matricula);

        return pontoRepository.save(salvarPonto(LocalTime.of(8, hora.getMinute()),
                null, funcionario, null));
    }

    @Override
    public Ponto fecharPonto(Integer matricula) {
        // Recupera o primeiro ponto registrado para o funcionário
        system.model.entity.Funcionario funcionario = this.funcRepository.findByMatricula(matricula);
        if (funcionario != null) {
            Ponto pontoAnterior = this.pontoRepository.findFirstByFuncionarioOrderByDataDesc(funcionario);

            if (pontoAnterior != null && pontoAnterior.getHoraSaida() == null) {

                atualizarPonto(pontoAnterior, LocalTime.of(17, LocalTime.now().getMinute()));
                return this.pontoRepository.save(pontoAnterior);
            }
        }
        return null;
    }

    private void atualizarPonto(Ponto ponto, LocalTime horaSaida) {
        ponto.setHoraSaida(horaSaida);
        ponto.setHorasTrabalhada(calcularHoraTrabalhada(ponto.getHoraEntrada(), horaSaida));
    }

    private Ponto salvarPonto(LocalTime entrada, LocalTime saida,
                              system.model.entity.Funcionario funcionario, LocalTime horasTrabalhada) {

        Ponto ponto = new Ponto();

        ponto.setData(LocalDate.now());
        ponto.setHoraEntrada(entrada);
        ponto.setHoraSaida(saida);
        ponto.setDiaTrabalhado(true);
        ponto.setFuncionario(funcionario);
        ponto.setNomeFuncionario(funcionario.getNome().concat(" " + funcionario.getSobrenome()));
        ponto.setHorasTrabalhada(horasTrabalhada);

        return pontoRepository.save(ponto);

    }

    private LocalTime calcularHoraTrabalhada(LocalTime horaEntrada, LocalTime horaSaida) {
        return horaSaida.minusHours(horaEntrada.getHour())
                .minusMinutes(horaEntrada.getMinute());
    }

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
