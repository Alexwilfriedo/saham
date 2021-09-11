package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Operation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date date;
    private double montant;
    private String libelle;
    private String username;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Operation(Date date, double montant, String libelle) {
        this.date = date;
        this.montant = montant;
        this.libelle = libelle;
    }

    public Operation(Date date, double montant, String libelle, String username) {
        this.date = date;
        this.montant = montant;
        this.libelle = libelle;
        this.username = username;
    }
}
