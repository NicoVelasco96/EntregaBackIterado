package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Categoria extends Base{

    private String denominacion;


    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "categoria")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "categoria")
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

}
