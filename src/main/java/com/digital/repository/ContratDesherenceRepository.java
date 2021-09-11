package com.digital.repository;

import com.digital.model.DesherenceContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ContratDesherenceRepository extends JpaRepository<DesherenceContrat,Long> {
    DesherenceContrat findByChContrat(String contrat);
    DesherenceContrat findByNomAssureAndDtNaisAssure(String assure, Date date);
    DesherenceContrat findByNomSouscAndDtNaisSouscript(String suscripteur, Date date);



}
