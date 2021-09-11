package com.digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Profils{

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "profil")
    private String profil;

    @CsvBindByName(column = "id_type_profil")
    @Transient
    @JsonIgnore
    private Long profilTypeId;

    @JsonIgnore
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY)
    private Collection<Personne> personnes;

    @ManyToOne
    @JoinColumn(name = "id_type_profils")
    @JsonIgnore
    private TypeProfils typeProfils;

    public Profils() {
    }

    public Profils(String profil, TypeProfils typeProfils) {
        this.profil = profil;
        this.typeProfils = typeProfils;
    }

    public Long getProfilTypeId() {
        return profilTypeId;
    }

    public void setProfilTypeId(Long profilTypeId) {
        this.profilTypeId = profilTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public Collection<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Collection<Personne> personnes) {
        this.personnes = personnes;
    }

    public TypeProfils getTypeProfils() {
        return typeProfils;
    }

    public void setTypeProfils(TypeProfils typeProfils) {
        this.typeProfils = typeProfils;
    }
}
