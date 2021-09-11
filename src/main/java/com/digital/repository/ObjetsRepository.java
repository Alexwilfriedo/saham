package com.digital.repository;

import com.digital.model.Objets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ObjetsRepository extends JpaRepository<Objets,Long>{
    public Objets findFirstByNomObjet(String obj);
    public Objets findByCode(int code);
    public Collection<Objets> findByNomObjetIn(List<String> nomObjet);
    public Collection<Objets> findByCodeIn(int[] code);
}
