package com.digital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DesherenceContrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chContrat;
    private String nmCodiProduit;
    private String statutContrat;
    private Date dtEffetCrt;
    private int duree;
    private Date dtFinCrt;
    private String nomAssure;
    private Date dtNaisAssure;
    private Date dtNaisSouscript;
    private String nomSousc;
    private String periodicite;
    private String nmMontant;
    private String cellulaire;

    public DesherenceContrat(String chContrat, String nmCodiProduit, String statutContrat, Date dtEffetCrt, int duree, Date dtFinCrt, String nomAssure, Date dtNaisAssure, Date dtNaisSouscript, String nomSousc, String periodicite, String nmMontant, String cellulaire) {
        this.chContrat = chContrat;
        this.nmCodiProduit = nmCodiProduit;
        this.statutContrat = statutContrat;
        this.dtEffetCrt = dtEffetCrt;
        this.duree = duree;
        this.dtFinCrt = dtFinCrt;
        this.nomAssure = nomAssure;
        this.dtNaisAssure = dtNaisAssure;
        this.dtNaisSouscript = dtNaisSouscript;
        this.nomSousc = nomSousc;
        this.periodicite = periodicite;
        this.nmMontant = nmMontant;
        this.cellulaire = cellulaire;
    }

    public DesherenceContrat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChContrat() {
        return chContrat;
    }

    public void setChContrat(String chContrat) {
        this.chContrat = chContrat;
    }

    public String getNmCodiProduit() {
        return nmCodiProduit;
    }

    public void setNmCodiProduit(String nmCodiProduit) {
        this.nmCodiProduit = nmCodiProduit;
    }

    public String getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(String statutContrat) {
        this.statutContrat = statutContrat;
    }

    public Date getDtEffetCrt() {
        return dtEffetCrt;
    }

    public void setDtEffetCrt(Date dtEffetCrt) {
        this.dtEffetCrt = dtEffetCrt;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDtFinCrt() {
        return dtFinCrt;
    }

    public void setDtFinCrt(Date dtFinCrt) {
        this.dtFinCrt = dtFinCrt;
    }

    public String getNomAssure() {
        return nomAssure;
    }

    public void setNomAssure(String nomAssure) {
        this.nomAssure = nomAssure;
    }

    public Date getDtNaisAssure() {
        return dtNaisAssure;
    }

    public void setDtNaisAssure(Date dtNaisAssure) {
        this.dtNaisAssure = dtNaisAssure;
    }

    public Date getDtNaisSouscript() {
        return dtNaisSouscript;
    }

    public void setDtNaisSouscript(Date dtNaisSouscript) {
        this.dtNaisSouscript = dtNaisSouscript;
    }

    public String getNomSousc() {
        return nomSousc;
    }

    public void setNomSousc(String nomSousc) {
        this.nomSousc = nomSousc;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getNmMontant() {
        return nmMontant;
    }

    public void setNmMontant(String nmMontant) {
        this.nmMontant = nmMontant;
    }

    public String getCellulaire() {
        return cellulaire;
    }

    public void setCellulaire(String cellulaire) {
        this.cellulaire = cellulaire;
    }
}
