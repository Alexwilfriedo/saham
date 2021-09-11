package com.digital.model.tchat;

import com.digital.model.User;
import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private User expediteur ;


    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private User destinataire ;

    public String content;
    public boolean state; //statut du message (lu ou non )

    public Message() {
    }

    public Message(User expediteur, User destinataire, String content, boolean state) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.content = content;
        this.state = state;
    }

    public Message(User expediteur, String content, boolean state) {
        this.expediteur = expediteur;
        this.content = content;
        this.state = state;
    }

    public Message(String content, boolean state) {
        this.content = content;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    @JsonIgnore
    public User getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(User expediteur) {
        this.expediteur = expediteur;
    }
    @Transient
    @JsonIgnore
    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
