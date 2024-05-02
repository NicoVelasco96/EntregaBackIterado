package com.example.buensaborback.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class ArticuloManufacturadoDetalle extends Base {
    private Double cantidad;

    @ManyToOne
    @JoinColumn(name="articuloManufacturado_id")
    private ArticuloManufacturado articuloManufacturado;

    @ManyToOne
    @JoinColumn(name="articuloInsumo_id")
    private ArticuloInsumo articuloInsumo;


}
