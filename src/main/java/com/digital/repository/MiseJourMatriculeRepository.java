package com.digital.repository;

import com.digital.model.reprise_donnee.MiseJourMatricule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiseJourMatriculeRepository extends JpaRepository<MiseJourMatricule,Long>{
}
