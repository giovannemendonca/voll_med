package com.mendonca.voll_med.domain.model;

import com.mendonca.voll_med.dtos.input.AtualizacaoMedico;
import com.mendonca.voll_med.dtos.input.CadastroMedico;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "MEDICOS")
public class Medico {

    @EqualsAndHashCode.Include
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CRM")
    private String crm;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(CadastroMedico cadastroMedico) {
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.crm = cadastroMedico.crm();
        this.ativo = true;
        this.telefone = cadastroMedico.telefone();
        this.especialidade = cadastroMedico.especialidade();
        this.endereco = new Endereco(cadastroMedico.endereco());
    }

    public void atualizar(AtualizacaoMedico dto) {
        if (dto.nome() != null)
            this.nome = dto.nome();
        if (dto.email() != null)
            this.email = dto.email();
        if (dto.telefone() != null)
            this.telefone = dto.telefone();
        if (dto.dadosEndereco() != null)
            this.endereco.atualizar(dto.dadosEndereco());
    }

    public void inativar() {
        this.ativo = false;
    }
}
