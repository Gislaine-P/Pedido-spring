package com.Pedido.Pedido.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.Pedido.Pedido.model.DetallePedido;
import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.recibirDTO.EstadoPagoDTO;
import com.Pedido.Pedido.repository.PedidoRepository;
import com.Pedido.Pedido.transferirDTO.DetallePedidoDTO;
import com.Pedido.Pedido.transferirDTO.VentaDTO;

@Service// Indica que esta clase es un servicio de Spring
public class PedidoService {

    @Autowired // Significa que Spring inyectará una instancia de PedidoRepository aquí
    private PedidoRepository pedidoRepository;




    //CREAR PEDIDO
public Pedido crearPedido(Pedido pedido) {
    double totalPedido = 0.0;

    for (DetallePedido detalle : pedido.getDetallePedido()) {
        double subtotal = detalle.getPrecioProducto() * detalle.getCantidadProducto();
        detalle.setSubtotal(subtotal);
        totalPedido += subtotal;
        detalle.setPedido(pedido); // Establecer la relación bidireccional
    }

    pedido.setTotal(totalPedido);
    pedido.setEstadoPedido(PedidoEstado.PENDIENTE);
    pedido.setFechaPedido(
            java.time.LocalDate.now()
    );
    Pedido pedidoGuardado = pedidoRepository.save(pedido);

    return pedidoGuardado;
}


   //OBTENER PEDIDO POR ID
       public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id).orElse(null); // Devuelve el pedido si existe o null si no se encuentra
    }


    //OBTENER TODOS LOS PEDIDOS
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll(); // Devuelve una lista de todos los pedidos
    }


    //OBTENER PEDIDO POR USUARIO
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuarioComprador){        //*************  
        return pedidoRepository.findByIdUsuarioComprador(idUsuarioComprador);
    }

    //OBTENER PEDIDO POR ESTADO
    public List<Pedido> obtenerPedidosPorEstado(PedidoEstado estado){    //*************************************** */
        return pedidoRepository.findByEstadoPedido(estado); // Devuelve una lista de pedidos filtrados por estado
    }


    //CONTAR PEDIDOS POR ESTADO
    public long contarPedidosPorEstado(PedidoEstado estado) {
        return pedidoRepository.countByEstadoPedido(estado);
    }

    //Actualizar esta do del pedido
public VentaDTO actualizarEstadoPedido(EstadoPagoDTO estadoDTO) {
    Pedido pedido = pedidoRepository.findById(estadoDTO.getIdPedido()).orElse(null);

    if (pedido != null) {
        PedidoEstado nuevoEstado = estadoDTO.getEstadoPedido();

        if (nuevoEstado == PedidoEstado.PAGADO || nuevoEstado == PedidoEstado.CANCELADO) {
            pedido.setEstadoPedido(nuevoEstado);
            pedidoRepository.save(pedido);

            // Si el estado es PAGADO, generar y retornar el VentaDTO
            if (nuevoEstado == PedidoEstado.PAGADO) {
                VentaDTO venta = new VentaDTO();
                venta.setIdPedido(pedido.getIdPedido());
                venta.setIdUsuarioComprador(pedido.getIdUsuarioComprador());
                venta.setIdUsuarioVendedor(pedido.getIdUsuarioVendedor()); // puede ser null si fue online
                venta.setTotal(pedido.getTotal());
                venta.setFechaPedido(LocalDate.now());
                List<DetallePedidoDTO> detallesDTO = pedido.getDetallePedido().stream().map(detalle -> {
                    DetallePedidoDTO dto = new DetallePedidoDTO();
                    dto.setIdProducto(detalle.getIdProducto());
                    dto.setNombreProducto(detalle.getNombreProducto());
                    dto.setPrecioProducto(detalle.getPrecioProducto());
                    dto.setCantidadProducto(detalle.getCantidadProducto());
                    dto.setSubtotal(detalle.getSubtotal());
                    return dto;
                }).toList();

                venta.setDetallePedido(detallesDTO);
                return venta;
            }
        }
    }

    return null; // Si no fue PAGADO o no existe, no se genera venta
}

}

