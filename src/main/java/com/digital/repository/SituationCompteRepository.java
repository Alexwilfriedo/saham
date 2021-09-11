package com.digital.repository;

import com.digital.model.SituationCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SituationCompteRepository extends JpaRepository<SituationCompte,Long>{

    SituationCompte findByCodeContrat(String code);
    SituationCompte findFirstByCodeContrat(String code);

    @Query("SELECT sc FROM SituationCompte sc WHERE sc.assure = :assure AND sc.capitalAcquisJanv = :capitalAcquisJanv AND sc.capitalAu = :capitalAu AND " +
            "sc.codeContrat = :codeContrat AND sc.contractant = :contractant AND sc.cotisationAnnuelle = :cotisationAnnuelle AND sc.dateSituation = :dateSituation AND " +
            "sc.interet = :interet AND sc.periodicite = :periodicite AND sc.prestation = :prestation AND sc.primeNette = :primeNette AND " +
            "sc.tauxRevaloEpargne = :taux AND sc.typeContrat = :typeContrat")
    SituationCompte findSituationCompteBeforeProcessFile(
            @Param("assure") String assure,
            @Param("capitalAcquisJanv") double capitalAcquisJanv,
            @Param("capitalAu") double capitalAu,
            @Param("codeContrat") String codeContrat,
            @Param("contractant") String contractant,
            @Param("cotisationAnnuelle") double cotisationAnnuelle,
            @Param("dateSituation") Date dateSituation,
            @Param("interet") double interet,
            @Param("periodicite") String periodicite,
            @Param("prestation") double prestation,
            @Param("primeNette") double primeNette,
            @Param("taux") String taux,
            @Param("typeContrat") String typeContrat
    );
}
