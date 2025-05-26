package com.Pedido.Pedido.dto.internaldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el carrito de compras de un usuario antes de confirmar el pedido.
 * No se guarda en base de datos, es una estructura temporal en memoria.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarritoDTO {

    private Long idUsuario;  // Usuario que está armando el carrito

    private List<PedidoItemDTO> items = new ArrayList<>();  // Lista de productos seleccionados con sus cantidades

}
