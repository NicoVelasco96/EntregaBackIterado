package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Promocion extends Base {

    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "promocion")
    @Builder.Default
    private Set<Imagen> imagenes= new HashSet<>();

    @ManyToMany(mappedBy = "promociones")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "promocion_articulo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id"))
    @Builder.Default
    private Set<Articulo> articulos= new HashSet<>();
}
