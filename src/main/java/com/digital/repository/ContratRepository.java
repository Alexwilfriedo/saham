package com.digital.repository;

import com.digital.model.Contrat;
import com.digital.model.Personne;
import com.digital.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat,Long>{
      List<Contrat> findAllByPolice(String police);
      public Contrat findByPolice(String police);
      public Contrat findFirstByPolice(String police);
      public Contrat findFirstByAssure(Personne personne);
      public List<Contrat> findBySouscripteur(Personne personne);
      //List<Contrat> findBySouscripteurOrAssure(Personne personne);
      public List<Contrat> findByAssure(Personne perso);
     // public List<Contrat> findByAssureAndId_souscrpteurIn(Personne assure,long[] refs);

      //public List<Contrat> findByAssureAndId_souscrpteurIn(Personne perso,Long[] refs);

      //recherche des etudiants page par page  dont le nom contient un mot clé mc
      @Query("select c from Contrat c where c.id_souscrpteur =:x")
      public Page<Contrat> getContratsOfSouscripteurPages(@Param("x") Long id_souscrpteur, Pageable pageable);

      @Query("select c from Contrat c where c.id_souscrpteur =:x or c.id_assure =:y")
      public Page<Contrat> getContratsOfSouscripteurOrAssurePages(@Param("x") Long id_souscrpteur, @Param("y") Long id_assure, Pageable pageable);

      @Query("select c from Contrat c where c.id_souscrpteur =:x or c.id_assure =:x")
      public List<Contrat> getContratsOfSouscripteurOrAssure(@Param("x") Long reference);


      @Query("select c from Contrat c where c.id_assure =:x")
      public Page<Contrat> getContratsOfAssurePages(@Param("x") Long id_assure, Pageable pageable);


      //total du jour
      @Query("select count (c.id) from Contrat c where c.id_souscrpteur =:x group by c.id_souscrpteur order by c.id_souscrpteur")
      Integer getContratNomberOfSouscripteur(@Param("x") Long id);

      @Query("select count (c.id) from Contrat c where c.id_assure =:x group by c.id_souscrpteur order by c.id_souscrpteur")
      Integer getContratNomberOfAssure(@Param("x") Long id);


      @Query("select max(q.echeance) from Contrat c, Quitance q where q.contrat = c.id and c.id_souscrpteur =:x group by c.id_souscrpteur order by c.id_souscrpteur")
      Date getdateOfLastEcheance(@Param("x") Long id);


      @Query("select  distinct p from Contrat c, Produit p where c.produit = p.id and c.id_souscrpteur =:x")
      List<Produit> getProduitCorporate(@Param("x") Long id);


      @Query("select c from Contrat c ,Personne p where c.assure = p.id and p.id=:x ")
      Contrat getContratOfEmploye(@Param("x") Long id);

      @Query("select c from Contrat c ,Personne p where c.assure = p.id and p.id=:x ")
      List<Contrat> findAllContratOfEmploye(@Param("x") Long id);

      /*@Query("SELECT c FROM Contrat c WHERE ")
      Contrat findContratBeforeProcessFile(@Param("code_statut_contrat") Long code_statut_contrat,
      @Param("codeperiodicite") Long codeperiodicite,
      @Param("dateEffet") Date dateEffet,
      @Param("datefin") Date datefin,
      @Param("dure") int dure,
      @Param("idassure") Long idassure,
      @Param("id_souscripteur") Long id_souscripteur,
      @Param("police") String police,
      @Param("prime") double prime,
      @Param("produitcode") Long produitcode)*/

}


