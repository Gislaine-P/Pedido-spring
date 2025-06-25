package com.Pedido.Pedido.recibirDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String tipoUsuario;
}
