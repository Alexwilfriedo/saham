package com.digital.repository;

import com.digital.model.StatutQuitance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutQuitanceRepository extends JpaRepository<StatutQuitance,Long>{
    public StatutQuitance findByStatutQuit(String statut);
}
