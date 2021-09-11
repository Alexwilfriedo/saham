package com.digital.model.tchat;

public class Greeting {

    private String expediteur;// reference de l'expediteur
    private String contenu;
    private String destinataire;// reference du destinateur

    public Greeting() {
    }

    public Greeting(String expediteur, String contenu, String destinataire) {
        this.expediteur = expediteur;
        this.contenu = contenu;
        this.destinataire = destinataire;
    }


    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
