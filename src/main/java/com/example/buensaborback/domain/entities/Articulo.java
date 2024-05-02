package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected String denominacion;
    protected Double precioVenta;

   public Articulo(String denominacion, Double precioVenta, UnidadMedida unidadMedida){
        super();
        this.denominacion = denominacion;
        this.precioVenta = precioVenta;
        this.unidadMedida = unidadMedida;
        this.imagenes = new HashSet<>();
   }

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "articulo")
    protected Set<Imagen> imagenes = new HashSet<Imagen>();

    @ManyToOne
    @JoinColumn(name="unidadMedida_id")
    protected UnidadMedida unidadMedida;

    @ManyToMany(mappedBy = "articulos")
    protected Set<Promocion> promociones = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="categoria_id")
    protected Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "articulo")
    protected Set<DetallePedido> detalles = new HashSet<>();

}
