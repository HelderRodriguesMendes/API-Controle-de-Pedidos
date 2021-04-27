package com.teste.api_pedidos.service;

import com.teste.api_pedidos.entidades.Cliente;
import com.teste.api_pedidos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id){
        Optional<Cliente> cli = clienteRepository.findById(id);
        return cli.get();
    }

    public Cliente getCliente_CPF(String cpf){
        return clienteRepository.getCliente_CPF(cpf);
    }

    public List<Cliente> getCliente_Nome(String nome){
        return clienteRepository.getCliente_Nome(nome).get();
    }

    public List<Cliente> getClientes(){
        return clienteRepository.getClientes().get();
    }

    public void excluir(Long id){
        clienteRepository.excluir(id);
    }
}
