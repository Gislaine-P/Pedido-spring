package com.Pedido.Pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pedido.Pedido.model.DetallePedido;

@Repository // Indica que esta interfaz es un repositorio de Spring
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

}
