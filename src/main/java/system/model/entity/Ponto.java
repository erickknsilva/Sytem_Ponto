package system.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity(name = "pontos")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_saida")
    private LocalTime horaSaida;

    @Column(name = "horas_trabalhada")
    private LocalTime horasTrabalhada;

    @Column(name = "dia_trabalhado")
    private boolean diaTrabalhado = false;

    @Column(name = "data_ponto")
    private LocalDate data;

    @Length(min = 4, max = 85, message = "O nome do responsavel deve estar entre {min} e {max} caracteres.\nExperimente abreaviar.")
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;

    @Range(min = 5, message = "O valor minimo para cadastrar é {min} reais")
    @Digits(integer = 6, fraction = 2, message = "Apenas centenas e 2 casas após o ponto.")
    @Column(name = "salario_dia")
    private BigDecimal salarioDia;

    @Range(min = 5, message = "O valor minimo para cadastrar é {min} reais")
    @Digits(integer = 6, fraction = 2, message = "Apenas centenas e 2 casas após o ponto.")
    @Column(name = "salario_mes")
    private BigDecimal salarioMes;

    @NotNull(message = "Insira o funcionario.")
    @ManyToOne
    @JoinColumn(name = "matricula")
    private Funcionario funcionario;

    public Ponto(Long id) {
        this.id = id;
    }

    public Ponto(LocalTime horaEntrada, LocalTime horaSaida, boolean diaTrabalhado,
            Funcionario funcionario, String nomeFuncionario)//
    {
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.diaTrabalhado = diaTrabalhado;
        this.data = LocalDate.now();
        this.funcionario = funcionario;
        this.setFuncionario(funcionario);
        this.setFuncionario(funcionario);
        this.nomeFuncionario = nomeFuncionario;
    }

}
