package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Sucursal extends Base{

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;

    @OneToOne//Bidireccionalidad entre Sucursal y Domicilio
    @JoinColumn(name="domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;
    
    @ManyToMany
    @JoinTable(name = "sucursal_categoria",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();


    @ManyToMany
    @JoinTable(name="sucursal_promocion",
        joinColumns = @JoinColumn(name="sucursal_id"),
        inverseJoinColumns = @JoinColumn(name="promocion_id"))
    @Builder.Default
    private Set<Promocion> promociones = new HashSet<>();

    @ManyToOne//Bidireccionalidad entre Empresa y Sucursal
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "sucursal")//Bidireccionalidad entre Pedido y Sucursal
    @Builder.Default
    private Set<Pedido> pedidos =new HashSet<>();
}
