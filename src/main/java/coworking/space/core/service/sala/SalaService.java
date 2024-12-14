package coworking.space.core.service.sala;

import coworking.space.core.model.sala.SalaModel;
import coworking.space.core.repository.sala.SalaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public List<SalaModel> getAllSalas() {
        return salaRepository.findAll();
    }

    public SalaModel insertSala(SalaModel sala) {
        return salaRepository.save(sala);
    }

    public SalaModel updateSala(SalaModel sala) {
        if (!salaRepository.existsById(sala.getId())) {
            throw new RuntimeException("Sala no encontrada con ID: " + sala.getId());
        }
        return salaRepository.save(sala);
    }

    public SalaModel changeSalaState(long id, int estado) {
        SalaModel sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada con ID: " + id));
        sala.setEstado(estado);
        return salaRepository.save(sala);
    }
}