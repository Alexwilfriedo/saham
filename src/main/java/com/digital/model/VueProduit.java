package com.digital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VueProduit {
    @Id
    @Column(name = "id")
    private Long id;

    private Long code;

    @Column(name = "nom_produit")
    private String nomProduit;

    @Column(name = "ide_branche")
    private Long ideBranche;

    @Column(name = "nom_branche")
    private String nomBranche;

    @Column(name = "ide_type")
    private Long ideType;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Long getIdeBranche() {
        return ideBranche;
    }

    public void setIdeBranche(Long ideBranche) {
        this.ideBranche = ideBranche;
    }

    public String getNomBranche() {
        return nomBranche;
    }

    public void setNomBranche(String nomBranche) {
        this.nomBranche = nomBranche;
    }

    public Long getIdeType() {
        return ideType;
    }

    public void setIdeType(Long ideType) {
        this.ideType = ideType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
