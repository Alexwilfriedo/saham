package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Branche {

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "branche")
    private String branche;

    @OneToMany(mappedBy = "branche",fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public Branche(String branche) {
        this.branche = branche;
    }

    public Branche() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getBranche() {
        return branche;
    }

    public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }
}
