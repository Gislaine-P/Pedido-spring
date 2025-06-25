package com.Pedido.Pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.recibirDTO.EstadoPagoDTO;
import com.Pedido.Pedido.service.PedidoService;
import com.Pedido.Pedido.transferirDTO.VentaDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v6/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // VENDEDOR: registra un pedido
    @PostMapping("/vendedor/registrar")
    @Operation(summary = "Registrar un pedido desde vendedor", description = "Crea un pedido que registra el vendedor (presencial)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Pedido> registrarPedidoDesdeVendedor(
        @RequestBody(
            description = "Pedido a registrar (presencial)",
            required = true,
            content = @Content(schema = @Schema(implementation = Pedido.class))
        ) @org.springframework.web.bind.annotation.RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @PostMapping("/comprador/registrar")
    @Operation(summary = "Registrar un pedido desde comprador", description = "Crea un pedido que registra el comprador (online)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Pedido> registrarPedidoDesdeComprador(
        @RequestBody(
            description = "Pedido a registrar (online)",
            required = true,
            content = @Content(schema = @Schema(implementation = Pedido.class))
        ) @org.springframework.web.bind.annotation.RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un pedido por ID", description = "Devuelve un pedido específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content)
    })
    public ResponseEntity<Pedido> obtenerPedidoPorId(
        @Parameter(description = "ID del pedido a buscar", required = true)
        @PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return (pedido != null)
            ? ResponseEntity.ok(pedido)
            : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todos los pedidos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente")
    })
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    @GetMapping("/usuario/{idUsuarioComprador}")
    @Operation(summary = "Obtener pedidos por usuario", description = "Devuelve una lista de pedidos realizados por el cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos del usuario obtenidos exitosamente")
    })
    public List<Pedido> obtenerPedidosPorUsuario(
        @Parameter(description = "ID del usuario comprador", required = true)
        @PathVariable Long idUsuarioComprador) {
        return pedidoService.obtenerPedidosPorUsuario(idUsuarioComprador);
    }

    @GetMapping("/estadoPedido/{estado}")
    @Operation(summary = "Obtener pedidos por estado", description = "Devuelve una lista de pedidos filtrados por su estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedidos por estado obtenidos exitosamente")
    })
    public List<Pedido> obtenerPedidosPorEstado(
        @Parameter(description = "Estado del pedido (PENDIENTE, PAGADO, CANCELADO)", required = true)
        @PathVariable PedidoEstado estado) {
        return pedidoService.obtenerPedidosPorEstado(estado);
    }

    @GetMapping("/cantidadPedido/{estado}")
    @Operation(summary = "Contar pedidos por estado", description = "Devuelve la cantidad de pedidos que tienen el estado especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cantidad de pedidos obtenida exitosamente")
    })
    public long contarPedidosPorEstado(
        @Parameter(description = "Estado del pedido a contar", required = true)
        @PathVariable PedidoEstado estado) {
        return pedidoService.contarPedidosPorEstado(estado);
    }

    @PutMapping("/actualizar-estado")
    @Operation(summary = "Actualizar estado del pedido", description = "Actualiza el estado del pedido y, si es PAGADO, retorna los datos de la venta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente y venta generada si aplica"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content)
    })
    public VentaDTO actualizarEstado(
        @RequestBody(
            description = "Datos para actualizar el estado del pedido",
            required = true,
            content = @Content(schema = @Schema(implementation = EstadoPagoDTO.class))
        ) @org.springframework.web.bind.annotation.RequestBody EstadoPagoDTO estadoDTO) {
        return pedidoService.actualizarEstadoPedido(estadoDTO);
    }
}