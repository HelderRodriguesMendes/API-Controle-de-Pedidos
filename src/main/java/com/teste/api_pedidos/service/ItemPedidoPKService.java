package com.teste.api_pedidos.service;

import com.teste.api_pedidos.entidades.ItemPedidoPK;
import com.teste.api_pedidos.entidades.Produto;
import com.teste.api_pedidos.repository.ItemPedidoPKRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoPKService {

    @Autowired
    ItemPedidoPKRepository itemPedidoPKRepository;

    public List<Produto> getProdutos_pedido(Long idPedido){
        List<Produto> PRODUTOS = new ArrayList<>();
        Optional<List<ItemPedidoPK>> itemPedidos = itemPedidoPKRepository.getProdutos_pedido(idPedido);
        itemPedidos.get().stream().forEach(p ->{
            PRODUTOS.add(p.getProduto());
        });
        return PRODUTOS;
    }
}
