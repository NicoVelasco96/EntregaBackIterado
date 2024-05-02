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
public class Localidad extends Base{

    private String nombre;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="localidad")
    @Builder.Default
    private Set<Domicilio> domicilios =new HashSet<>();

    @ManyToOne
    @JoinColumn(name="provincia_id")
    private Provincia provincia;

}
