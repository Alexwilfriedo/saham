package com.digital.model.api;

import com.digital.model.Quitance;

public class SituationCompteActuel {

    private String contrat;
    private Quitance valeurDeRachat;
    private Quitance valeurDeReduction;
    private Quitance pm; //provision mathematique

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public Quitance getValeurDeRachat() {
        return valeurDeRachat;
    }

    public void setValeurDeRachat(Quitance valeurDeRachat) {
        this.valeurDeRachat = valeurDeRachat;
    }

    public Quitance getValeurDeReduction() {
        return valeurDeReduction;
    }

    public void setValeurDeReduction(Quitance valeurDeReduction) {
        this.valeurDeReduction = valeurDeReduction;
    }

    public Quitance getPm() {
        return pm;
    }

    public void setPm(Quitance pm) {
        this.pm = pm;
    }
}
