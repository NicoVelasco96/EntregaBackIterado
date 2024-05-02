package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Cliente extends Base{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToOne
    @JoinColumn(name="usuario_id",referencedColumnName = "id")
    private Usuario usuario;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "cliente")
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

    @ManyToMany
    //SE AGREGA EL JOIN TABLE PARA QUE JPA CREE LA TABLA INTERMEDIA EN UNA RELACION MANY TO MANY
    @JoinTable(name = "Cliente_domicilio",
            joinColumns = @JoinColumn(name = "Cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "domicilio_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "imagen_id",referencedColumnName = "id")
    private Imagen imagen;
}
