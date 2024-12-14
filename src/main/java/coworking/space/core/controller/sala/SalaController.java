package coworking.space.core.controller.sala;

import coworking.space.core.model.sala.SalaModel;
import coworking.space.core.service.sala.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/api/sala" )
@Tag ( name = "Sala", description = "Controlador de Salas" )
public class SalaController {

    private final SalaService salaService;

    public SalaController( SalaService salaService ) {
        this.salaService = salaService;
    }

    @Operation ( summary = "Obtener todas las salas" )
    @GetMapping
    public ResponseEntity< ? > getAllSalas( ) {
        try {
            List< SalaModel > salas = salaService.getAllSalas( );
            return ResponseEntity.ok( salas );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( "Error al obtener las salas: " + e.getMessage( ) );
        }
    }

    @Operation ( summary = "Crear una nueva sala" )
    @PostMapping
    public ResponseEntity< ? > createSala( @RequestBody SalaModel sala ) {
        try {
            System.out.println( sala.toString( ) );
            SalaModel newSala = salaService.insertSala( sala );
            return ResponseEntity.status( HttpStatus.CREATED ).body( newSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Error al crear la sala: " + e.getMessage( ) );
        }
    }

    @Operation ( summary = "Actualizar una sala existente" )
    @PutMapping ( "/{id}" )
    public ResponseEntity< ? > updateSala( @PathVariable long id, @RequestBody SalaModel sala ) {
        try {
            sala.setId( id );
            SalaModel updatedSala = salaService.updateSala( sala );
            return ResponseEntity.ok( updatedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Error al actualizar la sala: " + e.getMessage( ) );
        }
    }

    @Operation ( summary = "Eliminar (inactivar) una sala" )
    @DeleteMapping ( "/{id}" )
    public ResponseEntity< ? > deleteSala( @PathVariable long id ) {
        try {
            SalaModel deletedSala = salaService.changeSalaState( id, SalaModel.inactivo );
            return ResponseEntity.ok( deletedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Error al eliminar la sala: " + e.getMessage( ) );
        }
    }

    @Operation ( summary = "Cambiar el estado de una sala" )
    @PatchMapping ( "/{id}/estado/{estado}" )
    public ResponseEntity< ? > changeSalaState( @PathVariable long id, @PathVariable int estado ) {
        try {
            SalaModel updatedSala = salaService.changeSalaState( id, estado );
            return ResponseEntity.ok( updatedSala );
        } catch ( Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Error al cambiar el estado de la sala: " + e.getMessage( ) );
        }
    }
}