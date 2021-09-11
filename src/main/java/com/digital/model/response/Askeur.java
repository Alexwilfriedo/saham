package com.digital.model.response;

import com.digital.model.Contrat;
import com.digital.model.Personne;

public class Askeur {
    private Personne personne;
    private Contrat contrat;

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }
}
