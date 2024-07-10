package pe.oly.yoga_backend_api.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.oly.yoga_backend_api.Suscription.Suscription;
import pe.oly.yoga_backend_api.Suscription.SuscriptionDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "correo" }),
        @UniqueConstraint(columnNames = { "nro_documento" })
})
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String correo;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String nombre;

    @Column(nullable = false)
    String apellido_paterno;

    @Column(nullable = false)
    String apellido_materno;

    @Column(nullable = false)
    Date fec_nacimiento;

    @Column(nullable = false)
    Integer id_tipo_documento;

    @Column(nullable = false)
    String nro_documento;

    String celular;

    LocalDateTime fecha_registro;

    @Column(name = "fecha_actualizacion")
    LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    Rol rol;

    @Column(name = "suscripcion_id")
    private Long suscripcionId;

    @Transient
    private SuscriptionDTO suscripcion;

    private int cantidadInscipciones;

    public void incremetarInscripciones() {
        this.cantidadInscipciones++;
    }

    public void restarInscripciones() {
        this.cantidadInscipciones--;
    }

    @PrePersist
    void prePersist() {
        fecha_registro = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return correo;
    }
}
