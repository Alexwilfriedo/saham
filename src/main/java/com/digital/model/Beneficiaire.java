package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Beneficiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Long reference;

    @ManyToOne
    @JoinColumn(name = "id_contrat")
    @JsonIgnore
    private Contrat contrat;

    private String benefVie;

    private String benefdeces;

    public Beneficiaire() {
    }

    public Beneficiaire(Long reference, Contrat contrat) {
        this.reference = reference;
        this.contrat = contrat;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public String getBenefVie() {
        return benefVie;
    }

    public void setBenefVie(String benefVie) {
        this.benefVie = benefVie;
    }

    public String getBenefdeces() {
        return benefdeces;
    }

    public void setBenefdeces(String benefdeces) {
        this.benefdeces = benefdeces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
