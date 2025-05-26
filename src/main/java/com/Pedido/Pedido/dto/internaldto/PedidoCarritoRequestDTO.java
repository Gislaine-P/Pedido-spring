package com.Pedido.Pedido.dto.internaldto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la solicitud para crear un pedido.
 * Incluye el comprador, opcionalmente el vendedor, el tipo de pedido y la lista de productos con cantidades.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCarritoRequestDTO {

    private Long idUsuarioComprador;  // Siempre requerido, el que compra
    private Long idUsuarioVendedor;   // Solo requerido si es pedido presencial (puede ser null si online)
    private boolean presencial;       // Indica si es presencial o online
    private List<PedidoItemDTO> items; // Lista de productos y cantidades a comprar

}
