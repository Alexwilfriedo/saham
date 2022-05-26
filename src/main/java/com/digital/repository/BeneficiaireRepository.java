package com.digital.repository;

import com.digital.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire,Long> {
    @Query("SELECT b FROM Beneficiaire b WHERE b.contrat.id = :idContrat")
    Optional<Beneficiaire> findByContrat(@Param("idContrat") Long aLong);
}
