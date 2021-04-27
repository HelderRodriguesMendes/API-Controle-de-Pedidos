package com.teste.api_pedidos.controller;

import com.teste.api_pedidos.entidades.Cliente;
import com.teste.api_pedidos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //cadastra um cliente
    @PostMapping(value = "/cadastrar", produces = "application/json")
    public ResponseEntity<Cliente>cadastrar(@RequestBody Cliente cliente){
        return new ResponseEntity<Cliente>( clienteService.cadastrar(cliente), HttpStatus.CREATED);
    }

    //pesquisa um cliente por CPF
    @GetMapping("/getCliente_CPF")
    public ResponseEntity<Cliente> getCliente_CPF(@RequestParam String cpf){
        return new ResponseEntity<Cliente>( clienteService.getCliente_CPF(cpf), HttpStatus.OK);
    }

    //pesquisa um cliente por nome
    @GetMapping("/getCliente_Nome")
    public ResponseEntity<List<Cliente>> getCliente_Nome(@RequestParam String nome){
        return new ResponseEntity<List<Cliente>>(clienteService.getCliente_Nome(nome), HttpStatus.OK);
    }

    //lista todos os cliente cadastrados e ativos
    @GetMapping("/getClientes")
    public ResponseEntity<List<Cliente>> getClientes(){
        return new ResponseEntity<List<Cliente>>(clienteService.getClientes(), HttpStatus.OK);
    }

    //altera um cliente
    @PutMapping(value = "/alterar/{id}", produces = "application/json")
    public ResponseEntity<Cliente>alterar(@RequestBody Cliente cliente, @PathVariable("id") Long id){
        cliente.setId(id);
        return new ResponseEntity<Cliente>( clienteService.cadastrar(cliente), HttpStatus.CREATED);
    }

    //exclui-desabilita um cliente
    @DeleteMapping(value = "/excluir/{id}", produces = "application/json")
    public ResponseEntity<Void>excluir( @PathVariable("id") Long id){
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
