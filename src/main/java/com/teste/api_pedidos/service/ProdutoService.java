package com.teste.api_pedidos.service;

import com.teste.api_pedidos.entidades.Produto;
import com.teste.api_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    List<Produto> PRODUTOS;

    public Produto cadastrar(Produto produto){
        return produtoRepository.save(produto);
    }

    /*
    * atualiza a quantidade dos produtos em estoque, subtraindo a quantidade
    * atual cadastrada com a quantidade que esta no pedido
    * */
    public void atualizarEstoque(List<Produto> produtosDeUmPedido){
        for(Produto p : produtosDeUmPedido){
            Optional<Produto> produtoCadastrado = produtoRepository.findById(p.getId());
            produtoRepository.atualizarEstoque(produtoCadastrado.get().getQuantidade() - p.getQuantidade(), produtoCadastrado.get().getId());
        }
    }

    public List<Produto> getProdutosEmEstoque(){
        return produtoRepository.getProdutosEmEstoque().get();
    }

    public List<Produto> getProduto_Nome(String nome){
        return produtoRepository.getProduto_Nome(nome).get();
    }

    public List<Produto> getProdutos(){
        return produtoRepository.getProdutos().get();
    }

    public void excluir(Long id){
        produtoRepository.excluir(id);
    }
}
