package com.Pedido.Pedido.dto.shareddto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok genera los metodos get y set
@AllArgsConstructor //Lombok genera el constructor con todos los atributos
@NoArgsConstructor // Lombok genera el constructor sin atributos

//Clase de PagoDTO enviar los datos de pago al servicio de Pago
public class PagoDTO {
    private Long idUsuario; // id del usuario que ha realizado el pago
    private double total; //total del pago
    private String metodoPago; //metodo de pago (tal vez se elimine)

}
