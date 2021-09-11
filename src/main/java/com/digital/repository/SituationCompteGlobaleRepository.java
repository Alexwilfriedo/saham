package com.digital.repository;

import com.digital.model.SituationCompte;
import com.digital.model.SituationCompteGlobale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationCompteGlobaleRepository extends JpaRepository<SituationCompteGlobale,Long>{

    SituationCompteGlobale findByCodeContrat(String code);
    SituationCompteGlobale findByIdContactant(Long id);
    SituationCompteGlobale getByIdContactant(Long id);

}
