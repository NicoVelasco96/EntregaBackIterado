package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Pedido extends Base{

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne
    @JoinColumn(name="domicilio_id")
    private Domicilio domicilio;

    @ManyToOne
    @JoinColumn(name="sucursal_id")
    private Sucursal sucursal;

    @OneToOne(mappedBy = "pedido")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="pedido")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;


}
