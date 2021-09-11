package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.digital.model.common.BaseEntity;

import javax.persistence.*;
import java.util.Collection;


public class Ordre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String libelle;
/*
    @OneToMany(mappedBy = "ordre",fetch = FetchType.LAZY)
    private Collection<Quitance> quitances;*/
}
