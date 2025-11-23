package com.javanauta.atividadecti.business;

import com.javanauta.atividadecti.infrastructure.entitys.Produto;
import com.javanauta.atividadecti.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto salvaProduto(Produto produto) {
        try {
            if (repository.existsByNome(produto.getNome())) {
                throw new RuntimeException("Produto já cadastrado com este nome!");
            }
            return repository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public List<Produto> buscarTodosProdutos() {
        return repository.findAll();
    }

    public void deletarProduto(Long id) {
        repository.deleteById(id);
    }
}