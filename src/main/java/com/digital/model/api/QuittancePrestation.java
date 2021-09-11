package com.digital.model.api;

import com.digital.model.Quitance;

import java.util.List;

public class QuittancePrestation {

    private String contrat;

    private Quitance quitance;

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat)
    {
        this.contrat = contrat;
    }

    public Quitance getQuitance() {
        return quitance;
    }

    public void setQuitance(Quitance quitance) {
        this.quitance = quitance;
    }
}
