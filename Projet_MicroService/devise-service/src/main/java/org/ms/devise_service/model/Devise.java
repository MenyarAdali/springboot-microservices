package org.ms.devise_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Devise {

    @Id
    @GeneratedValue
    private Long id;

    private String code;  // ex : USD, EUR, TND
    private String nom;   // ex : Dollar, Euro, Dinar Tunisien
    private double tauxChange;  // taux par rapport à une devise de référence (ex : USD)

    // Constructeurs
    public Devise() {}

    public Devise(String code, String nom, double tauxChange) {
        this.code = code;
        this.nom = nom;
        this.tauxChange = tauxChange;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getTauxChange() { return tauxChange; }
    public void setTauxChange(double tauxChange) { this.tauxChange = tauxChange; }
}

