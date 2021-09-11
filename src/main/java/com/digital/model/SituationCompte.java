package com.digital.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SituationCompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CsvBindByName(column = "assure")
    private String assure;

    @CsvBindByName(column = "capitalacquisjanv")
    private double capitalAcquisJanv; //Provision Mathematique

    @CsvBindByName(column = "capitalau")
    private double capitalAu;

    @CsvBindByName(column = "codecontrat")
    private String codeContrat;

    @CsvBindByName(column = "contractant")
    private String contractant;

    @CsvBindByName(column = "cotisationannuelle")
    private double cotisationAnnuelle;

    @CsvBindByName(column = "datesituation")
    @CsvDate(value = "MM/dd/yyyy")
    private Date dateSituation;

    @CsvBindByName(column = "interet")
    private double interet;

    @CsvBindByName(column = "periodicite")
    private String periodicite;


    @CsvBindByName(column = "prestation")
    private double prestation;

    @CsvBindByName(column = "primenette")
    private double primeNette;

    @CsvBindByName(column = "tauxrevaloepargne")
    private String tauxRevaloEpargne;

    @CsvBindByName(column = "typecontrat")
    private String typeContrat;

    private double cumulPrimeAu31122016;

    public SituationCompte() {
    }

    public SituationCompte(String codeContrat, String typeContrat, String periodicite, String contractant, String assure, Date dateSituation, double cotisationAnnuelle, double capitalAcquisJanv, double primeNette, double interet, double prestation, double capitalAu, String tauxRevaloEpargne) {
        this.codeContrat = codeContrat;
        this.typeContrat = typeContrat;
        this.periodicite = periodicite;
        this.contractant = contractant;
        this.assure = assure;
        this.dateSituation = dateSituation;
        this.cotisationAnnuelle = cotisationAnnuelle;
        this.capitalAcquisJanv = capitalAcquisJanv;
        this.primeNette = primeNette;
        this.interet = interet;
        this.prestation = prestation;
        this.capitalAu = capitalAu;
        this.tauxRevaloEpargne = tauxRevaloEpargne;
    }
    public SituationCompte(String codeContrat, String typeContrat, String periodicite, String contractant, String assure, Date dateSituation, double cotisationAnnuelle, double capitalAcquisJanv, double cumulPrimeAu31122016, double primeNette, double interet, double prestation, double capitalAu, String tauxRevaloEpargne) {
        this.codeContrat = codeContrat;
        this.typeContrat = typeContrat;
        this.periodicite = periodicite;
        this.contractant = contractant;
        this.assure = assure;
        this.dateSituation = dateSituation;
        this.cotisationAnnuelle = cotisationAnnuelle;
        this.capitalAcquisJanv = capitalAcquisJanv;
        this.cumulPrimeAu31122016 = cumulPrimeAu31122016;
        this.primeNette = primeNette;
        this.interet = interet;
        this.prestation = prestation;
        this.capitalAu = capitalAu;
        this.tauxRevaloEpargne = tauxRevaloEpargne;
    }

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

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getContractant() {
        return contractant;
    }

    public void setContractant(String contractant) {
        this.contractant = contractant;
    }

    public String getAssure() {
        return assure;
    }

    public void setAssure(String assure) {
        this.assure = assure;
    }

    public Date getDateSituation() {
        return dateSituation;
    }

    public void setDateSituation(Date dateSituation) {
        this.dateSituation = dateSituation;
    }

    public double getCotisationAnnuelle() {
        return cotisationAnnuelle;
    }

    public void setCotisationAnnuelle(double cotisationAnnuelle) {
        this.cotisationAnnuelle = cotisationAnnuelle;
    }

    public double getCapitalAcquisJanv() {
        return capitalAcquisJanv;
    }

    public void setCapitalAcquisJanv(double capitalAcquisJanv) {
        this.capitalAcquisJanv = capitalAcquisJanv;
    }

    public double getPrimeNette() {
        return primeNette;
    }

    public void setPrimeNette(double primeNette) {
        this.primeNette = primeNette;
    }

    public double getInteret() {
        return interet;
    }

    public void setInteret(double interet) {
        this.interet = interet;
    }

    public double getPrestation() {
        return prestation;
    }

    public void setPrestation(double prestation) {
        this.prestation = prestation;
    }

    public double getCapitalAu() {
        return capitalAu;
    }

    public void setCapitalAu(double capitalAu) {
        this.capitalAu = capitalAu;
    }


    public double getCumulPrimeAu31122016() {
        return cumulPrimeAu31122016;
    }

    public void setCumulPrimeAu31122016(double cumulPrimeAu31122016) {
        this.cumulPrimeAu31122016 = cumulPrimeAu31122016;
    }

    public String getTauxRevaloEpargne() {
        return tauxRevaloEpargne;
    }

    public void setTauxRevaloEpargne(String tauxRevaloEpargne) {
        this.tauxRevaloEpargne = tauxRevaloEpargne;
    }

    @Override
    public String toString() {
        return "SituationCompte{" +
                "id=" + id +
                ", assure='" + assure + '\'' +
                ", capitalAcquisJanv=" + capitalAcquisJanv +
                ", capitalAu=" + capitalAu +
                ", codeContrat='" + codeContrat + '\'' +
                ", contractant='" + contractant + '\'' +
                ", cotisationAnnuelle=" + cotisationAnnuelle +
                ", dateSituation=" + dateSituation +
                ", interet=" + interet +
                ", periodicite='" + periodicite + '\'' +
                ", prestation=" + prestation +
                ", primeNette=" + primeNette +
                ", tauxRevaloEpargne='" + tauxRevaloEpargne + '\'' +
                ", typeContrat='" + typeContrat + '\'' +
                ", cumulPrimeAu31122016=" + cumulPrimeAu31122016 +
                '}';
    }
}
