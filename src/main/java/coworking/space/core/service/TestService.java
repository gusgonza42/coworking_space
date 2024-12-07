package coworking.space.core.service;

import coworking.space.core.model.TestModel;
import coworking.space.core.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Servicio para gestionar las operaciones relacionadas con TestModel.
 */
@Service
public class TestService {

    private final TestRepository testRepository;

    /**
     * Constructor para inyectar el repositorio de TestModel.
     *
     * @param testRepository el repositorio de TestModel.
     */
    public TestService( TestRepository testRepository ) {
        this.testRepository = testRepository;
    }

    /**
     * Crea un nuevo TestModel.
     *
     * @param model el modelo de prueba a crear.
     * @return el TestModel creado.
     */
    public TestModel createTest( TestModel model ) {
        TestModel test = new TestModel( );
        test.setName( model.getName( ) );
        return testRepository.save( test );
    }

    /**
     * Obtiene todos los TestModel.
     *
     * @return una lista de todos los TestModel.
     */
    public List< TestModel > getAllTests( ) {
        return testRepository.findAll( );
    }

    /**
     * Actualiza un TestModel existente.
     *
     * @param model el modelo de prueba con los nuevos datos.
     * @return el TestModel actualizado, o null si no se encuentra.
     */
    public TestModel updateTest( TestModel model ) {
        TestModel test = testRepository.findById( 1L ).orElse( null );
        if( test != null ) {
            test.setName( model.getName( ) );
            return testRepository.save( test );
        }
        return null;
    }

    /**
     * Elimina lógicamente un TestModel por su ID.
     *
     * @param id el ID del TestModel a eliminar.
     */
    public void deleteTestById( long id ) {
        TestModel test = testRepository.findById( id ).orElse( null );
        if( test != null ) {
            test.setDeleted( true );
            testRepository.save( test );
        }
    }
}