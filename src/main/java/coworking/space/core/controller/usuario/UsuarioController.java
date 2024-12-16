package coworking.space.core.controller.usuario;

import coworking.space.core.model.usuario.UsuarioModel;
import coworking.space.core.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar usuarios.
 */
@RestController
@RequestMapping ( "/api/usuario" )
@Tag ( name = "Usuario", description = "API de Usuario" )
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Constructor para inyectar el servicio de usuario.
     *
     * @param usuarioService el servicio de usuario
     */
    public UsuarioController( UsuarioService usuarioService ) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    @GetMapping
    @Operation ( summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios" )
    public ResponseEntity< ? > findAll( ) {
        try {
            List< UsuarioModel > usuarios = usuarioService.findAll( );
            return ResponseEntity.ok( usuarios );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al obtener la lista de usuarios: " + e.getMessage( ) );
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id el ID del usuario
     * @return el usuario con el ID especificado
     */
    @GetMapping ( "/{id}" )
    @Operation ( summary = "Obtener usuario por ID", description = "Devuelve un usuario por su ID" )
    public ResponseEntity< ? > findUsuarioById( @PathVariable Long id ) {
        try {
            UsuarioModel usuario = usuarioService.findUsuarioById( id );
            if( usuario == null ) {
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Usuario no encontrado con ID: " + id );
            }
            return ResponseEntity.ok( usuario );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al buscar el usuario: " + e.getMessage( ) );
        }
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param usuarioModel el modelo del usuario a guardar
     * @return el usuario guardado
     */
    @PostMapping
    @Operation ( summary = "Guardar un nuevo usuario", description = "Crea y guarda un nuevo usuario" )
    public ResponseEntity< ? > saveUsuario( @RequestBody UsuarioModel usuarioModel ) {
        try {
            UsuarioModel nuevoUsuario = usuarioService.saveUsuario( usuarioModel );
            return ResponseEntity.status( HttpStatus.CREATED ).body( nuevoUsuario );
        } catch ( RuntimeException e ) {
            return ResponseEntity.badRequest( ).body( e.getMessage( ) );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al guardar el usuario: " + e.getMessage( ) );
        }
    }

    /**
     * Actualiza un usuario existente por su ID.
     *
     * @param id                 el ID del usuario a actualizar
     * @param usuarioActualizado el modelo del usuario actualizado
     * @return el usuario actualizado
     */
    @PutMapping ( "/{id}" )
    @Operation ( summary = "Actualizar un usuario", description = "Actualiza un usuario existente por su ID, nombre, email y estado" )
    public ResponseEntity< ? > updateUsuario( @PathVariable Long id, @RequestBody UsuarioModel usuarioActualizado ) {
        try {
            UsuarioModel usuario = usuarioService.findUsuarioById( id );
            if( usuario == null ) {
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Usuario no encontrado con ID: " + id );
            }
            UsuarioModel usuarioActualizadoFinal = usuarioService.updateUsuario( id, usuarioActualizado );
            return ResponseEntity.ok( usuarioActualizadoFinal );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al actualizar el usuario: " + e.getMessage( ) );
        }
    }

    /**
     * Elimina (inactiva) un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar
     * @return una respuesta indicando el resultado de la operación
     */
    @DeleteMapping ( "/{id}" )
    @Operation ( summary = "Eliminar un usuario", description = "Elimina (inactiva) un usuario por su ID" )
    public ResponseEntity< ? > eliminarUsuario( @PathVariable Long id ) {
        try {
            UsuarioModel usuario = usuarioService.findUsuarioById( id );
            if( usuario == null ) {
                return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Usuario no encontrado con ID: " + id );
            }
            usuarioService.deleteUsuario( id );
            return ResponseEntity.ok( "Usuario con ID " + id + " inactivado correctamente" );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al eliminar el usuario: " + e.getMessage( ) );
        }
    }
}