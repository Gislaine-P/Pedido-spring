package com.Pedido.Pedido.dto.externaldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok genera los metodos get y set
@AllArgsConstructor //Lombok genera el constructor con todos los atributos
@NoArgsConstructor //Lombok genera el constructor sin atributos
//Clase
public class PagoResponseDTO {

    private Long idPedido;
    private boolean pagoConfirmado;
    private String metodoPago;

}
