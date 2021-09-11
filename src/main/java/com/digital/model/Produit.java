package com.digital.model;

import com.digital.model.common.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Produit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CsvBindByName(column = "nom_produit")
    private String nomProduit;

    @CsvBindByName(column = "code_produit")
    private Long code;

//    private int codeBancass;

    @ManyToOne
    @JoinColumn(name = "id_branche")
    @JsonIgnore
    private Branche branche;

    @ManyToOne
    @JoinColumn(name = "id_type_produit")
    @JsonIgnore
    private TypesProduit typesProduit;

    @JsonIgnore
    @OneToMany(mappedBy = "produit", targetEntity = Contrat.class)
    private Collection<Contrat> contrats;

    @JsonIgnore
    @CsvBindByName(column = "id_type_produit")
    @Transient
    private Long typeProduit;

    @JsonIgnore
    @CsvBindByName(column = "id_branche")
    @Transient
    private Long refbranche;

    public Long getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(Long typeProduit) {
        this.typeProduit = typeProduit;
    }

    public Long getRefbranche() {
        return refbranche;
    }

    public void setRefbranche(Long refbranche) {
        this.refbranche = refbranche;
    }

    public Produit() {
    }

    public Produit(String nomProduit, Branche branche) {
        this.nomProduit = nomProduit;
        this.branche = branche;
    }

    public Produit(Long code,String nomProduit,  Branche branche, TypesProduit typesProduit) {
        this.nomProduit = nomProduit;
        this.code = code;
        this.branche = branche;
        this.typesProduit = typesProduit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @Transient
    public Collection<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(Collection<Contrat> contrats) {
        this.contrats = contrats;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @JsonIgnore
    @Transient
    public TypesProduit getTypesProduit() {
        return typesProduit;
    }

    public void setTypesProduit(TypesProduit typesProduit) {
        this.typesProduit = typesProduit;
    }

    /* public int getCodeBancass() {
            return codeBancass;
        }

        public void setCodeBancass(int codeBancass) {
            this.codeBancass = codeBancass;
        }*/
    @JsonIgnore
    @Transient
    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

}
