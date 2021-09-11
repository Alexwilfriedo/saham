package com.digital.repository;

import com.digital.model.Branche;
import com.digital.model.Produit;
import com.digital.model.TypesProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

    public Produit findByCode(Long id);
    public Produit findFirstByCode(Long id);

    public List<Produit> findByCodeIn(Long[] code);

    public List<Produit> findProduitsByBrancheAndTypesProduit(Branche branche, TypesProduit typesProduit);
    public List<Produit> findProduitsByBrancheAndTypesProduitAndCodeIsNotIn(Branche branche, TypesProduit typesProduit, Long[] codes);

    public List<Produit> findByBrancheAndTypesProduit(Branche branche, TypesProduit typesProduit);

}
