package com.digital.repository;

import com.digital.model.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutContratRepository extends JpaRepository<StatutContrat,Long>{
    StatutContrat findByStatutContrat(String label);
}
