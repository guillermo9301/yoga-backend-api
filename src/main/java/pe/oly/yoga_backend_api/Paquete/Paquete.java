package pe.oly.yoga_backend_api.Paquete;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Paquete")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private float precio;

    @Column(name = "cantidad_clases")
    private Integer cantidadClases;

    @Column(name = "cantidad_dias")
    private Integer cantidadDias;
}

