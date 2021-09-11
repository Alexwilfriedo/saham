package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Civilite {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String civilite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Civilite() {
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }


}
