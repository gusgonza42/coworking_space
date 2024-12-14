package coworking.space.core.model.sala;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

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

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Schema ( hidden = true )
    private Long id;

    @Column ( name = "nombre" )
    @Schema ( example = "Sala de reuniones" )
    private String nombre;

    @Column ( name = "capacidad" )
    @Schema ( example = "10" )
    private Integer capacidad;

    @Column ( name = "ubicacion" )
    @Schema ( example = "Piso 2, Oficina A" )
    private String ubicacion;

    @Column ( name = "precio" )
    @Schema ( example = "100.0" )
    private Double precio;

    // 0 = inactivo, 1 = disponible, 2 = reservado, 3 = mantenimiento
    @Column ( name = "estado" )
    @Schema ( hidden = true )
    private int estado = disponible;

    @Column ( name = "descripcion" )
    @Schema ( example = "Sala adecuada para reuniones o conferencias." )
    private String descripcion;

    @Column ( name = "fecha_creacion" )
    @Temporal ( TemporalType.DATE )
    @Schema ( hidden = true )
    private Date fechaCreacion;

    @PrePersist
    protected void onCreate( ) {
        if( fechaCreacion == null ) {
            fechaCreacion = new Date( );
        }
    }
}