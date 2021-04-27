package com.teste.api_pedidos.repository;

import com.teste.api_pedidos.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional
    @Modifying
    @Query(value = "update produto set quantidade = ?1 where habilitado = true and produto_id = ?2", nativeQuery = true)
    public void atualizarEstoque(int estoqueAtual, Long id);

    @Transactional
    @Query(value = "select * from produto where habilitado = true and nome like %?1% order by nome limit 100", nativeQuery = true)
    public Optional<List<Produto>> getProduto_Nome(String nome);

    @Transactional
    @Query(value = "select * from produto where habilitado = true", nativeQuery = true)
    public Optional<List<Produto>> getProdutos();

    @Transactional
    @Modifying
    @Query(value = "update produto set habilitado = false where produto_id = ?1 ", nativeQuery = true)
    public void excluir(Long id);

    @Transactional
    @Query(value = "select * from produto where habilitado = true and quantidade > 0", nativeQuery = true)
    public Optional<List<Produto>> getProdutosEmEstoque();
}
