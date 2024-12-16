package coworking.space.core.model.sala;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * Modelo de entidad para representar una sala en el sistema de reservas de coworking.
 */
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Entity
@Table ( name = "sala" )
public class SalaModel {
    public static final int inactivo = 0;
    public static final int disponible = 1;
    public static final int reservado = 2;
    public static final int mantenimiento = 3;

    /**
     * Identificador único de la sala.
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Schema ( hidden = true )
    private Long id;

    /**
     * Nombre de la sala.
     */
    @Column ( name = "nombre" )
    @Schema ( example = "Sala de reuniones" )
    private String nombre;

    /**
     * Capacidad de la sala.
     */
    @Column ( name = "capacidad" )
    @Schema ( example = "10" )
    private Integer capacidad;

    /**
     * Ubicación de la sala.
     */
    @Column ( name = "ubicacion" )
    @Schema ( example = "Piso 2, Oficina A" )
    private String ubicacion;

    /**
     * Precio de la sala.
     */
    @Column ( name = "precio" )
    @Schema ( example = "100.0" )
    private Double precio;

    /**
     * Estado de la sala (0 = inactivo, 1 = disponible, 2 = reservado, 3 = mantenimiento).
     */
    @Column ( name = "estado" )
    @Schema ( hidden = true )
    private int estado = disponible;

    /**
     * Descripción de la sala.
     */
    @Column ( name = "descripcion" )
    @Schema ( example = "Sala adecuada para reuniones o conferencias." )
    private String descripcion;

    /**
     * Fecha de creación de la sala.
     */
    @Column ( name = "fecha_creacion" )
    @Temporal ( TemporalType.DATE )
    @Schema ( hidden = true )
    private Date fechaCreacion;

    /**
     * Metodo de ciclo de vida de JPA que se ejecuta antes de persistir la entidad.
     * Establece la fecha de creación si no está ya establecida.
     */
    @PrePersist
    protected void onCreate( ) {
        if( fechaCreacion == null ) {
            fechaCreacion = new Date( );
        }
    }
}