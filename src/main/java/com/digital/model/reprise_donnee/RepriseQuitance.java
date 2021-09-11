package com.digital.model.reprise_donnee;

import com.digital.model.common.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RepriseQuitance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Long objet ;

    private Long statutQuitance ;
    //DT_CREATION;ORDRE

    @Column(name = "num_quittance")
   private String numQuittance;


    @JoinColumn(name = "code_contrat")
    private String contrat ;


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

    public RepriseQuitance() {
    }

    public RepriseQuitance(Long objet, Long statutQuitance, String numQuittance, String contrat, Date echeance, Date dateValeur, Date dateCreation, Date dateComptable, Date dateEmission, double montantPaye, double montantEmis) {
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

    public Long getObjet() {
        return objet;
    }

    public void setObjet(Long objet) {
        this.objet = objet;
    }

    public Long getStatutQuitance() {
        return statutQuitance;
    }

    public void setStatutQuitance(Long statutQuitance) {
        this.statutQuitance = statutQuitance;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }
}
