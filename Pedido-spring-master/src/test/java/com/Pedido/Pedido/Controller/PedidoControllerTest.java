package com.Pedido.Pedido.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.Pedido.Pedido.controller.PedidoController;
import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarPedidoDesdeVendedorTest() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);
        pedido.setIdUsuarioVendedor(100L);
        pedido.setIdUsuarioComprador(200L);

        Mockito.when(pedidoService.crearPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v6/pedidos/vendedor/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPedido").value(1));
    }

    @Test
    void obtenerPedidoPorId_Test() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(5L);

        Mockito.when(pedidoService.obtenerPedidoPorId(5L)).thenReturn(pedido);

        mockMvc.perform(get("/api/v6/pedidos/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPedido").value(5));
    }

    @Test
    void obtenerPedidoPorIdTest() throws Exception {
        Mockito.when(pedidoService.obtenerPedidoPorId(999L)).thenReturn(null);

        mockMvc.perform(get("/api/v6/pedidos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerPedidosPorUsuarioTest() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1L);

        Mockito.when(pedidoService.obtenerPedidosPorUsuario(200L))
                .thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v6/pedidos/usuario/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPedido").value(1));
    }

    @Test
    void obtenerPedidosPorEstadoTest() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(2L);
        pedido.setEstadoPedido(PedidoEstado.PAGADO);

        Mockito.when(pedidoService.obtenerPedidosPorEstado(PedidoEstado.PAGADO))
                .thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/v6/pedidos/estadoPedido/PAGADO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estadoPedido").value("PAGADO"));
    }

    @Test
    void contarPedidosPorEstadoTest() throws Exception {
        Mockito.when(pedidoService.contarPedidosPorEstado(PedidoEstado.PENDIENTE))
                .thenReturn(3L);

        mockMvc.perform(get("/api/v6/pedidos/cantidadPedido/PENDIENTE"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    void obtenerTodosLosPedidosTest() throws Exception {
        Pedido pedido1 = new Pedido();
        pedido1.setIdPedido(1L);

        Pedido pedido2 = new Pedido();
        pedido2.setIdPedido(2L);

        Mockito.when(pedidoService.obtenerTodosLosPedidos())
                .thenReturn(List.of(pedido1, pedido2));

        mockMvc.perform(get("/api/v6/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void registrarPedidoDesdeCompradorTest() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(10L);
        pedido.setIdUsuarioComprador(999L);

        Mockito.when(pedidoService.crearPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v6/pedidos/comprador/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idPedido").value(10));
    }
}