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
public class Provincia extends Base{

    private String nombre;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "provincia")
    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="pais_id")
    private Pais pais;

}
