package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Objets  {

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "nomobjet")
    private String nomObjet;

    @CsvBindByName(column = "code")
    private int code;

    @OneToMany(mappedBy = "objet",fetch = FetchType.LAZY)
    private Collection<Quitance> quitances;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Objets() {
    }

    public Objets(int code,String nomObjet) {
        this.nomObjet = nomObjet;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Objets(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    @JsonIgnore
    public Collection<Quitance> getQuitances() {
        return quitances;
    }

    public void setQuitances(Collection<Quitance> quitances) {
        this.quitances = quitances;
    }
}
