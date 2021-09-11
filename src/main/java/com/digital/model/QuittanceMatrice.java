package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuittanceMatrice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CsvBindByName(column = "code_contrat")
    private String codeContrat;

    @CsvBindByName(column = "societe")
    private String societe;

    @CsvBindByName(column = "code_produit")
    private Long codeProduit;

    @CsvBindByName(column = "typecontrat")

    private String typeContrat;

    @CsvBindByName(column = "janvier")
    @CsvNumber(value = "")
    private double janvier;

    @CsvBindByName(column = "fevrier")
    @CsvNumber(value = "")
    private double fevrier;

    @CsvBindByName(column = "mars")
    @CsvNumber(value = "")
    private double mars;

    @CsvBindByName(column = "avril")
    @CsvNumber(value = "")
    private double avril;

    @CsvBindByName(column = "mai")
    @CsvNumber(value = "")
    private double mai;

    @CsvBindByName(column = "juin")
    @CsvNumber(value = "")
    private double juin;

    @CsvBindByName(column = "juillet")
    @CsvNumber(value = "")
    private double juillet;

    @CsvBindByName(column = "aout")
    @CsvNumber(value = "")
    private double aout;

    @CsvBindByName(column = "septembre")
    @CsvNumber(value = "")
    private double septembre;

    @CsvBindByName(column = "octobre")
    @CsvNumber(value = "")
    private double octobre;

    @CsvBindByName(column = "novembre")
    @CsvNumber(value = "")
    private double novembre;

    @CsvBindByName(column = "decembre")
    @CsvNumber(value = "")
    private double deceembre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeContrat() {
        return codeContrat;
    }

    public void setCodeContrat(String codeContrat) {
        this.codeContrat = codeContrat;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public Long getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(Long codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public double getJanvier() {
        return janvier;
    }

    public void setJanvier(double janvier) {
        this.janvier = janvier;
    }

    public double getFevrier() {
        return fevrier;
    }

    public void setFevrier(double fevrier) {
        this.fevrier = fevrier;
    }

    public double getMars() {
        return mars;
    }

    public void setMars(double mars) {
        this.mars = mars;
    }

    public double getAvril() {
        return avril;
    }

    public void setAvril(double avril) {
        this.avril = avril;
    }

    public double getMai() {
        return mai;
    }

    public void setMai(double mai) {
        this.mai = mai;
    }

    public double getJuin() {
        return juin;
    }

    public void setJuin(double juin) {
        this.juin = juin;
    }

    public double getJuillet() {
        return juillet;
    }

    public void setJuillet(double juillet) {
        this.juillet = juillet;
    }

    public double getAout() {
        return aout;
    }

    public void setAout(double aout) {
        this.aout = aout;
    }

    public double getSeptembre() {
        return septembre;
    }

    public void setSeptembre(double septembre) {
        this.septembre = septembre;
    }

    public double getOctobre() {
        return octobre;
    }

    public void setOctobre(double octobre) {
        this.octobre = octobre;
    }

    public double getNovembre() {
        return novembre;
    }

    public void setNovembre(double novembre) {
        this.novembre = novembre;
    }

    public double getDeceembre() {
        return deceembre;
    }

    public void setDeceembre(double deceembre) {
        this.deceembre = deceembre;
    }

    @Override
    public String toString() {
        return "QuittanceMatrice{" +
                "id=" + id +
                ", codeContrat='" + codeContrat + '\'' +
                ", societe='" + societe + '\'' +
                ", codeProduit=" + codeProduit +
                ", typeContrat='" + typeContrat + '\'' +
                ", janvier=" + janvier +
                ", fevrier=" + fevrier +
                ", mars=" + mars +
                ", avril=" + avril +
                ", mai=" + mai +
                ", juin=" + juin +
                ", juillet=" + juillet +
                ", aout=" + aout +
                ", septembre=" + septembre +
                ", octobre=" + octobre +
                ", novembre=" + novembre +
                ", deceembre=" + deceembre +
                '}';
    }
}
