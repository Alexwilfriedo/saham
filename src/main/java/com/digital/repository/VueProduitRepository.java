package com.digital.repository;

import com.digital.model.VueProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VueProduitRepository extends JpaRepository<VueProduit,Long> {

    List<VueProduit> findByIdeBrancheAndIdeTypeAndCodeIsNotIn(Long idb, Long idt, Long[] codes);
    List<VueProduit> findByIdeBrancheAndIdeType(Long idb, Long idt);
}
