package com.digital.service.bootstrap;

import com.digital.model.SituationCompteGlobale;
import com.digital.repository.SituationCompteGlobaleRepository;
import com.digital.util.LoadCsvFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SituationCompteGlobaleBootstrap {
    public void seed(String pathname,SituationCompteGlobaleRepository situationCompteGlobaleRepository){
        if (situationCompteGlobaleRepository.count() ==0){
            List<String>  data = LoadCsvFile.getData(pathname);
            SituationCompteGlobale situationCompteGlobale;
            //0-_id,// 1-_rev,// 2-CapitalAU,// 3-CapitalAcquisJanv,// 4-Contractant,// 5-CotisationAnnuelle,// 6-DateSituation,// 7-Idcontractant,// 8-Interet,// 9-Prestation,// 10-PrimeNette,// 11-tauxRevaloEpargne,// 12-typeContrat
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                situationCompteGlobale = new SituationCompteGlobale();
                try {

                    if (s.split(",").length>0){
                        String capitalAcquisJanv    = s.split(",")[2];
                        String capitalAu            = s.split(",")[3];
                        String contractant          = s.split(",")[4];
                        String cotisationAnnuelle   = s.split(",")[5];
                        String cumulPrimeAu31122016 = s.split(",")[6];
                        String dateSituation        = s.split(",")[7];
                        String id                   = s.split(",")[8];
                        String interet              = s.split(",")[9];
                        String periodicite          = s.split(",")[10];
                        String prestation           = s.split(",")[11];
                        String primeNette           = s.split(",")[12];
                        String taux                 = s.split(",")[13];
                        String typeContrat          = s.split(",")[14];

                        if (id != null && !id.isEmpty() && !id.equalsIgnoreCase("-") && !id.equalsIgnoreCase("null") && !id.equalsIgnoreCase("NULL")){
                            if ( capitalAcquisJanv != null && !capitalAcquisJanv.isEmpty() && !capitalAcquisJanv.equalsIgnoreCase("null") && !capitalAcquisJanv.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCapitalAcquisJanv(Double.parseDouble(capitalAcquisJanv.trim()));

                            if ( capitalAu != null && !capitalAu.isEmpty() && !capitalAu.equalsIgnoreCase("null") && !capitalAu.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCapitalAu(Double.parseDouble(capitalAu.trim()));

                            if ( contractant != null && !contractant.isEmpty() && !contractant.equalsIgnoreCase("null") && !contractant.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setContractant(contractant.trim());

                            if ( cotisationAnnuelle != null && !cotisationAnnuelle.isEmpty() && !cotisationAnnuelle.equalsIgnoreCase("null") && !cotisationAnnuelle.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCotisationAnnuelle(Double.parseDouble(cotisationAnnuelle.trim()));

                            if ( cumulPrimeAu31122016 != null && !cumulPrimeAu31122016.isEmpty() && !cumulPrimeAu31122016.equalsIgnoreCase("null") && !cumulPrimeAu31122016.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCumulPrimeAu31122016(Double.parseDouble(cumulPrimeAu31122016.trim()));

                            if (dateSituation != null && !dateSituation.isEmpty() && !dateSituation.equalsIgnoreCase("null") && !dateSituation.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setDateSituation(new SimpleDateFormat("dd/MM/yyyy").parse(dateSituation));

                            situationCompteGlobale.setIdContactant(Long.parseLong(id.trim()));

                            if (interet != null && !interet.isEmpty() && !interet.equalsIgnoreCase("null") && !interet.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setInteret(Double.parseDouble(interet.trim()));

                            if (periodicite != null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null") && !periodicite.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPeriodicite(periodicite.trim());

                            if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null") && !prestation.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPrestation(Double.parseDouble(prestation.trim()));

                            if (primeNette !=null && !primeNette.isEmpty() && !primeNette.equalsIgnoreCase("null") && !primeNette.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPrimeNette(Double.parseDouble(primeNette.trim()));

                            if (taux !=null && !taux.isEmpty() && !taux.equalsIgnoreCase("null") && !taux.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setTauxRevaloEpargne(taux.trim());

                            if (typeContrat !=null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null") && !typeContrat.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setTypeContrat(typeContrat.trim());

                            situationCompteGlobaleRepository.save(situationCompteGlobale);
                            System.out.println(j+" situation Compte GLOBALE traitée");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public void load(String pathname,SituationCompteGlobaleRepository situationCompteGlobaleRepository){
        List<String>  data = LoadCsvFile.getData(pathname);

        //0-_id,// 1-_rev,// 2-CapitalAU,// 3-CapitalAcquisJanv,// 4-Contractant,// 5-CotisationAnnuelle,// 6-DateSituation,// 7-Idcontractant,// 8-Interet,// 9-Prestation,// 10-PrimeNette,// 11-tauxRevaloEpargne,// 12-typeContrat
        int i=0;
        for(int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            i++;
            try {
                SituationCompteGlobale situationCompteGlobale;

                if (s.split(",").length>0){
                    /**
                    String id                   = s.split(",")[0];
                    String contractant          = s.split(",")[1];
                    String typeContrat          = s.split(",")[2];
                    String dateSituation        = s.split(",")[3];
                    String capitalAcquisJanv    = s.split(",")[4];
                    String cotisationAnnuelle   = s.split(",")[5];
                    String primeNette           = s.split(",")[6];
                    String interet              = s.split(",")[7];
                    String prestation           = s.split(",")[8];
                    String capitalAu            = s.split(",")[9];
                    String taux                 = s.split(",")[10];
                    **/

                    String capitalAcquisJanv  = s.split(",")[0];
                    String capitalAu          = s.split(",")[1];
                    String contractant        = s.split(",")[2];
                    String cotisationAnnuelle = s.split(",")[3];
                    String cumulPrime         = s.split(",")[4];
                    String dateSituation      = s.split(",")[5];
                    String id                 = s.split(",")[6];
                    String interet            = s.split(",")[7];
                    String periodicite        = s.split(",")[8];
                    String prestation         = s.split(",")[9];
                    String primeNette         = s.split(",")[10];
                    String taux               = s.split(",")[11];
                    String typeContrat        = s.split(",")[12];

                    if (id != null && !id.isEmpty() && !id.equalsIgnoreCase("-") && !id.equalsIgnoreCase("null") && !id.equalsIgnoreCase("NULL")){
                        situationCompteGlobale = situationCompteGlobaleRepository.findByIdContactant(Long.parseLong(id.trim()));

                        if (situationCompteGlobale == null) situationCompteGlobale = new SituationCompteGlobale();

                        if ( contractant != null && !contractant.isEmpty() && !contractant.equalsIgnoreCase("null") && !contractant.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setContractant(contractant.trim());

                        if (typeContrat !=null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null") && !typeContrat.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setTypeContrat(typeContrat.trim());

                        if (dateSituation != null && !dateSituation.isEmpty() && !dateSituation.equalsIgnoreCase("null") && !dateSituation.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setDateSituation(new SimpleDateFormat("dd/MM/yyyy").parse(dateSituation));

                        if ( capitalAcquisJanv != null && !capitalAcquisJanv.isEmpty() && !capitalAcquisJanv.equalsIgnoreCase("null") && !capitalAcquisJanv.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setCapitalAcquisJanv(Double.parseDouble(capitalAcquisJanv.trim()));

                        if ( cotisationAnnuelle != null && !cotisationAnnuelle.isEmpty() && !cotisationAnnuelle.equalsIgnoreCase("null") && !cotisationAnnuelle.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setCotisationAnnuelle(Double.parseDouble(cotisationAnnuelle.trim()));

                        if (primeNette !=null && !primeNette.isEmpty() && !primeNette.equalsIgnoreCase("null") && !primeNette.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setPrimeNette(Double.parseDouble(primeNette.trim()));

                        if (interet != null && !interet.isEmpty() && !interet.equalsIgnoreCase("null") && !interet.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setInteret(Double.parseDouble(interet.trim()));

                        if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null") && !prestation.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setPrestation(Double.parseDouble(prestation.trim()));

                        if ( capitalAu != null && !capitalAu.isEmpty() && !capitalAu.equalsIgnoreCase("null") && !capitalAu.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setCapitalAu(Double.parseDouble(capitalAu.trim()));

                        if (taux !=null && !taux.isEmpty() && !taux.equalsIgnoreCase("null") && !taux.equalsIgnoreCase("NULL"))
                            situationCompteGlobale.setTauxRevaloEpargne(taux.trim());

                        if (periodicite !=null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null"))
                            situationCompteGlobale.setPeriodicite(periodicite.trim());

                        if (cumulPrime !=null && !cumulPrime.isEmpty() && !cumulPrime.equalsIgnoreCase("null"))
                            situationCompteGlobale.setCumulPrimeAu31122016(Double.parseDouble(cumulPrime.trim()));

                        situationCompteGlobale.setIdContactant(Long.parseLong(id.trim()));

                        situationCompteGlobaleRepository.save(situationCompteGlobale);
                        System.out.println(j+" situation Compte GLOBALE traitée");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    public void local(String pathname,SituationCompteGlobaleRepository situationCompteGlobaleRepository){
        if (situationCompteGlobaleRepository.count() ==0){
            List<String>  data = LoadCsvFile.getData(pathname);
            SituationCompteGlobale situationCompteGlobale;
            //0-_id,// 1-_rev,// 2-CapitalAU,// 3-CapitalAcquisJanv,// 4-Contractant,// 5-CotisationAnnuelle,// 6-DateSituation,// 7-Idcontractant,// 8-Interet,// 9-Prestation,// 10-PrimeNette,// 11-tauxRevaloEpargne,// 12-typeContrat
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                situationCompteGlobale = new SituationCompteGlobale();
                try {

                    if (s.split(",").length>0){
                        String id                     = s.split(",")[0];
                        String assure                 = s.split(",")[1];
                        String capital_acquis_janv    = s.split(",")[2];
                        String capital_au             = s.split(",")[3];
                        String code_contrat           = s.split(",")[4];
                        String contractant            = s.split(",")[5];
                        String cotisation_annuelle    = s.split(",")[6];
                        String cumul_prime_au31122016 = s.split(",")[7];
                        String date_situation         = s.split(",")[8];
                        String id_contactant          = s.split(",")[9];
                        String interet                = s.split(",")[10];
                        String periodicite            = s.split(",")[11];
                        String prestation             = s.split(",")[12];
                        String prime_nette            = s.split(",")[13];
                        String taux_revalo            = s.split(",")[14];
                        String type_contrat           = s.split(",")[15];

                        if (id_contactant != null && !id_contactant.isEmpty() && !id_contactant.equalsIgnoreCase("-") && !id_contactant.equalsIgnoreCase("null") && !id_contactant.equalsIgnoreCase("NULL")){
                            if (capital_acquis_janv != null && !capital_acquis_janv.isEmpty() && !capital_acquis_janv.equalsIgnoreCase("null") && !capital_acquis_janv.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCapitalAcquisJanv(Double.parseDouble(capital_acquis_janv.trim()));

                            if (capital_au != null && !capital_au.isEmpty() && !capital_au.equalsIgnoreCase("null") && !capital_au.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCapitalAu(Double.parseDouble(capital_au.trim()));

                            if (contractant != null && !contractant.isEmpty() && !contractant.equalsIgnoreCase("null") && !contractant.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setContractant(contractant.trim());

                            if ( cotisation_annuelle != null && !cotisation_annuelle.isEmpty() && !cotisation_annuelle.equalsIgnoreCase("null") && !cotisation_annuelle.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCotisationAnnuelle(Double.parseDouble(cotisation_annuelle.trim()));

                            if ( cumul_prime_au31122016 != null && !cumul_prime_au31122016.isEmpty() && !cumul_prime_au31122016.equalsIgnoreCase("null") && !cumul_prime_au31122016.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setCumulPrimeAu31122016(Double.parseDouble(cumul_prime_au31122016.trim()));

                            if (date_situation != null && !date_situation.isEmpty() && !date_situation.equalsIgnoreCase("null") && !date_situation.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setDateSituation(new SimpleDateFormat("yyyy-MM-dd").parse(date_situation));

                            situationCompteGlobale.setIdContactant(Long.parseLong(id_contactant.trim()));

                            if (interet != null && !interet.isEmpty() && !interet.equalsIgnoreCase("null") && !interet.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setInteret(Double.parseDouble(interet.trim()));

                            if (periodicite != null && !periodicite.isEmpty() && !periodicite.equalsIgnoreCase("null") && !periodicite.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPeriodicite(periodicite.trim());

                            if (prestation !=null && !prestation.isEmpty() && !prestation.equalsIgnoreCase("null") && !prestation.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPrestation(Double.parseDouble(prestation.trim()));

                            if (prime_nette !=null && !prime_nette.isEmpty() && !prime_nette.equalsIgnoreCase("null") && !prime_nette.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setPrimeNette(Double.parseDouble(prime_nette.trim()));

                            if (taux_revalo !=null && !taux_revalo.isEmpty() && !taux_revalo.equalsIgnoreCase("null") && !taux_revalo.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setTauxRevaloEpargne(taux_revalo.trim());

                            if (type_contrat !=null && !type_contrat.isEmpty() && !type_contrat.equalsIgnoreCase("null") && !type_contrat.equalsIgnoreCase("NULL"))
                                situationCompteGlobale.setTypeContrat(type_contrat.trim());

                            situationCompteGlobaleRepository.save(situationCompteGlobale);
                            System.out.println(j+" situation Compte GLOBALE traitée");
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
