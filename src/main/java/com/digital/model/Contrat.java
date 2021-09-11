package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
public class Contrat{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_periodicite")
    private Periodicite periodicite ;

    private Long codePeriodicite;

    @ManyToOne
    @JoinColumn(name = "code_statut_contrat")
    private StatutContrat statutContrat ;

    private Long id_statut;

    @ManyToOne
    private Personne souscripteur;

    private Long id_souscrpteur;

    private Long id_assure;

    @ManyToOne
    private Personne assure ;

    @JsonIgnore
    @OneToMany(mappedBy = "contrat",fetch = FetchType.LAZY)
    private Collection<Quitance> quitances;

    public void setCodePeriodicite(Long codePeriodicite) {
        this.codePeriodicite = codePeriodicite;
    }

    public Long getId_statut() {
        return id_statut;
    }

    public void setId_statut(Long id_statut) {
        this.id_statut = id_statut;
    }

    public void setProduitCode(Long produitCode) {
        this.produitCode = produitCode;
    }

    @OneToMany(mappedBy = "contrat",fetch = FetchType.LAZY)
    private Collection<Beneficiaire> beneficiaires;

    @ManyToOne
    @JoinColumn(name = "code_produit")
    private Produit produit ;

    private Long produitCode;

    private Date dateEffet;

    private int duree;

    @Column(nullable = true ,name = "code_contrat")
    private String police;

    private Date dateFin;

    private double prime;

    public Contrat() {
    }

    public Contrat(Personne souscripteur, Personne assure, Produit produit, Date dateEffet, int duree, Date dateFin, double prime) {
        this.souscripteur = souscripteur;
        this.assure = assure;
        this.produit = produit;
        this.dateEffet = dateEffet;
        this.duree = duree;
        this.dateFin = dateFin;
        this.prime = prime;
    }


    public Long getProduitCode() {
        return produitCode;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public Periodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
        this.codePeriodicite = periodicite.getCode();
    }


    public StatutContrat getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(StatutContrat statutContrat) {
        this.statutContrat = statutContrat;
    }

    public Personne getSouscripteur() {
        return souscripteur;
    }

    public void setSouscripteur(Personne souscripteur) {
        this.souscripteur = souscripteur;
    }


    public Personne getAssure() {
        return assure;
    }

    public void setAssure(Personne assure) {
        this.assure = assure;
    }

     @JsonIgnore
    public Collection<Quitance> getQuitances() {
        return quitances;
    }

    public void setQuitances(Collection<Quitance> quitances) {
        this.quitances = quitances;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
        this.produitCode = produit.getCode();
    }

    public Long getCodePeriodicite() {
        return codePeriodicite;
    }


    public Date getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(Date dateEffet) {
        this.dateEffet = dateEffet;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }

    public Long getId_souscrpteur() {
        return id_souscrpteur;
    }

    public void setId_souscrpteur(Long id_souscrpteur) {
        this.id_souscrpteur = id_souscrpteur;
    }

    public Long getId_assure() {
        return id_assure;
    }

    public void setId_assure(Long id_assure) {
        this.id_assure = id_assure;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", codePeriodicite=" + codePeriodicite +
                ", id_statut=" + id_statut +
                ", id_souscrpteur=" + id_souscrpteur +
                ", id_assure=" + id_assure +
                ", produitCode=" + produitCode +
                ", dateEffet=" + dateEffet +
                ", duree=" + duree +
                ", police='" + police + '\'' +
                ", dateFin=" + dateFin +
                ", prime=" + prime +
                '}';
    }
}
