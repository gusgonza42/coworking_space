package coworking.space.core.controller.sala;

import coworking.space.core.model.sala.SalaModel;
import coworking.space.core.service.sala.SalaService;
import coworking.space.core.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con SalaModel.
 */
@RestController
@RequestMapping ( "/api/sala" )
@Tag ( name = "Sala", description = "Controlador de Salas" )
public class SalaController {

    private final SalaService salaService;

    /**
     * Constructor para inyectar el servicio de SalaModel.
     *
     * @param salaService el servicio de SalaModel.
     */
    public SalaController( SalaService salaService ) {
        this.salaService = salaService;
    }

    /**
     * Obtiene todas las salas.
     *
     * @return una respuesta HTTP con la lista de todas las salas.
     */
    @Operation ( summary = "Obtener todas las salas" )
    @GetMapping
    public ResponseEntity< ? > getAllSalas( ) {
        try {
            List< SalaModel > salas = salaService.getAllSalas( );
            return ResponseEntity.ok( salas );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( Constantes.ERROR_OBTENER_SALAS + e.getMessage( ) );
        }
    }

    /**
     * Crea una nueva sala.
     *
     * @param sala el modelo de la sala a crear.
     * @return una respuesta HTTP con la sala creada.
     */
    @Operation ( summary = "Crear una nueva sala" )
    @PostMapping
    public ResponseEntity< ? > createSala( @RequestBody SalaModel sala ) {
        try {
            System.out.println( sala.toString( ) );
            SalaModel newSala = salaService.insertSala( sala );
            return ResponseEntity.status( HttpStatus.CREATED ).body( newSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( Constantes.ERROR_CREAR_SALA + e.getMessage( ) );
        }
    }

    /**
     * Actualiza una sala existente.
     *
     * @param id   el ID de la sala a actualizar.
     * @param sala el modelo de la sala con los nuevos datos.
     * @return una respuesta HTTP con la sala actualizada.
     */
    @Operation ( summary = "Actualizar una sala existente" )
    @PutMapping ( "/{id}" )
    public ResponseEntity< ? > updateSala( @PathVariable long id, @RequestBody SalaModel sala ) {
        try {
            sala.setId( id );
            SalaModel updatedSala = salaService.updateSala( sala );
            return ResponseEntity.ok( updatedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Constantes.ERROR_ACTUALIZAR_SALA + e.getMessage( ) );
        }
    }

    /**
     * Elimina (inactiva) una sala.
     *
     * @param id el ID de la sala a eliminar.
     * @return una respuesta HTTP con la sala eliminada.
     */
    @Operation ( summary = "Eliminar (inactivar) una sala" )
    @DeleteMapping ( "/{id}" )
    public ResponseEntity< ? > deleteSala( @PathVariable long id ) {
        try {
            SalaModel deletedSala = salaService.changeSalaState( id, SalaModel.inactivo );
            return ResponseEntity.ok( deletedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( Constantes.ERROR_INACTIVAR_SALA + e.getMessage( ) );
        }
    }

    /**
     * Cambia el estado de una sala.
     *
     * @param id     el ID de la sala.
     * @param estado el nuevo estado de la sala.
     * @return una respuesta HTTP con la sala con el estado actualizado.
     */
    @Operation ( summary = "Cambiar el estado de una sala" )
    @PatchMapping ( "/{id}/estado/{estado}" )
    public ResponseEntity< ? > changeSalaState( @PathVariable long id, @PathVariable int estado ) {
        try {
            SalaModel updatedSala = salaService.changeSalaState( id, estado );
            return ResponseEntity.ok( updatedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( Constantes.ERROR_CAMBIAR_ESTADO_SALA + e.getMessage( ) );
        }
    }
}