package com.teste.api_pedidos.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "pedido")
@EqualsAndHashCode(of = { "id" })
public class Pedido implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "pedido_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private Double totalCompra;

    @Column(nullable = false)
    private LocalDate dataCompra;

    @Column(nullable = false)
    private Boolean habilitado;

    @OneToMany(mappedBy = "pedido")
    private List<Produto> produtos = new ArrayList<>();

    public Pedido(){}
}

