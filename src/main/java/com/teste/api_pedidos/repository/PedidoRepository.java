package com.teste.api_pedidos.repository;

import com.teste.api_pedidos.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional
    @Query(value = "select * from pedido where habilitado = true and cliente_id = ?1", nativeQuery = true)
    Optional<List<Pedido>> getPedido_idCli(Long idCli);

    @Transactional
    @Modifying
    @Query(value = "update pedido set cliente_id = null where habilitado = true and pedido_id = ?1", nativeQuery = true)
    public void removerCliente(Long idPedido);

    @Transactional
    @Modifying
    @Query(value = "delete from item_pedidopk where habilitado = true and produto_id = ?1 and pedido_id = ?2", nativeQuery = true)
    public void removerProduto(Long idProduto, Long idPedido);

    @Transactional
    @Query(value = "select * from pedido where habilitado = true and data_compra = ?1", nativeQuery = true)
    public Optional<List<Pedido>> getPedido_dataCompra(LocalDate dataCompra);

    @Transactional
    @Query(value = "select * from pedido where habilitado = true", nativeQuery = true)
    public Optional<List<Pedido>> getPedidos();

    @Transactional
    @Modifying
    @Query(value = "update pedido set habilitado = false where pedido_id = ?1 ", nativeQuery = true)
    public void excluir(Long id);
}
