package com.Solestride.comprador;

import com.Solestride.pessoa.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_COMPRADOR")
@DiscriminatorValue("COMPRADOR")
public class Comprador extends Pessoa {
    public Comprador() {
        super("COMPRADOR");
    }
    public Comprador(Long id, String nome, String cnpj, String email, String senha, String modTributario) {
        super(id, nome, cnpj, email, senha, modTributario, "COMPRADOR");
    }

    @Override
    public String toString() {
        return "Comprador{}" + super.toString();
    }
}