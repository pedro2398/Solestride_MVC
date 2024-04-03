package com.Solestride.fabricante;

import com.Solestride.pessoa.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_FABRICANTE")
@DiscriminatorValue("FABRICANTE")
public class Fabricante extends Pessoa {
    public Fabricante() {
        super("FABRICANTE");
    }

    public Fabricante(Long id, String nome, String cnpj, String email, String senha, String modTributario) {
        super(id, nome, cnpj, email, senha, modTributario, "FABRICANTE");
    }

    @Override
    public String toString() {
        return "Fabricante{}" + super.toString();
    }
}
