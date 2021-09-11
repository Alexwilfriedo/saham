package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class StatutQuitance{

    @Id
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "statutquitt")
    private String statutQuit;

    @OneToMany(mappedBy = "statutQuitance",fetch = FetchType.LAZY)
    private Collection<Quitance> quitances;

    public StatutQuitance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatutQuitance(String statutQuit) {
        this.statutQuit = statutQuit;
    }

    public String getStatutQuit() {
        return statutQuit;
    }

    public void setStatutQuit(String statutQuit) {
        this.statutQuit = statutQuit;
    }
    @JsonIgnore
    public Collection<Quitance> getQuitances() {
        return quitances;
    }

    public void setQuitances(Collection<Quitance> quitances) {
        this.quitances = quitances;
    }
}
