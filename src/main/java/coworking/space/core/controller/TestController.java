package coworking.space.core.controller;

import coworking.space.core.model.TestModel;
import coworking.space.core.service.TestService;
import coworking.space.core.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones relacionadas con TestModel.
 * {/test} es el que se va modificando en función de la entidad que se esté trabajando.
 */
@RestController
@RequestMapping ( "/api/test" )
@Tag ( name = "Test", description = "Ejemplo de Test Controller" )
public class TestController {

    private final TestService testService;

    /**
     * Constructor para inyectar el servicio de TestModel.
     *
     * @param testService el servicio de TestModel.
     */
    public TestController( TestService testService ) {
        this.testService = testService;
    }

    /**
     * Crea un nuevo TestModel en la base de datos.
     *
     * @param model el modelo de prueba a crear.
     * @return una respuesta HTTP con el TestModel creado.
     */
    @Operation ( summary = "Create Test", description = "Crear un nuevo test en la base de datos" )
    @PostMapping
    public ResponseEntity< Object > createTest( @RequestBody TestModel model ) {
        TestModel test = testService.createTest( model );
        return ResponseEntity.ok( ).body( test );
    }

    /**
     * Obtiene todos los TestModel de la base de datos.
     *
     * @return una respuesta HTTP con la lista de TestModel.
     */
    @Operation ( summary = "Get Test", description = "Mostrar todos los tests de la base de datos" )
    @GetMapping
    public ResponseEntity< Object > getTest( ) {
        List< TestModel > tests = testService.getAllTests( );
        return ResponseEntity.ok( ).body( tests );
    }

    /**
     * Actualiza un TestModel existente en la base de datos.
     *
     * @param model el modelo de prueba con los nuevos datos.
     * @return una respuesta HTTP con el TestModel actualizado.
     */
    @Operation ( summary = "Update Test", description = "actualiza un test existente en la base de datos" )
    @PutMapping
    public ResponseEntity< Object > updateTest( @RequestBody TestModel model ) {
        TestModel test = testService.updateTest( model );
        return ResponseEntity.ok( ).body( test );
    }

    /**
     * Elimina lógicamente un TestModel por su ID en la base de datos.
     *
     * @param id el ID del TestModel a eliminar.
     * @return una respuesta HTTP indicando que el TestModel ha sido eliminado.
     */
    @Operation ( summary = "Delete Logic Test by ID", description = "Eliminación lógica de un test por su ID" )
    @DeleteMapping ( "/{id}" )
    public ResponseEntity< Object > deleteTestById( @PathVariable long id ) {
        testService.deleteTestById( id );
        return ResponseEntity.ok( ).body( Constantes.DELETED_BY_ID );
    }

}