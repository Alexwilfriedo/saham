package com.digital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DemandeContrat {

    @Id
    @Column(name = "id_contrat")
    private Long id;

    private String police;
    private String assure;
    private Long souscripteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getAssure() {
        return assure;
    }

    public void setAssure(String assure) {
        this.assure = assure;
    }

    public Long getSouscripteur() {
        return souscripteur;
    }

    public void setSouscripteur(Long souscripteur) {
        this.souscripteur = souscripteur;
    }

    public DemandeContrat() {
    }

    public DemandeContrat(Long id, String police, String assure, Long souscripteur) {
        this.id = id;
        this.police = police;
        this.assure = assure;
        this.souscripteur = souscripteur;
    }
}
