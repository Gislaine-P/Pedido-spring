package com.Pedido.Pedido.dto.internaldto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para responder con la información del pedido creado o consultado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PedidoResponseDTO {

    private Long idPedido;
    private LocalDateTime fechaCreacion;
    private double total;
    private String estadoPedido; // Puede ser String para flexibilidad o enum si prefieres
    private List<DetallePedidoDTO> detalles;

}
