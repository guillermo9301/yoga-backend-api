package pe.oly.yoga_backend_api.User;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);

    @Modifying()
    @Query("update Usuario u set u.nombre=:nombre, u.apellido_paterno=:apellido_paterno, u.apellido_materno=:apellido_materno, u.celular=:celular, u.correo=:correo, u.fec_nacimiento=:fec_nacimiento, u.id_tipo_documento=:id_tipo_documento, u.nro_documento=:nro_documento, u.rol=:rol where u.id=:id")
    void updateUser(
            @Param(value = "id") Integer id,
            @Param(value = "nombre") String nombre,
            @Param(value = "apellido_paterno") String apellido_paterno,
            @Param(value = "apellido_materno") String apellido_materno,
            @Param(value = "celular") String celular,
            @Param(value = "correo") String correo,
            @Param(value = "fec_nacimiento") Date fec_nacimiento,
            @Param(value = "id_tipo_documento") Integer id_tipo_documento,
            @Param(value = "nro_documento") String nro_documento,
            @Param(value = "rol") Rol rol);
}
