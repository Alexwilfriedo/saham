package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Bancassurance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private int code_bancas;
    private String bancas;

 /*   @OneToMany(mappedBy = "bancassurance",fetch = FetchType.LAZY)
    private Collection<Produit> produits;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bancassurance() {
    }

    public Bancassurance(int code_bancas, String bancas) {
        this.code_bancas = code_bancas;
        this.bancas = bancas;
    }
  /*  public Collection<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }*/

    public int getCode_bancas() {
        return code_bancas;
    }

    public void setCode_bancas(int code_bancas) {
        this.code_bancas = code_bancas;
    }

    public String getBancas() {
        return bancas;
    }

    public void setBancas(String bancas) {
        this.bancas = bancas;
    }
}

