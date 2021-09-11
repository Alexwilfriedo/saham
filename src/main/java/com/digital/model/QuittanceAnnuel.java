package com.digital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class QuittanceAnnuel {

    @Id
    private Long id;

    private Date dateComptable;

    @Column(name = "dt_creation")
    private Date dateCreation;

    private Date dateEmission;

    private Date dateValeur;

    private Date echeance;

    @Column(name = "montant_emis")
    private double montantEmis;

    @Column(name = "montant_paye")
    private double montantPaye;

    @Column(name = "num_quittance")
    private String numQuittance;

    @Column(name = "idcontrat")
    private Long idContrat;

    @Column(name = "code_contrat")
    private String contrat ;

    @Column(name = "idobject")
    protected Long idObject;

    private String nomObjet;

    private int code;

    @JoinColumn(name = "id_statut_Quit")
    private Long idStatutQuit ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateComptable() {
        return dateComptable;
    }

    public void setDateComptable(Date dateComptable) {
        this.dateComptable = dateComptable;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(Date dateValeur) {
        this.dateValeur = dateValeur;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public double getMontantEmis() {
        return montantEmis;
    }

    public void setMontantEmis(double montantEmis) {
        this.montantEmis = montantEmis;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public String getNumQuittance() {
        return numQuittance;
    }

    public void setNumQuittance(String numQuittance) {
        this.numQuittance = numQuittance;
    }

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public Long getIdObject() {
        return idObject;
    }

    public void setIdObject(Long idObject) {
        this.idObject = idObject;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getIdStatutQuit() {
        return idStatutQuit;
    }

    public void setIdStatutQuit(Long idStatutQuit) {
        this.idStatutQuit = idStatutQuit;
    }
}
