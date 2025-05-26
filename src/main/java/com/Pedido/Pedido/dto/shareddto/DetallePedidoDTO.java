package com.Pedido.Pedido.dto.shareddto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok para generar los métodos getters y setters
@AllArgsConstructor //Lombok para generar el constructor con todos los atributos
@NoArgsConstructor //Lombok para generar el constructor sin atributos

/**Clase DetallePedidoDTO que maneja datos internamente del microservicio Pedido
* el cual contiene los detalles de un producto en un pedido
**/
public class DetallePedidoDTO {
    
    private Long idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private int cantidadProducto;
    private double precioProducto;
    private double subtotal;

}
