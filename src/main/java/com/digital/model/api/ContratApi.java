package com.digital.model.api;

import com.digital.model.Contrat;

public class ContratApi {

    private Contrat contrat;
    private double cumulPaye;
    private double cumulImpaye;

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public double getCumulPaye() {
        return cumulPaye;
    }

    public void setCumulPaye(double cumulPaye) {
        this.cumulPaye = cumulPaye;
    }

    public double getCumulImpaye() {
        return cumulImpaye;
    }

    public void setCumulImpaye(double cumulImpaye) {
        this.cumulImpaye = cumulImpaye;
    }

}
