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
public class Empresa extends Base{

    private String nombre;
    private String razonSocial;
    private Integer cuil;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="empresa")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}
