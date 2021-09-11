package com.digital.model;

import com.digital.model.common.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Personne extends Person {

    @ManyToOne
    @JoinColumn(name = "id_profil")
    private Profils profile;

    @JsonIgnore
    @OneToMany(mappedBy = "souscripteur",fetch = FetchType.LAZY)
    private Collection<Contrat> contrats;

    public Personne() {
    }


     @JsonIgnore
    public Collection<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(Collection<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Personne(String firstname, String lastname) {
        super(firstname, lastname);
    }

    public Profils getProfile() {
        return profile;
    }

    public void setProfile(Profils profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate=" + birthdate +
                ", displayName='" + displayName + '\'' +
                ", civilite='" + civilite + '\'' +
                ", fonction='" + fonction + '\'' +
                ", matricule='" + matricule + '\'' +
                ", sexe='" + sexe + '\'' +
                ", boitePostale='" + boitePostale + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", address='" + address + '\'' +
                ", secondaryAddress='" + secondaryAddress + '\'' +
                ", ville='" + ville + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
