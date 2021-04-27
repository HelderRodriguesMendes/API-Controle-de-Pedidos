package com.teste.api_pedidos.controller;

import com.teste.api_pedidos.entidades.Produto;
import com.teste.api_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    //cadastra um produto
    @PostMapping(value = "/cadastrar", produces = "application/json")
    public ResponseEntity<Produto>cadastrar(@RequestBody Produto produto){
        return new ResponseEntity<Produto>(produtoService.cadastrar(produto), HttpStatus.CREATED);
    }

    //pesquisa um produto por nome
    @GetMapping("/getProduto_Nome")
    public ResponseEntity<List<Produto>> getProduto_Nome(@RequestParam String nome){
        return new ResponseEntity<List<Produto>>(produtoService.getProduto_Nome(nome), HttpStatus.OK);
    }

    //lista todos os produtos ativos
    @GetMapping("/getProdutos")
    public ResponseEntity<List<Produto>> getProdutos(){
        return new ResponseEntity<List<Produto>>(produtoService.getProdutos(), HttpStatus.OK);
    }

    //lista todos os produtos ativos e em estoque
    @GetMapping("/getProdutosEmEstoque")
    public ResponseEntity<List<Produto>> getProdutosEmEstoque(){
        return new ResponseEntity<List<Produto>>(produtoService.getProdutosEmEstoque(), HttpStatus.OK);
    }

    //altera um produto
    @PutMapping(value = "/alterar/{id}", produces = "application/json")
    public ResponseEntity<Produto>alterar(@RequestBody Produto produto, @PathVariable("id") Long id){
        produto.setId(id);
        return new ResponseEntity<Produto>(produtoService.cadastrar(produto), HttpStatus.CREATED);
    }

    //exclui-desabilita um produto
    @DeleteMapping(value = "/excluir/{id}", produces = "application/json")
    public ResponseEntity<Void>excluir( @PathVariable("id") Long id){
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
