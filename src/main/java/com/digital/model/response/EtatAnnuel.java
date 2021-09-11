package com.digital.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class EtatAnnuel {

    @Id
    private Long id;
    private Date date;
    private String annee;
    @JsonIgnore
    private List<MonthAndAmount> monthAndAmounts;
    private double totalcotise;
    private double valeurDeRachat;
    private Date valeurDeRachatYear;
    private double capitalAcquis; //= pm
    private Date capitalAcquisYear;
    private double capitalDeces; // = pm
    private Date capitalDecesYear;
    private double valeurReduction;
    private Date valeurReductionYear;
    private double primeFidelite;
    private double capitalRetraite;
    private Date capitalRetraiteYear;
    private double rachatPartiel;
    private Date rachatPartielYear;

    public EtatAnnuel(Date date, String annee) {
        this.date = date;
        this.annee = annee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    @JsonIgnore
    public List<MonthAndAmount> getMonthAndAmounts() {
        return monthAndAmounts;
    }

    public void setMonthAndAmounts(List<MonthAndAmount> monthAndAmounts) {
        this.monthAndAmounts = monthAndAmounts;
        for (MonthAndAmount monthAndAmount : monthAndAmounts){
            this.totalcotise = this.totalcotise+monthAndAmount.getAmount();
        }
    }

    public double getTotalcotise() {
        return totalcotise;
    }

    public void setTotalcotise(double totalcotise) {
        this.totalcotise = totalcotise;
    }

    public double getValeurDeRachat() {
        return valeurDeRachat;
    }

    public void setValeurDeRachat(double valeurDeRachat) {
        this.valeurDeRachat = valeurDeRachat;
        this.capitalAcquis = valeurDeRachat;
        this.capitalDeces = valeurDeRachat;
        this.capitalRetraite = valeurDeRachat;
    }

    public Date getValeurDeRachatYear() {
        return valeurDeRachatYear;
    }

    public void setValeurDeRachatYear(Date valeurDeRachatYear) {
        this.valeurDeRachatYear = valeurDeRachatYear;
        this.capitalAcquisYear = valeurDeRachatYear;
        this.capitalDecesYear = valeurDeRachatYear;
    }

    public double getCapitalAcquis() {
        return capitalAcquis;
    }

    public void setCapitalAcquis(double capitalAcquis) {
        this.capitalAcquis = capitalAcquis;
    }

    public Date getCapitalAcquisYear() {
        return capitalAcquisYear;
    }

    public void setCapitalAcquisYear(Date capitalAcquisYear) {
        this.capitalAcquisYear = capitalAcquisYear;
    }

    public double getCapitalDeces() {
        return capitalDeces;
    }

    public void setCapitalDeces(double capitalDeces) {
        this.capitalDeces = capitalDeces;
    }

    public Date getCapitalDecesYear() {
        return capitalDecesYear;
    }

    public void setCapitalDecesYear(Date capitalDecesYear) {
        this.capitalDecesYear = capitalDecesYear;
    }

    public double getValeurReduction() {
        return valeurReduction;
    }

    public void setValeurReduction(double valeurReduction) {
        this.valeurReduction = valeurReduction;
    }

    public Date getValeurReductionYear() {
        return valeurReductionYear;
    }

    public void setValeurReductionYear(Date valeurReductionYear) {
        this.valeurReductionYear = valeurReductionYear;
    }

    public double getPrimeFidelite() {
        return primeFidelite;
    }

    public void setPrimeFidelite(double primeFidelite) {
        this.primeFidelite = primeFidelite;
    }

    public double getCapitalRetraite() {
        return capitalRetraite;
    }

    public void setCapitalRetraite(double capitalRetraite) {
        this.capitalRetraite = capitalRetraite;
    }

    public Date getCapitalRetraiteYear() {
        return capitalRetraiteYear;
    }

    public void setCapitalRetraiteYear(Date capitalRetraiteYear) {
        this.capitalRetraiteYear = capitalRetraiteYear;
    }

    public double getRachatPartiel() {
        return rachatPartiel;
    }

    public void setRachatPartiel(double rachatPartiel) {
        this.rachatPartiel = rachatPartiel;
    }

    public Date getRachatPartielYear() {
        return rachatPartielYear;
    }

    public void setRachatPartielYear(Date rachatPartielYear) {
        this.rachatPartielYear = rachatPartielYear;
    }
}
