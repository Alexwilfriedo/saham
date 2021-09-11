package com.digital.repository;

import com.digital.model.Contrat;
import com.digital.model.QuittanceAnnuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuittanceAnnuelRepository extends JpaRepository<QuittanceAnnuel,Long> {

    @Query("select q from QuittanceAnnuel q where q.nomObjet =:x and q.contrat =:y and date_part('year', q.echeance) =:z")
    public List<QuittanceAnnuel> getQuitAnnuel(@Param("x") String o, @Param("y") String police, @Param("z") int annee);


}
