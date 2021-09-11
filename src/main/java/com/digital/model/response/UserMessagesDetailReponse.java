package com.digital.model.response;

import com.digital.model.Contrat;
import com.digital.model.User;
import com.digital.model.tchat.Greeting;
import com.digital.model.tchat.Message;

import java.util.Date;
import java.util.List;

public class UserMessagesDetailReponse {

    private String exp_username;
    private String exp_lastName;
    private String exp_firstName;

    private String dest_lastName;
    private String dest_firstName;
    private String dest_username;

    private List<Contrat> expediteurContratList;
    private List<Greeting> messages;

    //information user
    private String username;
    private Date birthdate;
    private String phone;
    private String matricule;
    private String address;
    private String email;

    //SITUATION DE COMPTE
    private String code_contrat;
    private double cotisationAnnuelle;
    private double capitalAcquisJanv; //Provision Mathematique
    private double cumulPrimeAu31122016;
    private double primeNette;
    private double interet;
    private double prestation;
    private double capitalAu;

    public UserMessagesDetailReponse() {
    }

    public String getExp_username() {
        return exp_username;
    }

    public void setExp_username(String exp_username) {
        this.exp_username = exp_username;
    }

    public String getExp_lastName() {
        return exp_lastName;
    }

    public void setExp_lastName(String exp_lastName) {
        this.exp_lastName = exp_lastName;
    }

    public String getDest_lastName() {
        return dest_lastName;
    }

    public void setDest_lastName(String dest_lastName) {
        this.dest_lastName = dest_lastName;
    }

    public String getExp_firstName() {
        return exp_firstName;
    }

    public void setExp_firstName(String exp_firstName) {
        this.exp_firstName = exp_firstName;
    }

    public String getDest_firstName() {
        return dest_firstName;
    }

    public void setDest_firstName(String dest_firstName) {
        this.dest_firstName = dest_firstName;
    }

    public String getDest_username() {
        return dest_username;
    }

    public void setDest_username(String dest_username) {
        this.dest_username = dest_username;
    }

    public List<Contrat> getExpediteurContratList() {
        return expediteurContratList;
    }

    public void setExpediteurContratList(List<Contrat> expediteurContratList) {
        this.expediteurContratList = expediteurContratList;
    }

    public List<Greeting> getMessages() {
        return messages;
    }

    public void setMessages(List<Greeting> messages)
    {
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCotisationAnnuelle() {
        return cotisationAnnuelle;
    }

    public void setCotisationAnnuelle(double cotisationAnnuelle) {
        this.cotisationAnnuelle = cotisationAnnuelle;
    }

    public double getCapitalAcquisJanv() {
        return capitalAcquisJanv;
    }

    public void setCapitalAcquisJanv(double capitalAcquisJanv) {
        this.capitalAcquisJanv = capitalAcquisJanv;
    }

    public double getCumulPrimeAu31122016() {
        return cumulPrimeAu31122016;
    }

    public void setCumulPrimeAu31122016(double cumulPrimeAu31122016) {
        this.cumulPrimeAu31122016 = cumulPrimeAu31122016;
    }

    public double getPrimeNette() {
        return primeNette;
    }

    public void setPrimeNette(double primeNette) {
        this.primeNette = primeNette;
    }

    public double getInteret() {
        return interet;
    }

    public void setInteret(double interet) {
        this.interet = interet;
    }

    public double getPrestation() {
        return prestation;
    }

    public void setPrestation(double prestation) {
        this.prestation = prestation;
    }

    public double getCapitalAu() {
        return capitalAu;
    }

    public void setCapitalAu(double capitalAu)
    {
        this.capitalAu = capitalAu;
    }

    public String getCode_contrat() {
        return code_contrat;
    }

    public void setCode_contrat(String code_contrat) {
        this.code_contrat = code_contrat;
    }
}
