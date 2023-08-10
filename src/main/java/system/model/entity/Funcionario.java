/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Integer matricula;

    @NotBlank(message = "Insira o primeiro nome do funcionario.")
    @Length(min = 4, max = 30, message = "O primeiro nome deve estar entre {min} e {max} caractere.")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Insira o sobrenome nome do funcionario.")
    @Length(min = 4, max = 60, message = "O sobrenome nome deve estar entre {min} e {max} caractere.")
    @Column(name = "sobrenome")
    private String sobrenome;

    @Email
    @NotBlank(message = "Insira um email valido exemplo teste@gmail.com")
    @Length(min = 4, max = 80, message = "O email deve estar entre {min} e {max} caractere.")
    @Column(name = "email")
    private String email;

    @Column(name = "salario")
    @Range(min = 5, message = "O valor minimo para cadastrar é {min} reais")
    @Digits(integer = 6, fraction = 2, message = "Apenas centenas e 2 casas após o ponto.")
    @NotNull(message = "Insira o salario do funcionario")
    @Digits(integer = 6, fraction = 2, message = "Apenas centenas e 2 casas após o ponto. Exemplo 3234.54")
    private BigDecimal salario;

    @NotNull(message = "Insira a carga horaria diaria.")
    @Column(name = "carga_diaria")
    private LocalTime cargaDiaria;

    @Range(min = 0, message = "O valor minimo para cadastrar a carga semanal é {min} ")
    @Column(name = "carga_semanal")
    private Integer cargaSemanal;

    @Column(name = "carga_mensal")
    private Integer cargaMensal;

    @Length(min = 7, max = 10, message = "O valor deve estar entre {min} e {max} caracteres.")
    @NotBlank(message = "Insira o tipo de contrato do funcionario.")
    @Column(name = "tipoContrato")
    private String tipoContrato;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @NotNull(message = "Insira o departamento do funcionario.")
    @ManyToOne
    @JoinColumn(name = "idDep")
    private Departamento departamento;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ponto> ponto;

    public Funcionario(Integer matricula) {
        this.matricula = matricula;
    }

    public Funcionario(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank String email,
                       @NotNull BigDecimal salario, @NotNull LocalTime cargaDiaria, @NotNull Integer cargaSemanal,
                       @NotNull Integer cargaMensal, @NotNull String tipoContrato, @NotNull LocalDate dataEntrada,
                       @NotNull Departamento departamento) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.salario = salario;
        this.cargaDiaria = cargaDiaria;
        this.cargaSemanal = cargaSemanal;
        this.cargaMensal = cargaMensal;
        this.tipoContrato = tipoContrato;
        this.dataEntrada = dataEntrada;
        this.departamento = departamento;
    }

    public Funcionario(@Valid Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.salario = funcionario.getSalario();
        this.cargaDiaria = funcionario.getCargaDiaria();
        this.cargaSemanal = funcionario.getCargaSemanal();
        this.cargaMensal = funcionario.getCargaMensal();
        this.tipoContrato = funcionario.getTipoContrato();
        this.departamento = funcionario.getDepartamento();

    }

    @Override
    public String toString() {
        return "Funcionario: " + "matricula: " + matricula
                + ", nome: " + nome.concat(" " + sobrenome)
                + ",\ndata entrada: " + dataEntrada + ", " + "departamento: "
                + departamento.getNome();
    }
}


