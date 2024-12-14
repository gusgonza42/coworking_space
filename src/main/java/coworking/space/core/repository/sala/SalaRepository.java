package coworking.space.core.repository.sala;

import coworking.space.core.model.sala.SalaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository< SalaModel, Long> {

}