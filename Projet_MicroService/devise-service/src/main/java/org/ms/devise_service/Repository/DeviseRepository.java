package org.ms.devise_service.Repository;


import org.ms.devise_service.model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    // Recherche possible par code devise
    Devise findByCode(String code);
}

