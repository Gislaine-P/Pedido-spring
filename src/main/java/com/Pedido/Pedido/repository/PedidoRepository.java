package com.Pedido.Pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //Lista de pedido que ha realizado un cliente (tal vez)
    List <Pedido> findByIdUsuarioComprador(Long idUsuarioComprador);

    //Lista para buscar pedido por estado
    List <Pedido> findByEstadoPedido(PedidoEstado estadoPedido);

}