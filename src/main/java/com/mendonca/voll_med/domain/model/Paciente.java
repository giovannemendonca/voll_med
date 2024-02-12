package com.mendonca.voll_med.domain.model;

import com.mendonca.voll_med.dtos.input.AtualizacaoPaciente;
import com.mendonca.voll_med.dtos.input.CadastroPaciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "PACIENTE")
public class Paciente {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Column(name = "ENDERECO")
    @Embedded
    private Endereco endereco;

    public Paciente(CadastroPaciente paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.cpf = paciente.cpf();
        this.ativo = true;
        this.endereco = new Endereco(paciente.endereco());
    }

    public void atualizar(AtualizacaoPaciente paciente) {
        if (paciente.nome() != null)
            this.nome = paciente.nome();
        if (paciente.telefone() != null)
            this.telefone = paciente.telefone();
        if (paciente.endereco() != null)
            this.endereco = new Endereco(paciente.endereco());
    }


    public void inativar() {
        this.ativo = false;
    }
}
