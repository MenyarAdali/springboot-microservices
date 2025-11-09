package org.ms.facture_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ms.facture_service.model.Produit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureLigne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produitID;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Facture facture;

    @Transient
    private Produit produit;
}