/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author eric
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDEP")
    private Integer id;

    @NotBlank(message = "Insira o nome do departamento.")
    @Length(min = 4, max = 50, message = "O nome deve estar entre {min} e {max} caractere.\nExperimente abreviar.")
    @Column(name = "nome")
    private String nome;

    @Email
    @NotBlank(message = "Insira um email valido exemplo: teste@gmail.com")
    @Length(min = 4, max = 60, message = "O email deve estar entre {min} e {max} caractere.")
    @Column(name = "email")
    private String email;

    @Length(max = 14, message = "O telefone deve conter entre {min} e {max} numeros.")
    @Column(name = "telefone")
    private String telefone;

    @Length(max = 80, message = "O nome deve estar entre {min} e {max} caractere.\nExperimente abreviar.")
    @Column(name = "responsavel")
    private String responsavel;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Funcionario> funcionario;

    public Departamento(@NotBlank String nome, @NotBlank String email, String telefone, String responsavel) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.responsavel = responsavel;
    }

    public Departamento(@Valid Departamento departamento) {
        this.nome = departamento.getNome();
        this.email = departamento.getEmail();
        this.telefone = departamento.getTelefone();
        this.responsavel = departamento.getResponsavel();
    }

    public Departamento(Integer id) {
        this.id = id;
    }
}
