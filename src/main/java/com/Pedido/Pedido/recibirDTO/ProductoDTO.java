package com.Pedido.Pedido.recibirDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok genera los metodos get y set
@AllArgsConstructor //Lombok genera el constructor con todos los atributos
@NoArgsConstructor //Lombok genera el constructor sin atributos

//Clase
public class ProductoDTO {

    private Long idProducto;
    private String categoriaProducto;
    private String nombreProducto;
    private double precio;
    private int stock;
}
