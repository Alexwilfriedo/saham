package com.digital.model.response;

public class AskedRachatTotalCorporate {

    private Long idContrat;
    private String numContrat;
    private String personne;
    private String ville;

    public AskedRachatTotalCorporate(Long idContrat, String personne, String ville) {
        this.idContrat = idContrat;
        this.personne = personne;
        this.ville = ville;
    }

    public AskedRachatTotalCorporate() {
    }

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


}
