package com.digital.model.api;


import com.digital.model.Quitance;

import java.util.List;

public class ReleveApi {

    private String contrat;
    private List<Quitance> impayes;

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public List<Quitance> getImpayes() {
        return impayes;
    }

    public void setImpayes(List<Quitance> impayes) {
        this.impayes = impayes;
    }


}
