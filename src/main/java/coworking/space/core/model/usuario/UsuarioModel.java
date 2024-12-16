package coworking.space.core.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Representa el modelo de datos para un usuario en el sistema.
 */
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Entity
@Table ( name = "usuario" )
public class UsuarioModel {
    public static final int ESTADO_ACTIVO = 1;
    public static final int ESTADO_INACTIVO = 0;
    public static final int ROL_ADMIN = 1;
    public static final int ROL_USER = 0;

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Schema ( hidden = true )
    private Long id;

    /**
     * Nombre del usuario.
     */
    @Column ( name = "nombre", nullable = false )
    @Schema ( example = "Mario" )
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    @Column ( name = "email", nullable = false, unique = true )
    @Schema ( example = "mario@gmail.com" )
    private String email;

    /**
     * Estado del usuario: 1 = activo, 0 = inactivo.
     */
    @Column ( name = "estado" )
    private int estado = ESTADO_ACTIVO;

    @Column ( name = "rol" )
    @Schema ( example = "admin" )
    private Integer rol = ROL_USER;

    /**
     * Fecha de creación del usuario.
     */
    @Column ( name = "fecha_creacion" )
    @Schema ( hidden = true )
    @Temporal ( TemporalType.DATE )
    private Date fechaCreacion;

    /**
     * Establece la fecha a la hora de crear un Usuario.
     */
    @PrePersist
    protected void onCreate( ) {
        if( fechaCreacion == null ) {
            fechaCreacion = new Date( );
        }
    }
}