package com.Pedido.Pedido.dto.shareddto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok para generar los métodos getters y setters
@AllArgsConstructor //Lombok para generar el constructor con todos los atributos
@NoArgsConstructor //Lombok para generar el constructor sin atributos

//Clase de VentaDTO para transferir los datos de la venta al servicio de Venta
public class VentaDTO {
    private Long idPedido; //Id del pedido que se ha realizado
    private String usuarioComprador; //Comprador
    private String usuarioVendedor; //Tal vez, solo si es que puede implementarse
    private List<DetallePedidoDTO> detallePedido; //Lista de detalles del pedido
    private double total; //Total de la venta
    private Date fechaVenta; //Fecha de la venta

}
