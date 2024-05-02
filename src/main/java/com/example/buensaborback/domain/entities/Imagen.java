package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Imagen extends Base{

    private String url;
    @ManyToOne
    @JoinColumn(name="articulo_id")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name="promocion_id")
    private Promocion promocion;

    @OneToOne(mappedBy = "imagen")
    private Cliente cliente;
}
