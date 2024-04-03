package com.Solestride.requisicao;

import com.Solestride.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
