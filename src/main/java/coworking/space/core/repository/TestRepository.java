package coworking.space.core.repository;

import coworking.space.core.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad TestModel.
 * Proporciona métodos CRUD básicos y métodos personalizados para acceder a los datos de TestModel.
 */
@Repository
public interface TestRepository extends JpaRepository< TestModel, Long > {

}