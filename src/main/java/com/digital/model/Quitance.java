package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Quitance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @ManyToOne
    @JoinColumn(name = "code_objet")
    private Objets objet ;

    @ManyToOne
    @JoinColumn(name = "id_statutQuit")
    private StatutQuitance statutQuitance ;
    //DT_CREATION;ORDRE

    @Column(name = "num_quittance")
   private String numQuittance;


    @ManyToOne
    @JoinColumn(name = "code_contrat")
    private Contrat contrat ;

    private String police;

     private Long obj; //objet
     private Long statut; //objet

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public Long getObj() {
        return obj;
    }

    public void setObj(Long obj) {
        this.obj = obj;
    }

    public Long getStatut() {
        return statut;
    }

    public void setStatut(Long statut) {
        this.statut = statut;
    }

    private Date echeance;

    private Date dateValeur;

    @Column(name = "dt_creation")
    private Date dateCreation;

    private Date dateComptable;

    private Date dateEmission;

    @Column(name = "montant_paye")
    private double montantPaye;

    @Column(name = "montant_emis")
    private double montantEmis;

    public Quitance() {
    }

    public Quitance(Objets objet, StatutQuitance statutQuitance, String numQuittance, Contrat contrat,Date echeance, Date dateValeur, Date dateCreation, Date dateComptable, Date dateEmission, double montantPaye, double montantEmis) {
        this.objet = objet;
        this.statutQuitance = statutQuitance;
        this.numQuittance = numQuittance;
        this.contrat = contrat;
        this.echeance = echeance;
        this.dateValeur = dateValeur;
        this.dateCreation = dateCreation;
        this.dateComptable = dateComptable;
        this.dateEmission = dateEmission;
        this.montantPaye = montantPaye;
        this.montantEmis = montantEmis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumQuittance() {
        return numQuittance;
    }

    public void setNumQuittance(String numQuittance) {
        this.numQuittance = numQuittance;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public double getMontantEmis() {
        return montantEmis;
    }

    public void setMontantEmis(double montantEmis) {
        this.montantEmis = montantEmis;
    }

    public Objets getObjet() {
        return objet;
    }

    public void setObjet(Objets objet) {
        this.objet = objet;
    }

    public StatutQuitance getStatutQuitance() {
        return statutQuitance;
    }

    public void setStatutQuitance(StatutQuitance statutQuitance) {
        this.statutQuitance = statutQuitance;
    }

    @JsonIgnore
    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public Date getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(Date dateValeur) {
        this.dateValeur = dateValeur;
    }

    public Date getDateComptable() {
        return dateComptable;
    }

    public void setDateComptable(Date dateComptable) {
        this.dateComptable = dateComptable;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    @Override
    public String toString() {
        return "Quitance{" +
                "id=" + id +
                ", numQuittance='" + numQuittance + '\'' +
                ", contrat=" + contrat +
                ", police='" + police + '\'' +
                ", obj=" + obj +
                ", statut=" + statut +
                ", echeance=" + echeance +
                ", dateValeur=" + dateValeur +
                ", dateCreation=" + dateCreation +
                ", dateComptable=" + dateComptable +
                ", dateEmission=" + dateEmission +
                ", montantPaye=" + montantPaye +
                ", montantEmis=" + montantEmis +
                '}';
    }
}
