package com.Pedido.Pedido.dto.internaldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Detalle de un producto dentro del pedido, con precio y subtotal.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {

    private Long idProducto;
    private double precioProducto;
    private int cantidadProducto;
    private double subtotal;

}
