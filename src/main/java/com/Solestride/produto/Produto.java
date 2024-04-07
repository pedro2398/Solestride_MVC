package com.Solestride.produto;

import com.Solestride.fabricante.Fabricante;
import com.Solestride.fornecedor.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PRODUTO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_COD_PRODUTO", columnNames = "COD_PRODUTO")
})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @Column(name = "ID_PRODUTO")
    private Long id;

    @NotBlank
    @Column(name = "NM_NOME", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "COD_PRODUTO", nullable = false)
    private String codProduto;

    @NotBlank
    @Column(name = "DESC_PRODUTO", nullable = false)
    private String descricao;

    @NotBlank
    @Column(name = "MODELO_PRODUTO", nullable = false)
    private String modelo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "ID_FABRICANTE",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_FABRICANTE"),
            nullable = false

    )
    private Fabricante fabricante;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "ID_FORNECEDOR",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_FORNECEDOR"),
            nullable = false

    )
    private Fornecedor fornecedor;

    private BigDecimal valor;
}