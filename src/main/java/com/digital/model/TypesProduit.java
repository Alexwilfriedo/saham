package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TypesProduit{
    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "type")
    private String type;

    @OneToMany(mappedBy = "typesProduit",fetch = FetchType.LAZY)
    private Collection<Produit> produits;

    public TypesProduit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypesProduit(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
