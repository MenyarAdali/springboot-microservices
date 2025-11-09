package org.ms.devise_service.web;


import org.ms.devise_service.model.Devise;
import org.ms.devise_service.Service.DeviseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devises")
public class DeviseController {

    private final DeviseService deviseService;

    public DeviseController(DeviseService deviseService) {
        this.deviseService = deviseService;
    }

    @GetMapping
    public List<Devise> getAll() {
        return deviseService.getAllDevises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devise> getById(@PathVariable Long id) {
        return deviseService.getDeviseById(id)
                .map(devise -> ResponseEntity.ok(devise))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Devise> add(@RequestBody Devise d) {
        Devise created = deviseService.addDevise(d);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devise> update(@PathVariable Long id, @RequestBody Devise d) {
        try {
            Devise updated = deviseService.updateDevise(id, d);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deviseService.deleteDevise(id);
        return ResponseEntity.noContent().build();
    }
}

