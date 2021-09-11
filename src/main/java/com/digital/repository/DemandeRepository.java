package com.digital.repository;

import com.digital.model.Demande;
import com.digital.model.Periodicite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long>{

    //public Demande findByCode(Long code);
    public List<Demande> findAllByUsername(String username);
}
