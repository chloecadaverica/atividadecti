package com.javanauta.atividadecti.controller;

import com.javanauta.atividadecti.infrastructure.entitys.Produto;
import com.javanauta.atividadecti.infrastructure.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // Criar
    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    // Ler
    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    // Atualizar
    @PutMapping
    public Produto atualizar(@RequestBody Produto produto) {
        if (produto.getId() != null && repository.existsById(produto.getId())) {
            return repository.save(produto);
        }
        return null;
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}