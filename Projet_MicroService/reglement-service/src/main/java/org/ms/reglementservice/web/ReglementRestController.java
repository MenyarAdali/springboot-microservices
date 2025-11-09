package org.ms.reglementservice.web;

import org.ms.reglementservice.entites.Reglement;
import org.ms.reglementservice.repository.ReglementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reglements")
public class ReglementRestController {

    private final ReglementRepository reglementRepository;

    public ReglementRestController(ReglementRepository reglementRepository) {
        this.reglementRepository = reglementRepository;
    }

    @GetMapping("/facture/{factureId}")
    public List<Reglement> findReglementsByFactureId(@PathVariable Long factureId) {
        return reglementRepository.findByFactureId(factureId);
    }

    @GetMapping("/{id}")
    public Reglement findReglementById(@PathVariable Long id) {
        return reglementRepository.findById(id).orElseThrow(() -> new RuntimeException("Reglement not found"));
    }

    @PostMapping
    public Reglement createReglement(@RequestBody Reglement reglement) {
        return reglementRepository.save(reglement);
    }

    @PutMapping("/{id}")
    public Reglement updateReglement(@PathVariable Long id, @RequestBody Reglement reglement) {
        Reglement existing = reglementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reglement not found"));
        existing.setFactureId(reglement.getFactureId());
        existing.setMontant(reglement.getMontant());
        existing.setDateReglement(reglement.getDateReglement());
        existing.setModePaiement(reglement.getModePaiement());
        return reglementRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteReglement(@PathVariable Long id) {
        reglementRepository.deleteById(id);
    }

    @GetMapping
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }
}