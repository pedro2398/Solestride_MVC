package com.Solestride.fornecedor;

import com.Solestride.pessoa.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_FORNECEDOR")
@DiscriminatorValue("FORNECEDOR")
public class Fornecedor extends Pessoa {
    public Fornecedor() {
        super("FORNECEDOR");
    }
    public Fornecedor(Long id, String nome, String cnpj, String email, String senha, String modTributario) {
        super(id, nome, cnpj, email, senha, modTributario, "FORNECEDOR");
    }

    @Override
    public String toString() {
        return "Fornecedor{}" + super.toString();
    }
}