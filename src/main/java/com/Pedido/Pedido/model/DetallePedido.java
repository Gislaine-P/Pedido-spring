package com.Pedido.Pedido.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "detallePedido") // Indica el nombre de la tabla en la base de datos
@Data // Genera automáticamente los métodos getter y setter
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Builder
public class DetallePedido {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el valor automáticamente
    private Long idDetallePedido;

    @Column(nullable = false)
    private Long idProducto;

    @Column(nullable = false)
    private double precioProducto;

    @Column(nullable = false)
    private int cantidadProducto;
    
    @Column(nullable = false)
    private double subtotal;
    

    /** ManyToOne indica que muchos detalles pertenecen a un pedido
     * joinColumn crea una columna llamada pedido_id en la tabla detallePedido referenciando al pedido que pertenece al detalle
     */
    @ManyToOne 
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

}
