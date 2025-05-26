package com.Pedido.Pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pedido.Pedido.model.Pedido;
import com.Pedido.Pedido.repository.PedidoRepository;

@Service// Indica que esta clase es un servicio de Spring
public class PedidoService {

    @Autowired //Significa que Spring inyectará una instancia de PedidoRepository aquí
    private PedidoRepository pedidoRepository;


    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    //Obtener pedido por usuario
    public List<Pedido> obtenenerPedidoPorUsuario(Long idUsuarioComprador){
        return pedidoRepository.findByIdUsuarioComprador(idUsuarioComprador);
    }




    //METODOS BASICOS DE CRUD
    //Crear pedido
    public Pedido crearPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
    
    //obtener todos los pedidos
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll(); // Devuelve una lista de todos los pedidos
    }

    //obtener pedido por id
       public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id).orElse(null); // Devuelve el pedido si existe o null si no se encuentra
    }

    //actualizar un pedido
    public Pedido actualizarPedido(Long id, Pedido pedidoNuevo){
        Optional<Pedido> pedidoOp = pedidoRepository.findById(id); //Optional significa que puede o no contener un valor en este caso un pedido

        if (pedidoOp.isPresent()){  //isPresent significa que el pedido existe por lo que si existe se actualiza
            Pedido pedidoExist = pedidoOp.get(); // Se obtiene el pedido existente y se actualiza con nuevos datos
            pedidoExist. setIdUsuarioComprador(pedidoNuevo.getIdUsuarioComprador());
            pedidoExist.setIdsuarioVendedor(pedidoNuevo.getIdsuarioVendedor());
            pedidoExist.setDetalleProducto(pedidoNuevo.getDetalleProducto());
            pedidoExist.setEstadoPedido(pedidoNuevo.getEstadoPedido());
            pedidoExist.setTotal(pedidoNuevo.getTotal());

            return pedidoRepository.save(pedidoExist); //Se guarda el pedido actualizado
        } else {
        return null; // Si el pedido no existe devuelve null
    }
}

    //eliminar un pedido
    public void eliminarPedido(Long id){
        pedidoRepository.deleteById(id);
    }

}
