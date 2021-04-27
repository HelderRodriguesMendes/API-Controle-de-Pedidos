package com.teste.api_pedidos.repository;

import com.teste.api_pedidos.entidades.Cliente;
import com.teste.api_pedidos.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Transactional
    @Query(value = "select * from cliente where cpf = ?1 and habilitado = true", nativeQuery = true)
    public Cliente getCliente_CPF(String cpf);

    @Transactional
    @Query(value = "select * from cliente where habilitado = true and nome like %?1% order by nome limit 100", nativeQuery = true)
    public Optional<List<Cliente>> getCliente_Nome(String nome);

    @Transactional
    @Query(value = "select * from cliente where habilitado = true", nativeQuery = true)
    public Optional<List<Cliente>> getClientes();

    @Transactional
    @Modifying
    @Query(value = "update cliente set habilitado = false where cliente_id = ?1 ", nativeQuery = true)
    public void excluir(Long id);
}
