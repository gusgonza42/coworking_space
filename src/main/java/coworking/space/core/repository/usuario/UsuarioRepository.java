package coworking.space.core.repository.usuario;

import coworking.space.core.model.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la gestión de usuarios.
 * Proporciona métodos para realizar operaciones CRUD en la entidad UsuarioModel.
 */
@Repository
public interface UsuarioRepository extends JpaRepository< UsuarioModel, Long > {

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email el correo electrónico del usuario
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no se encuentra
     */
    Optional< UsuarioModel > findByEmail( String email );
}