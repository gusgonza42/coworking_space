package coworking.space.core.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un modelo de prueba.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table ( name = "model_test" )
public class TestModel {

    /**
     * Identificador único del modelo de prueba.
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;

    /**
     * Nombre del modelo de prueba.
     */
    @Column ( name = "name" )
    private String name;

    /**
     * Indicador de eliminación lógica.
     * Si es true, el modelo se considera eliminado.
     */
    @Column ( name = "is_deleted" )
    private boolean isDeleted = false;
}