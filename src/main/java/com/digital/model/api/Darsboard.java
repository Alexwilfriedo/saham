package com.digital.model.api;

import com.digital.model.response.Darshboard;

import java.util.ArrayList;
import java.util.List;

public class Darsboard {
    private double cumul_impaye = 0;
    private double cumul_total_impaye = 0;
    private double cumul_total_paye = 0;
    private double cumul_paye =0;
    private double cumul_instance;
    private int statut_impaye = 0;
    private int statut_paye = 0 ;
    private int statut_instance =0;
    private int total_contrat = 0;
    private List<Darshboard> darshboardsContrat;

    public double getCumul_impaye() {
        return cumul_impaye;
    }

    public void setCumul_impaye(double cumul_impaye) {
        this.cumul_impaye = cumul_impaye;
    }

    public double getCumul_total_impaye() {
        return cumul_total_impaye;
    }

    public void setCumul_total_impaye(double cumul_total_impaye) {
        this.cumul_total_impaye = cumul_total_impaye;
    }

    public double getCumul_total_paye() {
        return cumul_total_paye;
    }

    public void setCumul_total_paye(double cumul_total_paye) {
        this.cumul_total_paye = cumul_total_paye;
    }

    public double getCumul_paye() {
        return cumul_paye;
    }

    public void setCumul_paye(double cumul_paye) {
        this.cumul_paye = cumul_paye;
    }

    public double getCumul_instance() {
        return cumul_instance;
    }

    public void setCumul_instance(double cumul_instance) {
        this.cumul_instance = cumul_instance;
    }

    public int getStatut_impaye() {
        return statut_impaye;
    }

    public void setStatut_impaye(int statut_impaye) {
        this.statut_impaye = statut_impaye;
    }

    public int getStatut_paye() {
        return statut_paye;
    }

    public void setStatut_paye(int statut_paye) {
        this.statut_paye = statut_paye;
    }

    public int getStatut_instance() {
        return statut_instance;
    }

    public void setStatut_instance(int statut_instance) {
        this.statut_instance = statut_instance;
    }

    public int getTotal_contrat() {
        return total_contrat;
    }

    public void setTotal_contrat(int total_contrat) {
        this.total_contrat = total_contrat;
    }

    public List<Darshboard> getDarshboardsContrat() {
        return darshboardsContrat;
    }

    public void setDarshboardsContrat(List<Darshboard> darshboardsContrat) {
        this.darshboardsContrat = darshboardsContrat;
    }
}
