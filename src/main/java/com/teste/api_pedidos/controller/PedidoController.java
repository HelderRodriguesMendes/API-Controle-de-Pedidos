package com.teste.api_pedidos.controller;

import com.teste.api_pedidos.entidades.Pedido;
import com.teste.api_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    //cadastra um pedido completo e atualiza o estoque de produtos
    @PostMapping(value = "/cadastrar", produces = "application/json")
    public ResponseEntity<Pedido>cadastrar(@RequestBody Pedido pedido){
        return new ResponseEntity<Pedido>(pedidoService.cadastrar(pedido), HttpStatus.CREATED);
    }

    //pesquisa um pedido pelo cpf do cliente
    @GetMapping("/getPedido_CPF")
    public ResponseEntity<List<Pedido>> getPedido_CPF(@RequestParam String cpf){
        return new ResponseEntity<List<Pedido>>(pedidoService.getPedido_CPF(cpf), HttpStatus.OK);
    }

    //remove um cliente do pedido
    @PutMapping("/removerCliente/{id}")
    public ResponseEntity<Void> removerCliente(@PathVariable("id") Long idCliente){
        pedidoService.removerCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    //remove um produto do pedido
    @PutMapping("/removerProduto")
    public ResponseEntity<Void> removerProduto(@RequestParam Long idProduto, @RequestParam Long idPedido){
        pedidoService.removerProduto(idProduto, idPedido);
        return ResponseEntity.noContent().build();
    }

    //pesquisa um pedido pela data da compra
    @GetMapping("/getPedido_dataCompra")
    public ResponseEntity<List<Pedido>> getPedido_dataCompra(@RequestParam String data){
        return new ResponseEntity<List<Pedido>>(pedidoService.getPedido_dataCompra(data), HttpStatus.OK);
    }

    //lista todos os pedidos disponiveis
    @GetMapping("/getPedidos")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return new ResponseEntity<List<Pedido>>(pedidoService.getPedidos(), HttpStatus.OK);
    }

    //altera um pedido
    @PutMapping("/alterar/{id}")
    public ResponseEntity<Pedido>alterar(@RequestBody Pedido pedido, @PathVariable("id") Long id){
        pedido.setId(id);
        return new ResponseEntity<Pedido>(pedidoService.alterar(pedido), HttpStatus.OK);
    }

    //exclui - desabilita um pedido
    @DeleteMapping(value = "/excluir/{id}", produces = "application/json")
    public ResponseEntity<Void>excluir( @PathVariable("id") Long id){
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
