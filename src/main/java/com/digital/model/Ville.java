package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Ville{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String nomVille;


    public Ville() {
    }

    public Ville(String nomVille) {
        this.nomVille = nomVille;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }


}
