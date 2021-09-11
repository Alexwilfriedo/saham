package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CompteResultat {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected Long id;

        private Long idContactant;
        private String contratGroupe;
        private int codeProduit;
        private String typeContrat;
        private String societe;
        private Date dateCompteResultat;
        private int anneeExercice;
        private double  prestationPayees;
        private double  fraisGestionSurCotisation;
        private double  fraisGestionSurEpargne;
        private double  soldeCrediteur;
        private double  totalDebit;
        private Date dateReportSolde;
        private double  reportDuSolde;
        private double  cotisationNetteTaxe;
        private double  interetGaranti;
        private double  participationOBenef;
        private double  totalCredit;


        public Long getId() {
                return id;
        }


        public void setId(Long id) {
                this.id = id;
        }

        public CompteResultat() {
        }

        public Long getIdContactant() {
                return idContactant;
        }

        public void setIdContactant(Long idContactant) {
                this.idContactant = idContactant;
        }

        public String getContratGroupe() {
                return contratGroupe;
        }

        public void setContratGroupe(String contratGroupe) {
                this.contratGroupe = contratGroupe;
        }

        public int getCodeProduit() {
                return codeProduit;
        }

        public void setCodeProduit(int codeProduit) {
                this.codeProduit = codeProduit;
        }

        public String getTypeContrat() {
                return typeContrat;
        }

        public void setTypeContrat(String typeContrat) {
                this.typeContrat = typeContrat;
        }

        public String getSociete() {
                return societe;
        }

        public void setSociete(String societe) {
                this.societe = societe;
        }

        public Date getDateCompteResultat() {
                return dateCompteResultat;
        }

        public void setDateCompteResultat(Date dateCompteResultat) {
                this.dateCompteResultat = dateCompteResultat;
        }

        public int getAnneeExercice() {
                return anneeExercice;
        }

        public void setAnneeExercice(int anneeExercice) {
                this.anneeExercice = anneeExercice;
        }

        public double getPrestationPayees() {
                return prestationPayees;
        }

        public void setPrestationPayees(double prestationPayees) {
                this.prestationPayees = prestationPayees;
        }

        public double getFraisGestionSurCotisation() {
                return fraisGestionSurCotisation;
        }

        public void setFraisGestionSurCotisation(double fraisGestionSurCotisation) {
                this.fraisGestionSurCotisation = fraisGestionSurCotisation;
        }

        public double getFraisGestionSurEpargne() {
                return fraisGestionSurEpargne;
        }

        public void setFraisGestionSurEpargne(double fraisGestionSurEpargne) {
                this.fraisGestionSurEpargne = fraisGestionSurEpargne;
        }

        public double getSoldeCrediteur() {
                return soldeCrediteur;
        }

        public void setSoldeCrediteur(double soldeCrediteur) {
                this.soldeCrediteur = soldeCrediteur;
        }

        public double getTotalDebit() {
                return totalDebit;
        }

        public void setTotalDebit(double totalDebit) {
                this.totalDebit = totalDebit;
        }

        public Date getDateReportSolde() {
                return dateReportSolde;
        }

        public void setDateReportSolde(Date dateReportSolde) {
                this.dateReportSolde = dateReportSolde;
        }

        public double getReportDuSolde() {
                return reportDuSolde;
        }

        public void setReportDuSolde(double reportDuSolde) {
                this.reportDuSolde = reportDuSolde;
        }

        public double getCotisationNetteTaxe() {
                return cotisationNetteTaxe;
        }

        public void setCotisationNetteTaxe(double cotisationNetteTaxe) {
                this.cotisationNetteTaxe = cotisationNetteTaxe;
        }

        public double getInteretGaranti() {
                return interetGaranti;
        }

        public void setInteretGaranti(double interetGaranti) {
                this.interetGaranti = interetGaranti;
        }

        public double getParticipationOBenef() {
                return participationOBenef;
        }

        public void setParticipationOBenef(double participationOBenef) {
                this.participationOBenef = participationOBenef;
        }

        public double getTotalCredit() {
                return totalCredit;
        }

        public void setTotalCredit(double totalCredit) {
                this.totalCredit = totalCredit;
        }

        @Override
        public String toString() {
                return "CompteResultat{" +
                        "id=" + id +
                        ", idContactant=" + idContactant +
                        ", contratGroupe='" + contratGroupe + '\'' +
                        ", codeProduit=" + codeProduit +
                        ", typeContrat='" + typeContrat + '\'' +
                        ", societe='" + societe + '\'' +
                        ", dateCompteResultat=" + dateCompteResultat +
                        ", anneeExercice=" + anneeExercice +
                        ", prestationPayees=" + prestationPayees +
                        ", fraisGestionSurCotisation=" + fraisGestionSurCotisation +
                        ", fraisGestionSurEpargne=" + fraisGestionSurEpargne +
                        ", soldeCrediteur=" + soldeCrediteur +
                        ", totalDebit=" + totalDebit +
                        ", dateReportSolde=" + dateReportSolde +
                        ", reportDuSolde=" + reportDuSolde +
                        ", cotisationNetteTaxe=" + cotisationNetteTaxe +
                        ", interetGaranti=" + interetGaranti +
                        ", participationOBenef=" + participationOBenef +
                        ", totalCredit=" + totalCredit +
                        '}';
        }
}
