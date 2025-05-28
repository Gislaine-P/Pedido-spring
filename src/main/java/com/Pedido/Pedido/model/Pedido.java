package com.Pedido.Pedido.model;

import java.time.LocalDate;
import java.util.List;

import com.Pedido.Pedido.enums.PedidoEstado;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "pedido") // Indica el nombre de la tabla en la base de datos
@Data // Genera automáticamente los métodos getter y setter
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
public class Pedido {
    
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor automáticamente
    private Long idPedido;
    
    @Column(nullable = false)
    private Long idUsuarioComprador;

    @Column(nullable = true)
    private Long idUsuarioVendedor;

    /**Onetomany indica que un pedido puede tener muchos detalles de pedido
    * mappedBy indica que el detalle de pedido es la clase que tiene la referencia al pedido
    * cascade = cascadeType.all indica que las operaciones guardar, eliminar, actualizar aplica
    * orphanRemoval = true indica que si se elimina un detalle de la lista tambien se eliminara de la base de datos igual a sus detalles
    */ 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true) 
    @JsonManagedReference // Para evitar problemas de referencia cíclica al serializar a JSON
    private List<DetallePedido> detallePedido;

    @Enumerated(EnumType.STRING)
    private PedidoEstado estadoPedido;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private LocalDate fechaPedido;



}
