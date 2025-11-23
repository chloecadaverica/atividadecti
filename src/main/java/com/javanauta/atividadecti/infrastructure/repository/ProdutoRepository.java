package com.javanauta.atividadecti.infrastructure.repository;

import com.javanauta.atividadecti.infrastructure.entitys.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);

}