package org.ms.devise_service.Service;


import org.ms.devise_service.model.Devise;
import org.ms.devise_service.Repository.DeviseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviseService {

    private final DeviseRepository repository;

    public DeviseService(DeviseRepository repository) {
        this.repository = repository;
    }

    public List<Devise> getAllDevises() {
        return repository.findAll();
    }

    public Optional<Devise> getDeviseById(Long id) {
        return repository.findById(id);
    }

    public Devise addDevise(Devise d) {
        return repository.save(d);
    }

    public Devise updateDevise(Long id, Devise d) {
        return repository.findById(id).map(existing -> {
            existing.setCode(d.getCode());
            existing.setNom(d.getNom());
            existing.setTauxChange(d.getTauxChange());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Devise non trouvée"));
    }

    public void deleteDevise(Long id) {
        repository.deleteById(id);
    }
}

