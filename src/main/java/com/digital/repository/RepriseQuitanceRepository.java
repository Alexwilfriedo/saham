package com.digital.repository;

import com.digital.model.Contrat;
import com.digital.model.Objets;
import com.digital.model.reprise_donnee.RepriseQuitance;
import com.digital.model.StatutQuitance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface RepriseQuitanceRepository extends JpaRepository<RepriseQuitance,Long>{

}
