package com.digital.service.bootstrap;

import com.digital.model.Contrat;
import com.digital.model.SituationCompte;
import com.digital.repository.ContratRepository;
import com.digital.repository.SituationCompteRepository;
import com.digital.repository.StatutContratRepository;
import com.digital.util.LoadCsvFile;
import com.digital.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SituationCompteBootstrap  {
    public void seed(String filePath, SituationCompteRepository situationCompteRepository){
        if (situationCompteRepository.count() == 0) {
            List<String>  data = LoadCsvFile.getData(filePath);
            int i=0;
            //3280290
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                //situationCompte = new SituationCompte();
                try {
                    if (s.split(",").length>0){
                        SituationCompte situationCompte = new SituationCompte();

                        String assure               = s.split(",")[2];
                        String capitalAcquisJanv    = s.split(",")[3];
                        String capitalAu            = s.split(",")[4];
                        String codeContrat          = s.split(",")[5];
                        String contractant          = s.split(",")[6];
                        String cotisationAnnuelle   = s.split(",")[7];
                        String dateSituation        = s.split(",")[8];
                        String interet              = s.split(",")[9];
                        String periodicite          = s.split(",")[10];
                        String prestation           = s.split(",")[11];
                        String primeNette           = s.split(",")[12];
                        String taux                 = s.split(",")[13];
                        String typeContrat          = s.split(",")[14];

                        if (codeContrat !=null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("null") && !codeContrat.equalsIgnoreCase("NULL")){

                            if (assure != null && !assure.isEmpty() && !assure.equalsIgnoreCase("null"))
                                situationCompte.setAssure(assure.trim());

                            if (capitalAcquisJanv !=null && !capitalAcquisJanv.isEmpty() && !capitalAcquisJanv.equalsIgnoreCase("null"))
                                situationCompte.setCapitalAcquisJanv(Double.parseDouble(capitalAcquisJanv.trim()));

                            if (capitalAu !=null && !capitalAu.isEmpty() && !capitalAu.equalsIgnoreCase("null"))
                                situationCompte.setCapitalAu(Double.parseDouble(capitalAu.trim()));

                            situationCompte.setCodeContrat(codeContrat.trim());

                            if ( contractant != null && !contractant.isEmpty()&& !contractant.equalsIgnoreCase("null"))
                                situationCompte.setContractant(contractant.trim());

                            if (cotisationAnnuelle !=null && !cotisationAnnuelle.isEmpty() && !cotisationAnnuelle.equalsIgnoreCase("null"))
                                situationCompte.setCotisationAnnuelle(Double.parseDouble(cotisationAnnuelle.trim()));

                            if (dateSituation !=null && !dateSituation.isEmpty()&& !dateSituation.equalsIgnoreCase("null"))
                                situationCompte.setDateSituation(new SimpleDateFormat("dd/MM/yyyy").parse(dateSituation.trim()));

                            if (interet !=null && !interet.isEmpty() && !interet.equalsIgnoreCase("null"))
                                situationCompte.setInteret(Double.parseDouble(interet.trim()));

                            if (periodicite !=null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null"))
                                situationCompte.setPeriodicite(periodicite.trim());

                            if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null"))
                                situationCompte.setPrestation(Double.parseDouble(prestation.trim()));

                            if ( primeNette !=null && !primeNette.isEmpty()&& !primeNette.equalsIgnoreCase("null"))
                                situationCompte.setPrimeNette(Double.parseDouble(primeNette.trim()));

                            if (taux !=null && !taux.isEmpty()&& !taux.equalsIgnoreCase("null"))
                                situationCompte.setTauxRevaloEpargne(taux.trim());

                            if (typeContrat != null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null"))
                                situationCompte.setTypeContrat(typeContrat.trim());

                            //if(situationCompteRepository.findSituationCompteBeforeProcessFile(situationCompte.getAssure(), situationCompte.getCapitalAcquisJanv(), situationCompte.getCapitalAu(),situationCompte.getCodeContrat(),situationCompte.getContractant(), situationCompte.getCotisationAnnuelle(),situationCompte.getDateSituation(),situationCompte.getInteret(), situationCompte.getPeriodicite(),situationCompte.getPrestation(),situationCompte.getPrimeNette(), situationCompte.getTauxRevaloEpargne(),situationCompte.getTypeContrat()) == null){
                                System.out.println(j+" situation Compte en cours de traitement ...");

                                situationCompteRepository.save(situationCompte);
                                System.out.println(j+" situation Compte traitée");
                            //}

                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }


        }
    }

    public void load(String filePath, SituationCompteRepository situationCompteRepository, ContratRepository contratRepository, StatutContratRepository statutContratRepository){
        List<String>  data = LoadCsvFile.getData(filePath);
        int i=0;
        //3280290
        for(int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            i++;
            try {
                if (s.split(",").length>0){
                    SituationCompte situationCompte; // = new SituationCompte();

                    /**
                    String codeContrat          = s.split(",")[0];
                    String typeContrat          = s.split(",")[1];
                    String periodicite          = s.split(",")[2];
                    String contractant          = s.split(",")[3];
                    String assure               = s.split(",")[4];
                    String dateSituation        = s.split(",")[5];
                    String cotisationAnnuelle   = s.split(",")[6];
                    String capitalAcquisJanv    = s.split(",")[7];
                    String cumulPrime           = s.split(",")[8];
                    String primeNette           = s.split(",")[9];
                    String interet              = s.split(",")[10];
                    String prestation           = s.split(",")[11];
                    String capitalAu            = s.split(",")[12];
                    String taux                 = s.split(",")[13];
                    String statut               = s.split(",")[18];
                    **/



                    String assure             = s.split(",")[0];
                    String capitalAcquisJanv  = s.split(",")[1];
                    String capitalAu          = s.split(",")[2];
                    String codeContrat        = s.split(",")[3];
                    String contractant        = s.split(",")[4];
                    String cotisationAnnuelle = s.split(",")[5];
                    String dateSituation      = s.split(",")[6];
                    String interet            = s.split(",")[7];
                    String periodicite        = s.split(",")[8];
                    String prestation         = s.split(",")[9];
                    String primeNette         = s.split(",")[10];
                    String taux               = s.split(",")[11];
                    String typeContrat        = s.split(",")[12];

                    if (codeContrat !=null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("null") && !codeContrat.equalsIgnoreCase("NULL")){
                        situationCompte = situationCompteRepository.findByCodeContrat(codeContrat.trim());
                        Contrat contrat = contratRepository.findByPolice(codeContrat.trim());

                        if (situationCompte == null) situationCompte = new SituationCompte();

                        if (contrat != null) {
                            //contrat.setId_statut(statutContratRepository.findByStatutContrat(statut.trim()).getId());
                            contratRepository.save(contrat);
                            //System.out.println(contrat);
                        }

                        if (typeContrat != null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null"))
                            situationCompte.setTypeContrat(typeContrat.trim());

                        if (periodicite !=null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null"))
                            situationCompte.setPeriodicite(periodicite.trim());

                        if ( contractant != null && !contractant.isEmpty()&& !contractant.equalsIgnoreCase("null"))
                            situationCompte.setContractant(contractant.trim());

                        if (assure != null && !assure.isEmpty() && !assure.equalsIgnoreCase("null"))
                            situationCompte.setAssure(assure.trim());

                        if (dateSituation !=null && !dateSituation.isEmpty()&& !dateSituation.equalsIgnoreCase("null"))
                            situationCompte.setDateSituation(new SimpleDateFormat("dd/MM/yyyy").parse(dateSituation.trim()));

                        if (cotisationAnnuelle !=null && !cotisationAnnuelle.isEmpty() && !cotisationAnnuelle.equalsIgnoreCase("null"))
                            situationCompte.setCotisationAnnuelle(Double.parseDouble(cotisationAnnuelle.trim()));

                        if (capitalAcquisJanv !=null && !capitalAcquisJanv.isEmpty() && !capitalAcquisJanv.equalsIgnoreCase("null"))
                            situationCompte.setCapitalAcquisJanv(Double.parseDouble(capitalAcquisJanv.trim()));

                        //if (cumulPrime !=null && !cumulPrime.isEmpty() && !cumulPrime.equalsIgnoreCase("null")) situationCompte.setCumulPrimeAu31122016(Double.parseDouble(cumulPrime.trim()));

                        if ( primeNette !=null && !primeNette.isEmpty()&& !primeNette.equalsIgnoreCase("null"))
                            situationCompte.setPrimeNette(Double.parseDouble(primeNette.trim()));

                        if (interet !=null && !interet.isEmpty() && !interet.equalsIgnoreCase("null"))
                            situationCompte.setInteret(Double.parseDouble(interet.trim()));

                        if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null"))
                            situationCompte.setPrestation(Double.parseDouble(prestation.trim()));

                        if (capitalAu !=null && !capitalAu.isEmpty() && !capitalAu.equalsIgnoreCase("null"))
                            situationCompte.setCapitalAu(Double.parseDouble(capitalAu.trim()));

                        if (taux !=null && !taux.isEmpty()&& !taux.equalsIgnoreCase("null"))
                            situationCompte.setTauxRevaloEpargne(taux.trim());

                        situationCompte.setCodeContrat(codeContrat.trim());


                        System.out.println(j+" situation Compte en cours de traitement ...");

                        System.out.println(situationCompte);
                        situationCompteRepository.save(situationCompte);
                        System.out.println(j+" situation Compte traitée");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
    }

    public void local(String filePath, SituationCompteRepository situationCompteRepository){
        if (situationCompteRepository.count() == 0) {
            List<String>  data = LoadCsvFile.getData(filePath);
            int i=0;
            //3280290
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                //situationCompte = new SituationCompte();
                try {
                    if (s.split(",").length>0){
                        SituationCompte situationCompte = new SituationCompte();

                        String id                     = s.split(",")[0];
                        String assure                 = s.split(",")[1];
                        String capital_acquis_janv    = s.split(",")[2];
                        String capital_au             = s.split(",")[3];
                        String code_contrat           = s.split(",")[4];
                        String contractant            = s.split(",")[5];
                        String cotisation_annuelle    = s.split(",")[6];
                        String cumul_prime_au31122016 = s.split(",")[7];
                        String date_situation         = s.split(",")[8];
                        String interet                = s.split(",")[9];
                        String periodicite            = s.split(",")[10];
                        String prestation             = s.split(",")[11];
                        String prime_nette            = s.split(",")[12];
                        String taux_revalo            = s.split(",")[13];
                        String type_contrat           = s.split(",")[14];

                        if (code_contrat !=null && !code_contrat.isEmpty() && !code_contrat.equalsIgnoreCase("null") && !code_contrat.equalsIgnoreCase("NULL")){

                            if (assure != null && !assure.isEmpty() && !assure.equalsIgnoreCase("null"))
                                situationCompte.setAssure(assure.trim());

                            if (capital_acquis_janv !=null && !capital_acquis_janv.isEmpty() && !capital_acquis_janv.equalsIgnoreCase("null"))
                                situationCompte.setCapitalAcquisJanv(Double.parseDouble(capital_acquis_janv.trim()));

                            if (capital_au !=null && !capital_au.isEmpty() && !capital_au.equalsIgnoreCase("null"))
                                situationCompte.setCapitalAu(Double.parseDouble(capital_au.trim()));

                            situationCompte.setCodeContrat(code_contrat.trim());

                            if ( contractant != null && !contractant.isEmpty()&& !contractant.equalsIgnoreCase("null"))
                                situationCompte.setContractant(contractant.trim());

                            if (cotisation_annuelle !=null && !cotisation_annuelle.isEmpty() && !cotisation_annuelle.equalsIgnoreCase("null"))
                                situationCompte.setCotisationAnnuelle(Double.parseDouble(cotisation_annuelle.trim()));

                            if (date_situation !=null && !date_situation.isEmpty()&& !date_situation.equalsIgnoreCase("null"))
                                situationCompte.setDateSituation(new SimpleDateFormat("yyyy-MM-dd").parse(date_situation.trim()));

                            if (interet !=null && !interet.isEmpty() && !interet.equalsIgnoreCase("null"))
                                situationCompte.setInteret(Double.parseDouble(interet.trim()));

                            if (periodicite !=null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null"))
                                situationCompte.setPeriodicite(periodicite.trim());

                            if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null"))
                                situationCompte.setPrestation(Double.parseDouble(prestation.trim()));

                            if ( prime_nette !=null && !prime_nette.isEmpty()&& !prime_nette.equalsIgnoreCase("null"))
                                situationCompte.setPrimeNette(Double.parseDouble(prime_nette.trim()));

                            if (taux_revalo !=null && !taux_revalo.isEmpty()&& !taux_revalo.equalsIgnoreCase("null"))
                                situationCompte.setTauxRevaloEpargne(taux_revalo.trim());

                            if (type_contrat != null && !type_contrat.isEmpty() && !type_contrat.equalsIgnoreCase("null"))
                                situationCompte.setTypeContrat(type_contrat.trim());

                            System.out.println(j+" situation Compte en cours de traitement ...");

                            situationCompteRepository.save(situationCompte);
                            System.out.println(j+" situation Compte traitée");
                            //}

                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }


        }
    }
}
