package com.teste.api_pedidos.repository;

import com.teste.api_pedidos.entidades.ItemPedidoPK;
import com.teste.api_pedidos.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPedidoPKRepository extends JpaRepository<ItemPedidoPK, Long> {
    @Transactional
    @Query(value = "select * from item_pedidopk where pedido_id = ?1", nativeQuery = true)
    public Optional<List<ItemPedidoPK>> getProdutos_pedido(Long idPedido);
}
