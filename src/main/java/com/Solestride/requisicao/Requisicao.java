package com.Solestride.requisicao;

import com.Solestride.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_REQUISICAO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COD_REQUISICAO", columnNames = "COD_REQUISICAO")
})
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REQUISICAO")
    @Column(name = "ID_REQUISICAO")
    private Long id;

    @NotBlank
    @Column(name = "COD_REQUISICAO", nullable = false)
    private String codRequisicao;

    @NotNull
    @Positive
    @Column(name = "QTD_REQUISICAO", nullable = false)
    private Integer quantidade;

    @NotNull
    @Positive
    @Column(name = "TETO_REQUISICAO", nullable = false)
    private BigDecimal tetoAutomatico;

    @Column(name = "DT_REQUISICAO", nullable = false)
    private LocalDate data;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_REQ_PRODUTO",
            referencedColumnName = "ID_PRODUTO",
            foreignKey = @ForeignKey(name = "FK_REQUISICAO_PRODUTO"),
            nullable = false
    )
    private Produto produto;
}