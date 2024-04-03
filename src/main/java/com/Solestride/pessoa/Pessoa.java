package com.Solestride.pessoa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", allocationSize = 1)
    @Column(name = "ID_PESSOA")
    private Long id;

    @NotBlank
    @Column(name = "NM_PESSOA" ,nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "CNPJ_PESSOA", nullable = false)
    private String cnpj;

    @NotBlank
    @Column(name = "EMAIL_PESSOA", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "SENHA_PESSOA", nullable = false)
    private String senha;

    @NotBlank
    @Column(name = "MOD_TRIBUTARIO", nullable = false)
    private String modTributario;

    @NotBlank
    @Column(name = "TP_PESSOA", nullable = false)
    private String tipo;

    public Pessoa(String tipo) {
        this.tipo = tipo;
    }
}