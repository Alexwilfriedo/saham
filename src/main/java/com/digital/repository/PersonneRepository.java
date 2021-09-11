package com.digital.repository;

import com.digital.model.Personne;
import com.digital.model.common.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonneRepository extends JpaRepository<Personne,Long>{
     //Personne findById_pers(Long idpers);
     public  Personne findByReferenceAndFirstnameIsNotNullAndLastnameIsNotNull(Long ref);
     public Personne findFirstByReference(Long ref);
     public Personne findByReference(Long ref);

     public Personne findByBirthdate(Date birthdate);



     @Query("select p from Personne p, Contrat c where c.assure = p.id and c.id_souscrpteur =:x and (upper(p.firstname) like upper(:mc) or upper(p.matricule) like upper(:mc)) order by p.lastname")
     Page<Personne> getEmployes(@Param("x") Long id_souscrpteur, @Param("mc") String motCle, Pageable pageable);
}
