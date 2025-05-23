package com.Pedido.Pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pedido.Pedido.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by status, date range, etc.
    
}