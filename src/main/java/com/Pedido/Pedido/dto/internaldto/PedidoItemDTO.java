
package com.Pedido.Pedido.dto.internaldto;

//import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un producto con la cantidad que se quiere agregar al pedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemDTO {

    private Long idProducto;
    private int cantidad;

}
