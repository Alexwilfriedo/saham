package com.digital.model.api;

import com.digital.model.response.EtatAnnuel;
import com.digital.model.response.MonthAndAmount;

public class SituationCompteAnnuelle {
    private String contrat;
    private double cumulVersement ;
    private double prime;
    private EtatAnnuel etatAnnuel;

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public EtatAnnuel getEtatAnnuel() {
        return etatAnnuel;
    }

    public void setEtatAnnuel(EtatAnnuel etatAnnuel) {
        this.etatAnnuel = etatAnnuel;
        for (MonthAndAmount monthAndAmount : etatAnnuel.getMonthAndAmounts()){
            setCumulVersement(monthAndAmount.getAmount());
        }

    }

    public double getCumulVersement() {
        return cumulVersement;
    }

    public void setCumulVersement(double cumulVersement) {
        this.cumulVersement += cumulVersement;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }
}
