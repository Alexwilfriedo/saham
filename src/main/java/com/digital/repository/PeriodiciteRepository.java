package com.digital.repository;

import com.digital.model.Periodicite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodiciteRepository extends JpaRepository<Periodicite,Long>{

    public Periodicite findByCode(Long code);
}
