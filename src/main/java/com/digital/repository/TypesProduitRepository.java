package com.digital.repository;

import com.digital.model.TypesProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesProduitRepository extends JpaRepository<TypesProduit,Long>{
}
