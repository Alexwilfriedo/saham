package com.digital.model;

import com.digital.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class StatutContrat{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @CsvBindByName(column = "id")
    protected Long id;

    @CsvBindByName(column = "statutcontrat")
    private String statutContrat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "statutContrat",fetch = FetchType.LAZY)
    private Collection<Contrat> contrats;

    public StatutContrat() {
    }

    public StatutContrat(String statutContrat) {
        this.statutContrat = statutContrat;
    }

    public String getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(String statutContrat) {
        this.statutContrat = statutContrat;
    }

    @JsonIgnore
    public Collection<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(Collection<Contrat> contrats) {
        this.contrats = contrats;
    }
}
