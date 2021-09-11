package com.digital.repository;

import com.digital.model.DemandeContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeContratRepository extends JpaRepository<DemandeContrat,Long> {

    List<DemandeContrat> findBySouscripteur(Long souscripteur);
}
