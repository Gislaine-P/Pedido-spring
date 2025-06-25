package com.Pedido.Pedido.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.DetallePedido;
import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.repository.PedidoRepository;
import com.Pedido.Pedido.service.PedidoService;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearPedidoTest() {
        // Prepara un pedido con detalles
        Pedido pedido = new Pedido();
        pedido.setDetallePedido(List.of(
            new DetallePedido(null, 1L, "Prod1", 10.0, 2, 0.0, pedido),
            new DetallePedido(null, 2L, "Prod2", 5.0, 3, 0.0, pedido)
        ));

        // Mockeamos que el repository devuelve el pedido guardado (con id asignado)
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido p = invocation.getArgument(0);
            p.setIdPedido(100L);
            return p;
        });

        Pedido resultado = pedidoService.crearPedido(pedido);

        assertEquals(100L, resultado.getIdPedido());
        assertEquals(PedidoEstado.PENDIENTE, resultado.getEstadoPedido());
        assertEquals(LocalDate.now(), resultado.getFechaPedido());

        // Total = (10*2) + (5*3) = 35.0
        assertEquals(35.0, resultado.getTotal());

        // Cada detalle tiene subtotal correcto
        for (DetallePedido detalle : resultado.getDetallePedido()) {
            assertEquals(detalle.getPrecioProducto() * detalle.getCantidadProducto(), detalle.getSubtotal());
            assertEquals(resultado, detalle.getPedido()); // La relación bidireccional está seteada
        }

        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void obtenerPedidoPorId_Test() {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.obtenerPedidoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPedido());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    void obtenerPedidoPorIdTest() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        Pedido resultado = pedidoService.obtenerPedidoPorId(99L);

        assertNull(resultado);
        verify(pedidoRepository).findById(99L);
    }

    @Test
    void contarPedidosPorEstadoTest() {
        when(pedidoRepository.countByEstadoPedido(PedidoEstado.PENDIENTE)).thenReturn(5L);

        long cantidad = pedidoService.contarPedidosPorEstado(PedidoEstado.PENDIENTE);

        assertEquals(5L, cantidad);
        verify(pedidoRepository).countByEstadoPedido(PedidoEstado.PENDIENTE);
    }
}
