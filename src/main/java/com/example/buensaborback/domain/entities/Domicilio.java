package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Domicilio extends Base{

    private String calle;
    private Integer numero;
    private Integer cp;
    @OneToOne(mappedBy="domicilio")//Bidireccionalidad entre Domicilio y Sucursal
    private Sucursal sucursal;
    @ManyToOne
    @JoinColumn(name="localidad_id")//Bidireccionalidad entre Domicilio y Localidad
    private Localidad localidad;

    @ManyToMany(mappedBy = "domicilios")//Bidireccionalidad entre Cliente y Domicilio
    @Builder.Default
    private Set<Cliente> clientes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy ="domicilio")
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

}
