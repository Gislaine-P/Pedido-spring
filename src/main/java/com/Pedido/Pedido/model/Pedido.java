package com.Pedido.Pedido.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Long id;
    
    @Column(nullable = false)
    private Long idUsuarioComprador;

    @Column(nullable = false)
    private Long idsuarioVendedor;

    /**Onetomany indica que un pedido puede tener muchos detalles de pedido
    * mappedBy indica que el detalle de pedido es la clase que tiene la referencia al pedido
    * cascade = cascadeType.all indica que las operaciones guardar, eliminar, actualizar aplica
    * orphanRemoval = true indica que si se elimina un detalle de la lista tambien se eliminara de la base de datos igual a sus detalles
    */ 
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<DetallePedido> detalleProducto;

    @Column(nullable = false)
    private double total;



}
