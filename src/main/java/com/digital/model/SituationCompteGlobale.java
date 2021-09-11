package com.digital.model;

import com.digital.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class SituationCompteGlobale{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected Long id;

        private Long idContactant;
        private String typeContrat;
        private String contractant;
        private Date dateSituation;
        private double cotisationAnnuelle;
        private String codeContrat;
        private String periodicite;
        private String assure;
        private double capitalAcquisJanv;
        private double cumulPrimeAu31122016;
        private double primeNette;
        private double interet;
        private double prestation;
        private double capitalAu;
        private String tauxRevaloEpargne;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getCodeContrat() {
                return codeContrat;
        }

        public void setCodeContrat(String codeContrat) {
                this.codeContrat = codeContrat;
        }

        public String getTypeContrat() {
                return typeContrat;
        }

        public void setTypeContrat(String typeContrat) {
                this.typeContrat = typeContrat;
        }

        public String getPeriodicite() {
                return periodicite;
        }

        public void setPeriodicite(String periodicite) {
                this.periodicite = periodicite;
        }

        public String getContractant() {
                return contractant;
        }

        public void setContractant(String contractant) {
                this.contractant = contractant;
        }

        public String getAssure() {
                return assure;
        }

        public void setAssure(String assure) {
                this.assure = assure;
        }

        public Date getDateSituation() {
                return dateSituation;
        }

        public void setDateSituation(Date dateSituation) {
                this.dateSituation = dateSituation;
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

        public void setCapitalAu(double capitalAu) {
                this.capitalAu = capitalAu;
        }


        public double getCumulPrimeAu31122016() {
                return cumulPrimeAu31122016;
        }

        public void setCumulPrimeAu31122016(double cumulPrimeAu31122016) {
                this.cumulPrimeAu31122016 = cumulPrimeAu31122016;
        }

        public Long getIdContactant() {
                return idContactant;
        }

        public void setIdContactant(Long idContactant) {
                this.idContactant = idContactant;
        }

        public String getTauxRevaloEpargne() {
                return tauxRevaloEpargne;
        }

        public void setTauxRevaloEpargne(String tauxRevaloEpargne) {
                this.tauxRevaloEpargne = tauxRevaloEpargne;
        }

        @Override
        public String toString() {
                return "SituationCompteGlobale{" +
                        "id=" + id +
                        ", idContactant=" + idContactant +
                        ", typeContrat='" + typeContrat + '\'' +
                        ", contractant='" + contractant + '\'' +
                        ", dateSituation=" + dateSituation +
                        ", cotisationAnnuelle=" + cotisationAnnuelle +
                        ", codeContrat='" + codeContrat + '\'' +
                        ", periodicite='" + periodicite + '\'' +
                        ", assure='" + assure + '\'' +
                        ", capitalAcquisJanv=" + capitalAcquisJanv +
                        ", cumulPrimeAu31122016=" + cumulPrimeAu31122016 +
                        ", primeNette=" + primeNette +
                        ", interet=" + interet +
                        ", prestation=" + prestation +
                        ", capitalAu=" + capitalAu +
                        ", tauxRevaloEpargne='" + tauxRevaloEpargne + '\'' +
                        '}';
        }
}
