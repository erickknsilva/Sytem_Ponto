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

    private final PontoRepository pontoRepository;

    private final FuncionarioRepository funcRepository;

    private final EmailService emailService;

    public Employee(PontoRepository pontoRepository, FuncionarioRepository funcRepository, EmailService emailService) {
        this.pontoRepository = pontoRepository;
        this.funcRepository = funcRepository;
        this.emailService = emailService;
    }

    @Override
    public BigDecimal calcularSalarioPorDia(BigDecimal salario, int cargaMensal, LocalTime horasTrabalhadas) {
        BigDecimal cargaHorariaMensal = new BigDecimal(cargaMensal);

        BigDecimal salarioPorHora = salario
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

                atualizarPonto(pontoAnterior, LocalTime.of(16, LocalTime.now().getMinute()));
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

    public void mensagemEmailFecharPonto(Funcionario funcionario, Ponto ponto) {
        String mensagem = mensagemSaida(funcionario, ponto);

        this.emailService.sendEmail(funcionario.getEmail(), "Este é um email do registro do ponto",
                mensagem);
    }

    public String mensagemSaida(Funcionario funcionario, Ponto ponto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        BiFunction<Funcionario, Ponto, String> horaSaida = (func, pon) -> {

            if (func.getTipoContrato().equalsIgnoreCase("mensalista")) {
                String mensagem = "\nNome: " + func.getNome().concat(" " + func.getSobrenome())
                        + "\nTipo contrato: " + func.getTipoContrato()
                        + "\nDepartamento: " + func.getDepartamento().getNome()
                        + "\nHora saida: " + formatter.format(LocalTime.now())
                        + "\nData: " + pon.getData().format(formatterDate)
                        + "\n\nObrigado pelo seus serviços, bom descanso e até.";
                System.out.println(mensagem);
                return mensagem;
            }

            String mensagem = "Nome: " + func.getNome().concat(" " + func.getSobrenome())
                    + "\nDepartamento: " + func.getDepartamento().getNome()
                    + "\nTipo contrato: " + func.getTipoContrato()
                    + "\nHora saida: " + formatter.format(pon.getHoraSaida())
                    + "\nHora da entrada: " + pon.getHoraEntrada()
                    + "\nData: " + pon.getData().format(formatterDate)
                    + "\nSalario do dia: " + pon.getSalarioDia()
                    + "\nSalario do mes: R$" + pon.getSalarioMes() + ", até o momento."
                    + "\nQtd Horas trabalhada: " + pon.getHorasTrabalhada()
                    + "\n\nObrigado pelo seus serviços, bom descanso e até.";
            System.out.println(mensagem);
            return mensagem;
        };
        return horaSaida.apply(funcionario, ponto);
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
