package coworking.space.core.service.sala;

import coworking.space.core.model.sala.SalaModel;
import coworking.space.core.repository.sala.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las operaciones relacionadas con SalaModel.
 */
@Service
public class SalaService {

    private final SalaRepository salaRepository;

    /**
     * Constructor para inyectar el repositorio de SalaModel.
     *
     * @param salaRepository el repositorio de SalaModel.
     */
    public SalaService( SalaRepository salaRepository ) {
        this.salaRepository = salaRepository;
    }

    /**
     * Obtiene todas las salas.
     *
     * @return una lista de todas las salas.
     */
    public List< SalaModel > getAllSalas( ) {
        return salaRepository.findAll( );
    }

    /**
     * Inserta una nueva sala.
     *
     * @param sala la sala a insertar.
     * @return la sala insertada.
     */
    public SalaModel insertSala( SalaModel sala ) {
        return salaRepository.save( sala );
    }

    /**
     * Actualiza una sala existente.
     *
     * @param sala la sala con los nuevos datos.
     * @return la sala actualizada.
     */
    public SalaModel updateSala( SalaModel sala ) {
        // Buscar la sala existente
        Optional< SalaModel > existingSalaOpt = salaRepository.findById( sala.getId( ) );

        // Verificar si la sala existe
        SalaModel existingSala = existingSalaOpt.orElseThrow( ( ) -> new RuntimeException( "Sala no encontrada con ID: " + sala.getId( ) ) );

        // Preservar la fecha de creación
        if( existingSala.getFechaCreacion( ) != null ) {
            sala.setFechaCreacion( existingSala.getFechaCreacion( ) );
        }

        return salaRepository.save( sala );
    }

    /**
     * Cambia el estado de una sala.
     *
     * @param id     el ID de la sala.
     * @param estado el nuevo estado de la sala.
     * @return la sala con el estado actualizado.
     */
    public SalaModel changeSalaState( long id, int estado ) {
        SalaModel sala = salaRepository.findById( id ).orElseThrow( ( ) -> new RuntimeException( "Sala no encontrada con ID: " + id ) );
        sala.setEstado( estado );
        return salaRepository.save( sala );
    }
}