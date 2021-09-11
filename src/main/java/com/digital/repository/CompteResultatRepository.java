package com.digital.repository;

import com.digital.model.CompteResultat;
import com.digital.model.SituationCompteGlobale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteResultatRepository extends JpaRepository<CompteResultat,Long>{

    CompteResultat findByIdContactant(Long id);
    CompteResultat getByIdContactant(Long id);

}
