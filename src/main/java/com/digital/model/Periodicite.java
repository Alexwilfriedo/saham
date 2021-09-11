package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Periodicite{

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "periodicite")
    private String periodicite;

    @CsvBindByName(column = "code")
    private Long code;

    @JsonIgnore
    @OneToMany(mappedBy = "periodicite",fetch = FetchType.LAZY)
    private Collection<Contrat> contrats;


    public Periodicite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Periodicite(String periodicite, Long code) {
        this.periodicite = periodicite;
        this.code = code;
    }

    public Periodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    @JsonIgnore
    public Collection<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(Collection<Contrat> contrats) {
        this.contrats = contrats;
    }
}
