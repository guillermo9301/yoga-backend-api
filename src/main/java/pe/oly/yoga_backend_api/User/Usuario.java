package pe.oly.yoga_backend_api.User;

import java.sql.Date;
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
    Date fecha_registro;
    @Enumerated(EnumType.STRING)
    Rol rol;
    @ManyToOne(targetEntity = Suscription.class, fetch = FetchType.LAZY)
    List<Suscription> suscripciones;

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
