package com.teste.api_pedidos.service;

import com.teste.api_pedidos.entidades.ItemPedidoPK;
import com.teste.api_pedidos.entidades.Pedido;
import com.teste.api_pedidos.repository.ItemPedidoPKRepository;
import com.teste.api_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ItemPedidoPKService itemPedidoPKService;

    @Autowired
    ItemPedidoPKRepository itemPedidoPKRepository;

    List<Pedido> PEDIDOS;

    public Pedido cadastrar(Pedido pedido) {

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        produtoService.atualizarEstoque(pedido.getProdutos());
        pedido.getProdutos().stream().forEach(p -> {
            ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
            itemPedidoPK.setProduto(p);
            itemPedidoPK.setPedido(pedidoSalvo);

            itemPedidoPKRepository.save(itemPedidoPK);
        });


        return montarJsonCompleto(pedidoSalvo);
    }

    public List<Pedido> getPedido_CPF(String cpf) {
        PEDIDOS = new ArrayList<>();
        List<Pedido> pedidosCliente = pedidoRepository.getPedido_idCli(clienteService.getCliente_CPF(cpf).getId()).get();
        pedidosCliente.stream().forEach(p -> {
            PEDIDOS.add(montarJsonCompleto(p));
        });
        return PEDIDOS;
    }

    public void removerCliente(Long idPedido) {
        pedidoRepository.removerCliente(idPedido);
    }

    public void removerProduto(Long idProduto, Long idPedido) {
        pedidoRepository.removerProduto(idProduto, idPedido);
    }

    public List<Pedido> getPedido_dataCompra(String data) {
        PEDIDOS = new ArrayList<>();
        LocalDate dataCompra = LocalDate.parse(data);
        List<Pedido> pedidos = pedidoRepository.getPedido_dataCompra(dataCompra).get();

        pedidos.stream().forEach(p -> {
            PEDIDOS.add(montarJsonCompleto(p));
        });
        return PEDIDOS;
    }

    public List<Pedido> getPedidos() {
        PEDIDOS = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepository.getPedidos().get();
        pedidos.stream().forEach(p -> {
            PEDIDOS.add(montarJsonCompleto(p));
        });
        return PEDIDOS;
    }

    public Pedido alterar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id){
        pedidoRepository.excluir(id);
    }

    //Busca os dados do cliente e os dados dos produtos que contem no pedido
    private Pedido montarJsonCompleto(Pedido p) {
        p.setCliente(clienteService.findById(p.getCliente().getId()));
        p.setProdutos(itemPedidoPKService.getProdutos_pedido(p.getId()));

        return p;
    }
}
