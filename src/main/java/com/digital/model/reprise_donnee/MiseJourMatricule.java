package com.digital.model.reprise_donnee;


import javax.persistence.*;

@Entity
public class MiseJourMatricule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private Long reference;

    private String matricule;

    //@Size(min = Constant.JPA_CONSTRAINTS_SHORTEST, max = Constant.JPA_CONSTRAINTS_MEDIUM)
    @Column(name = "prenoms")
    protected String firstname;

    //@Size(min = Constant.JPA_CONSTRAINTS_SHORTEST, max = Constant.JPA_CONSTRAINTS_SHORT)
    //@NotNull
    @Column(name = "nom")
    protected String lastname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
