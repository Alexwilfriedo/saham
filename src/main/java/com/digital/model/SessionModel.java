package com.digital.model;

import com.digital.model.response.AskedRachatTotalCorporate;
import com.digital.model.response.Darshboard;
import com.digital.model.response.UploadFileResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionModel {

    private boolean isConnected = false;
    private boolean isIndivOfCorporate = false;
    private boolean indivOfOrange = false;
    private Personne personne;
    private User manager;
    private int isOrNot = 0;
    private  Long id;
    private String fullName;
    private Long identifiant;
    private String username;
    private List<AskedRachatTotalCorporate> askeds = new ArrayList<>();
    private List<AskedRachatTotalCorporate> termesContrat = new ArrayList<>();

    private List<Contrat> contrats = new ArrayList<>();
    private List<Quitance> quitanceList = new ArrayList<>();
    private List<Darshboard> darshboardsContrat = new ArrayList<>();
    private Map<String, Produit> produitMap = new HashMap<>();
    private List<UploadFileResponse> uploadFileResponse = new ArrayList<>();
    private int cumul_impaye ;
    private int cumul_paye;
    private int statut_impaye = 0;
    private int statut_paye = 0;
    private int statut_instance = 0;
    private int total_contrat = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.identifiant = Long.parseLong(username)-10000000;
        this.username = username;
    }

    public List<UploadFileResponse> getUploadFileResponse() {
        return uploadFileResponse;
    }

    public void setUploadFileResponse(List<UploadFileResponse> uploadFileResponse) {
        this.uploadFileResponse = uploadFileResponse;
    }

    public List<AskedRachatTotalCorporate> getAskeds() {
        return askeds;
    }

    public void setAskeds(List<AskedRachatTotalCorporate> askeds) {
        this.askeds = askeds;
    }

    public List<AskedRachatTotalCorporate> getTermesContrat() {
        return termesContrat;
    }

    public void setTermesContrat(List<AskedRachatTotalCorporate> termesContrat) {
        this.termesContrat = termesContrat;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public List<Quitance> getQuitanceList() {
        return quitanceList;
    }

    public void setQuitanceList(List<Quitance> quitanceList) {
        this.quitanceList = quitanceList;
    }

    public List<Darshboard> getDarshboardsContrat() {
        return darshboardsContrat;
    }

    public void setDarshboardsContrat(List<Darshboard> darshboardsContrat) {
        this.darshboardsContrat = darshboardsContrat;
    }

    public int getCumul_impaye() {
        return cumul_impaye;
    }

    public void setCumul_impaye(int cumul_impaye) {
        this.cumul_impaye = cumul_impaye;
    }

    public int getCumul_paye() {
        return cumul_paye;
    }

    public void setCumul_paye(int cumul_paye) {
        this.cumul_paye = cumul_paye;
    }

    public int getStatut_impaye() {
        return statut_impaye;
    }

    public void setStatut_impaye(int statut_impaye) {
        this.statut_impaye = statut_impaye;
    }

    public int getStatut_paye() {
        return statut_paye;
    }

    public void setStatut_paye(int statut_paye) {
        this.statut_paye = statut_paye;
    }

    public int getStatut_instance() {
        return statut_instance;
    }

    public void setStatut_instance(int statut_instance) {
        this.statut_instance = statut_instance;
    }

    public Map<String, Produit> getProduitMap() {
        return produitMap;
    }

    public void setProduitMap(Map<String, Produit> produitMap) {
        this.produitMap = produitMap;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public int getTotal_contrat() {
        return total_contrat;
    }

    public void setTotal_contrat(int total_contrat) {
        this.total_contrat = total_contrat;
    }

    public boolean isIndivOfCorporate() {
        return isIndivOfCorporate;
    }

    public void setIndivOfCorporate(boolean indivOfCorporate) {
        this.isOrNot =1;
        isIndivOfCorporate = indivOfCorporate;
    }

    public boolean isIndivOfOrange() {
        return indivOfOrange;
    }

    public void setIndivOfOrange(boolean indivOfOrange) {
        this.isOrNot =1;
        this.indivOfOrange = indivOfOrange;
    }

    public int getIsOrNot() {
        return isOrNot;
    }

    public void setIsOrNot(int isOrNot) {
        this.isOrNot = isOrNot;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
