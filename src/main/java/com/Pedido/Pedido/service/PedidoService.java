package com.Pedido.Pedido.service;

import com.Pedido.Pedido.dto.internaldto.CarritoDTO;
import com.Pedido.Pedido.dto.internaldto.PedidoItemDTO;
import com.Pedido.Pedido.dto.internaldto.PedidoRequestDTO;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PedidoService {

    // Simulación de carritos en memoria (clave: idUsuario, valor: carrito)
    private final Map<Long, CarritoDTO> carritos = new HashMap<>();

    // ===============================
    // 🧑‍💻 MÉTODOS PARA EL USUARIO
    // ===============================

    /**
     * Agrega un producto al carrito del usuario.
     *
     * @param idUsuario ID del usuario que agrega el producto.
     * @param item      Producto con cantidad a agregar.
     */
    public void agregarProductoAlCarrito(Long idUsuario, PedidoItemDTO item) {
        CarritoDTO carrito = carritos.getOrDefault(idUsuario, new CarritoDTO(idUsuario, new ArrayList<>()));

        // Buscar si ya existe ese producto para sumar cantidades
        Optional<PedidoItemDTO> existente = carrito.getItems().stream()
                .filter(i -> i.getIdProducto().equals(item.getIdProducto()))
                .findFirst();

        if (existente.isPresent()) {
            // Sumar cantidad
            PedidoItemDTO actual = existente.get();
            actual.setCantidad(actual.getCantidad() + item.getCantidad());
        } else {
            carrito.getItems().add(item);
        }

        carritos.put(idUsuario, carrito);
    }

    /**
     * Obtiene los productos actuales del carrito del usuario.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de productos en el carrito.
     */
    public List<PedidoItemDTO> verCarrito(Long idUsuario) {
        return carritos.getOrDefault(idUsuario, new CarritoDTO()).getItems();
    }

    /**
     * Vacía completamente el carrito del usuario.
     *
     * @param idUsuario ID del usuario.
     */
    public void vaciarCarrito(Long idUsuario) {
        carritos.remove(idUsuario);
    }

    /**
     * Genera un pedido a partir del carrito del usuario si el pago fue confirmado.
     * Aquí se deberían hacer validaciones reales, como stock y comunicación con MS Pago/Venta.
     *
     * @param request Información del pedido (id comprador, presencialidad, etc.)
     */
    public void generarPedidoDesdeCarrito(PedidoRequestDTO request) {
        if (request.isPresencial()) {
            throw new IllegalArgumentException("Este método es solo para pedidos online.");
        }

        CarritoDTO carrito = carritos.get(request.getIdUsuarioComprador());

        if (carrito == null || carrito.getItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío.");
        }

        // Aquí iría la lógica para:
        // - Validar stock consultando MS Producto
        // - Llamar a MS Pago para procesar el pago
        // - Si el pago se confirma, llamar a MS Venta para registrar el pedido

        // Simulación: Pedido creado con éxito
        System.out.println("Pedido generado para usuario " + request.getIdUsuarioComprador());

        // Vaciar carrito luego de generar el pedido
        vaciarCarrito(request.getIdUsuarioComprador());
    }

    /**
     * Consulta el historial de pedidos del usuario.
     * En un escenario real, llamaría a MS Venta para obtener el historial.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de pedidos (simulado).
     */
    public List<String> obtenerHistorialDePedidos(Long idUsuario) {
        // Simulación de pedidos históricos
        return List.of("Pedido #123 - Confirmado", "Pedido #124 - Entregado");
    }

    // ===============================
    // 🧑‍🔧 MÉTODOS PARA EL VENDEDOR
    // ===============================

    /**
     * El vendedor registra un pedido presencial para un cliente.
     *
     * @param request DTO con los datos del pedido presencial.
     */
    public void registrarPedidoPresencial(PedidoRequestDTO request) {
        if (!request.isPresencial()) {
            throw new IllegalArgumentException("El pedido debe ser presencial.");
        }

        if (request.getIdUsuarioVendedor() == null) {
            throw new IllegalArgumentException("El ID del vendedor es obligatorio para pedidos presenciales.");
        }

        // Aquí iría la lógica para:
        // - Validar stock con MS Producto
        // - Procesar pago presencial (puede ser pago confirmado instantáneo)
        // - Registrar pedido en MS Venta

        // Simulación: Pedido presencial registrado
        System.out.println("Pedido presencial registrado por vendedor " + request.getIdUsuarioVendedor());
    }
}

