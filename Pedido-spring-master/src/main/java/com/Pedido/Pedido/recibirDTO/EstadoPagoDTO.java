package com.Pedido.Pedido.recibirDTO;

import com.Pedido.Pedido.enums.PedidoEstado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//ClaseDTO para recibir el estado del pago de un pedido y validar el estado del pedido
public class EstadoPagoDTO {
    private Long idPedido;
    private PedidoEstado estadoPedido; // Estado del pedido, puede ser PENDIENTE, PAGADO, CANCELADO

}
