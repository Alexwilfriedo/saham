package com.digital.model.api;

import com.digital.model.Branche;
import com.digital.model.Contrat;
import com.digital.model.TypesProduit;
import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;
import org.jsoup.nodes.Document;

import javax.persistence.*;
import java.util.Collection;


public class ProduitApi extends BaseEntity {

    private Long id;
    private String nomProduit;
    private Long code;
    private String html;
    @URL
    private String imgUrl; //http://sahamassurancevie.ci/img/produits/cari.jpg

    @JsonIgnore
    @Transient
    private Document document;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    @Transient
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
        this.html =document.outerHtml();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProduitApi() {
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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


    /* public int getCodeBancass() {
            return codeBancass;
        }

        public void setCodeBancass(int codeBancass) {
            this.codeBancass = codeBancass;
        }*/

}
