package com.digital.repository;

import com.digital.model.Contrat;
import com.digital.model.Objets;
import com.digital.model.Quitance;
import com.digital.model.StatutQuitance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface QuitanceRepository extends JpaRepository<Quitance,Long>{
    Quitance findByNumQuittance(String numQuittance);
    Quitance findByContratAndEcheance(Contrat contrat, Date date);
    Quitance findFirstByContratAndEcheance(Contrat contrat, Date date);
    Quitance findFirstByContratAndEcheanceAndObjet(Contrat c, Date d, Objets o);

     List<Quitance> getQuitancesByContratAndObjet(Contrat contrat, Objets objets);
     List<Quitance> findByContratAndObjetOrderByEcheanceDesc(Contrat contrat, Objets objets);
     List<Quitance> findByPolice(String police);

   //  List<Quitance> findByContratAndObjetAndEcheance_YearOrderByEcheance(Contrat contrat, Objets objets,int date);
     Quitance findByContratAndObjet(Contrat contrat, Objets objets);
     Quitance findByContratAndObjetAndEcheanceGreaterThan(Contrat contrat, Objets objets, Date date);
     Quitance findByContratAndObjetAndEcheance(Contrat contrat, Objets objets, Date date);
     Quitance findByContratAndObjetAndEcheanceBetween(Contrat contrat, Objets objets, Date date1, Date date2);
     List<Quitance>  findByContratAndObjetAndEcheanceAfterOrderByEcheance(Contrat contrat, Objets objets, Date date);

     List<Quitance> getQuitansByContratAndAndObjetOrObjetOrObjet(Contrat contrat, Objets ob, Objets obj, Objets obje);
    List<Quitance> findByContratAndStatutQuitanceAndObjetInOrderByEcheance(Contrat contrat, StatutQuitance statutQuitance, Collection<Objets> objetsCollection);
    Quitance findFirstByContratOrderByEcheanceDesc(Contrat contrat);
     List<Quitance> findByContratAndStatutQuitanceOrderByEcheanceDesc(Contrat contrat, StatutQuitance statutQuitance);
     List<Quitance> getQuitancesByContratAndObjetIn(Contrat contrat, Collection<Objets> objetsCollection);
     List<Quitance> findByContratAndObjetIn(Contrat contrat, Collection<Objets> objetsCollection);
     List<Quitance> findByContratAndObjetInOrderByEcheanceDesc(Contrat contrat, Collection<Objets> objetsCollection);

    @Query("select q from Quitance q, Objets o, Contrat c where q.contrat =c.id and q.objet=o.id and o.id = 1 and c.id =:x order by q.echeance desc")
    List<Quitance> getQuittancesOfEmploye(@Param("x") Long id);

    @Query("SELECT q FROM Quitance q WHERE q.police = :police AND q.obj = :codeObjet AND q.dateComptable = :dateC AND q.dateCreation = :dateCr AND q.dateEmission = :dateEmis AND q.dateValeur = :dateV AND q.echeance = :echeance AND q.statut = :statut AND q.montantEmis = :montantEmis AND q.montantPaye = :montantpaye AND q.numQuittance = :numQuit")
    Quitance findBeforeProcessFile(
            @Param("police") String police,
            @Param("codeObjet") Long codeObjet,
            @Param("dateC") Date dateC,
            @Param("dateCr") Date dateCr,
            @Param("dateEmis") Date dateEmis,
            @Param("dateV") Date dateV,
            @Param("echeance") Date echeance,
            @Param("statut") Long statut,
            @Param("montantEmis") double montantEmis,
            @Param("montantpaye") double montantpaye,
            @Param("numQuit") String numQuit
    );

    // List<Quitance> findByO
    // List<Quitance> getQuitancesByContratAndObjetOrObjetOrObjetOrObjet(Contrat contrat,Objets ob,Objets obj,Objets obje,Objets objt);
}
