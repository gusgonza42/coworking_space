package coworking.space.core.service.usuario;

import coworking.space.core.model.usuario.UsuarioModel;
import coworking.space.core.repository.usuario.UsuarioRepository;
import coworking.space.core.utils.Constantes;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de usuarios.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para inyectar el repositorio de usuarios.
     *
     * @param usuarioRepository el repositorio de usuarios
     */
    public UsuarioService( UsuarioRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    public List< UsuarioModel > findAll( ) {
        return usuarioRepository.findAll( );
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario
     * @return el usuario encontrado o null si no se encuentra
     */
    public UsuarioModel findUsuarioById( Long id ) {
        return usuarioRepository.findById( id ).orElse( null );
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param usuarioModel el modelo del usuario a guardar
     * @return el usuario guardado
     * @throws RuntimeException si el correo electrónico ya está registrado
     */
    public UsuarioModel saveUsuario( UsuarioModel usuarioModel ) {
        if( usuarioRepository.findByEmail( usuarioModel.getEmail( ) ).isPresent( ) ) {
            throw new RuntimeException( Constantes.ERROR_EMAIL_REGISTRADO );
        }
        return usuarioRepository.save( usuarioModel );
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id                 el ID del usuario a actualizar
     * @param usuarioActualizado el modelo del usuario actualizado
     * @return el usuario actualizado
     */
    public UsuarioModel updateUsuario( Long id, UsuarioModel usuarioActualizado ) {
        UsuarioModel usuario = findUsuarioById( id );
        usuario.setNombre( usuarioActualizado.getNombre( ) );
        usuario.setEmail( usuarioActualizado.getEmail( ) );
        usuario.setEstado( usuarioActualizado.getEstado( ) );
        return usuarioRepository.save( usuario );
    }

    /**
     * Elimina (inactiva) un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar
     */
    public void deleteUsuario( Long id ) {
        UsuarioModel usuario = findUsuarioById( id );
        if( usuario != null ) {
            usuario.setEstado( UsuarioModel.ESTADO_INACTIVO );
            usuarioRepository.save( usuario );
        }
    }
}