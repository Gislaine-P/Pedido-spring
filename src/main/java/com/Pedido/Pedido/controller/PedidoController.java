package com.Pedido.Pedido.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.recibirDTO.EstadoPagoDTO;
import com.Pedido.Pedido.service.PedidoService;
import com.Pedido.Pedido.transferirDTO.VentaDTO;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }


    //ResponseEntity es una clase que representa una respuesta HTTP incluyendo el cuerp los encabezados y el estado de la respuesta

// VENDEDOR: registra un pedido (presencial, incluye idUsuarioVendedor y idUsuarioComprador)
    @PostMapping("/vendedor/registrar")
    public ResponseEntity<Pedido> registrarPedidoDesdeVendedor(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
}

    @GetMapping("/usuario/{idUsuarioComprador}")
    public List<Pedido> obtenerPedidosPorUsuario(@PathVariable Long idUsuarioComprador) {
        return pedidoService.obtenerPedidosPorUsuario(idUsuarioComprador);
}

// COMPRADOR: registra un pedido (online, solo se incluye idUsuarioComprador)
    @PostMapping("/comprador/registrar")
    public ResponseEntity<Pedido> registrarPedidoDesdeComprador(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
}
    //OBTENER PEDIDO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return (pedido != null)
                ? ResponseEntity.ok(pedido)  //ResponseEntity con Status OK y el pedido encontrado
                : ResponseEntity.notFound().build(); //ResponseEntity con Status NOT FOUND si no se encuentra el pedido
    }

    //OBTENER PEDIDO POR ESTADO
    @GetMapping("/estadoPedido/{estado}")
    public List<Pedido> obtenerPedidosPorEstado(@PathVariable PedidoEstado estado) {
        return pedidoService.obtenerPedidosPorEstado(estado);
    }

    //CONTAR PEDIDOS POR ESTADO
    @GetMapping("/cantidadPedido/{estado}")
    public long contarPedidosPorEstado(@PathVariable PedidoEstado estado) {
        return pedidoService.contarPedidosPorEstado(estado);
    }


        //OBTENER TODOS LOS PEDIDOS
    @GetMapping("/pedidosGenerados")
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

        @PutMapping("/actualizar-estado")
    public VentaDTO actualizarEstado(@RequestBody EstadoPagoDTO estadoDTO) { 
        return pedidoService.actualizarEstadoPedido(estadoDTO);
}




    

}
