package com.digital.model;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;
@Entity
public class TypeProfils{

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "typeProfils",fetch = FetchType.LAZY)
    private Collection<Profils> profilsCollection;

    @CsvBindByName(column = "typeprofile")
    private String typeProfile;

    public TypeProfils() {
    }

    public TypeProfils(String typeProfile) {
        this.typeProfile = typeProfile;
    }

    public Collection<Profils> getProfilsCollection() {
        return profilsCollection;
    }

    public void setProfilsCollection(Collection<Profils> profilsCollection) {
        this.profilsCollection = profilsCollection;
    }

    public String getTypeProfile() {
        return typeProfile;
    }

    public void setTypeProfile(String typeProfile) {
        this.typeProfile = typeProfile;
    }
}
