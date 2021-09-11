package com.digital.service.bootstrap;

import com.digital.model.*;
import com.digital.repository.*;
import com.digital.util.LoadCsvFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ContratBootstrap {

    public  void seed(String pathname, ContratRepository contratRepository, PersonneRepository personneRepository, ProduitRepository produitRepository, StatutContratRepository statutContratRepository, PeriodiciteRepository periodiciteRepository){
        if (contratRepository.count() ==0){
            List<String> data = LoadCsvFile.getData(pathname);
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                try {
                    if (s.split(",").length>0){
                        Contrat contrat = new Contrat();

                        //String id                    = s.split(",")[0];
                        String code_statut_contrat   = s.split(",")[2];
                        String codeperiodicite       = s.split(",")[3];
                        String dateEffet             = s.split(",")[4];
                        String datefin               = s.split(",")[5];
                        String dure                  = s.split(",")[6];
                        String id_souscripteur       = s.split(",")[7];
                        String idassure              = s.split(",")[8];
                        String police                = s.split(",")[9];
                        String prime                 = s.split(",")[10];
                        String produitcode           = s.split(",")[11];

                        if (police != null && !police.isEmpty() && !police.equalsIgnoreCase("null") && !police.equalsIgnoreCase("NULL")){
                            if (code_statut_contrat !=null && !code_statut_contrat.isEmpty() &&  !code_statut_contrat.equalsIgnoreCase("NULL") &&  !code_statut_contrat.equalsIgnoreCase("null")) contrat.setId_statut(Long.parseLong(code_statut_contrat));

                            if (codeperiodicite != null && !codeperiodicite.isEmpty() && !codeperiodicite.equalsIgnoreCase("NULL")&& !codeperiodicite.equalsIgnoreCase("null")) contrat.setCodePeriodicite(Long.parseLong(codeperiodicite));

                            if (dateEffet !=null && !dateEffet.isEmpty() && !dateEffet.equalsIgnoreCase("null") && !dateEffet.equalsIgnoreCase("NULL")){
                                if ((dateEffet.split("/")[2]).length()==2) contrat.setDateEffet(new SimpleDateFormat("dd/MM/yy").parse(dateEffet));
                                else contrat.setDateEffet(new SimpleDateFormat("dd/MM/yyyy").parse(dateEffet));
                            }

                            if (datefin !=null && !datefin.isEmpty() && !datefin.equalsIgnoreCase("null") && !datefin.equalsIgnoreCase("NULL")){
                                if ((datefin.split("/")[2]).length()==2) contrat.setDateFin(new SimpleDateFormat("dd/MM/yy").parse(datefin));
                                else contrat.setDateFin(new SimpleDateFormat("dd/MM/yyyy").parse(datefin));
                            }

                            if (dure !=null && !dure.isEmpty() && !dure.equalsIgnoreCase("NULL") && !dure.equalsIgnoreCase("null")) contrat.setDuree(Integer.parseInt(dure));

                            if (id_souscripteur !=null && !id_souscripteur.isEmpty() && !id_souscripteur.equalsIgnoreCase("NULL") && !id_souscripteur.equalsIgnoreCase("null")) contrat.setId_souscrpteur(Long.parseLong(id_souscripteur.replaceAll("\\s","")));

                            if (idassure != null && !idassure.isEmpty() && !idassure.equalsIgnoreCase("NULL")&& !idassure.equalsIgnoreCase("null")) contrat.setId_assure(Long.parseLong(idassure.replaceAll("\\s","")));

                            contrat.setPolice(police);

                            if (prime != null && !prime.isEmpty() && !prime.equalsIgnoreCase("NULL")&& !prime.equalsIgnoreCase("null")) contrat.setPrime(Double.parseDouble(prime.replaceAll("\\s","")));

                            if (produitcode !=null && !produitcode.isEmpty() && !produitcode.equalsIgnoreCase("NULL")&& !produitcode.equalsIgnoreCase("null")) contrat.setProduitCode(Long.parseLong(produitcode));

                            contratRepository.save(contrat);
                            System.out.println(j+" contrat traitée");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public  void load(String pathname, ContratRepository contratRepository){
        List<String> data = LoadCsvFile.getData(pathname);
        int i=0;
        for(int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            i++;
            try {
                if (s.split(",").length > 0){
                    Contrat contrat;

                    /**
                    String police                = s.split(",")[0];
                    String idperiodicite         = s.split(",")[1];
                    String codeperiodicite       = s.split(",")[2];
                    String code_statut_contrat   = s.split(",")[3];
                    String id_souscripteur       = s.split(",")[4];
                    String idassure              = s.split(",")[5];
                    String produitcode           = s.split(",")[6];
                    String dateEffet             = s.split(",")[7];
                    String prime                 = s.split(",")[11];
                    **/

                    String code_statut_contrat = s.split(",")[0];
                    String codeperiodicite     = s.split(",")[1];
                    String dateEffet           = s.split(",")[2];
                    String datefin             = s.split(",")[3];
                    String duree               = s.split(",")[4];
                    String idassure            = s.split(",")[5];
                    String id_souscripteur     = s.split(",")[6];
                    String police              = s.split(",")[7];
                    String prime               = s.split(",")[8];
                    String produitcode         = s.split(",")[9];


                    if (police != null && !police.isEmpty() && !police.equalsIgnoreCase("null") && !police.equalsIgnoreCase("NULL")){
                        contrat = contratRepository.findFirstByPolice(police.replaceAll("\\s",""));

                        if (contrat == null) contrat = new Contrat();

                        if (codeperiodicite != null && !codeperiodicite.isEmpty() && !codeperiodicite.equalsIgnoreCase("NULL")&& !codeperiodicite.equalsIgnoreCase("null")) contrat.setCodePeriodicite(Long.parseLong(codeperiodicite));

                        if (code_statut_contrat !=null && !code_statut_contrat.isEmpty() &&  !code_statut_contrat.equalsIgnoreCase("NULL") &&  !code_statut_contrat.equalsIgnoreCase("null")) contrat.setId_statut(Long.parseLong(code_statut_contrat));

                        if (id_souscripteur !=null && !id_souscripteur.isEmpty() && !id_souscripteur.equalsIgnoreCase("NULL") && !id_souscripteur.equalsIgnoreCase("null")) contrat.setId_souscrpteur(Long.parseLong(id_souscripteur.replaceAll("\\s","")));

                        if (idassure != null && !idassure.isEmpty() && !idassure.equalsIgnoreCase("NULL")&& !idassure.equalsIgnoreCase("null")) contrat.setId_assure(Long.parseLong(idassure.replaceAll("\\s","")));

                        if (produitcode != null && !produitcode.isEmpty() && !produitcode.equalsIgnoreCase("NULL")&& !produitcode.equalsIgnoreCase("null")) contrat.setProduitCode(Long.parseLong(produitcode));

                        if (dateEffet != null && !dateEffet.isEmpty() && !dateEffet.equalsIgnoreCase("null") && !dateEffet.equalsIgnoreCase("NULL")){
                            if ((dateEffet.split("/")[2]).length()==2) contrat.setDateEffet(new SimpleDateFormat("dd/MM/yy").parse(dateEffet));
                            else contrat.setDateEffet(new SimpleDateFormat("dd/MM/yyyy").parse(dateEffet));
                        }

                        if (datefin != null && !datefin.isEmpty() && !datefin.equalsIgnoreCase("null") && !datefin.equalsIgnoreCase("NULL")){
                            if ((datefin.split("/")[2]).length()==2) contrat.setDateFin(new SimpleDateFormat("dd/MM/yy").parse(dateEffet));
                            else contrat.setDateFin(new SimpleDateFormat("dd/MM/yyyy").parse(dateEffet));
                        }

                        if (duree != null && !duree.isEmpty() && !duree.equalsIgnoreCase("NULL")&& !duree.equalsIgnoreCase("null")) contrat.setDuree(Integer.parseInt(duree));

                        contrat.setPolice(police.replaceAll("\\s",""));

                        if (prime != null && !prime.isEmpty() && !prime.equalsIgnoreCase("NULL")&& !prime.equalsIgnoreCase("null")) contrat.setPrime(Double.parseDouble(prime.replaceAll("\\s","")));

                        //contratRepository.save(contrat);
                        System.out.println(j+" contrat traitée");
                        System.out.println(contrat.toString());
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }

    public  void local(String pathname, ContratRepository contratRepository){
        if (contratRepository.count() == 0){
            List<String> data = LoadCsvFile.getData(pathname);
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;
                try {
                    if (s.split(",").length>0){
                        Contrat contrat = new Contrat();

                        String id                  = s.split(",")[0];
                        String code_periodicite    = s.split(",")[1];
                        String date_effet          = s.split(",")[2];
                        String date_fin            = s.split(",")[3];
                        String duree               = s.split(",")[4];
                        String id_assure           = s.split(",")[5];
                        String id_souscrpteur      = s.split(",")[6];
                        String id_statut           = s.split(",")[7];
                        String code_contrat        = s.split(",")[8];
                        String prime               = s.split(",")[9];
                        String produit_code        = s.split(",")[10];
                        String assure_id           = s.split(",")[11];
                        String id_periodicite      = s.split(",")[12];
                        String code_produit        = s.split(",")[13];
                        String souscripteur_id     = s.split(",")[14];
                        String code_statut_contrat = s.split(",")[15];

                        if (code_contrat != null && !code_contrat.isEmpty() && !code_contrat.equalsIgnoreCase("null") && !code_contrat.equalsIgnoreCase("NULL")){
                            if (code_statut_contrat !=null && !code_statut_contrat.isEmpty() &&  !code_statut_contrat.equalsIgnoreCase("NULL") &&  !code_statut_contrat.equalsIgnoreCase("null")) contrat.setId_statut(Long.parseLong(code_statut_contrat));

                            if (code_periodicite != null && !code_periodicite.isEmpty() && !code_periodicite.equalsIgnoreCase("NULL")&& !code_periodicite.equalsIgnoreCase("null")) contrat.setCodePeriodicite(Long.parseLong(code_periodicite));

                            if (date_effet !=null && !date_effet.isEmpty() && !date_effet.equalsIgnoreCase("null") && !date_effet.equalsIgnoreCase("NULL"))
                                contrat.setDateEffet(new SimpleDateFormat("yyyy-MM-dd").parse(date_effet));

                            if (date_fin !=null && !date_fin.isEmpty() && !date_fin.equalsIgnoreCase("null") && !date_fin.equalsIgnoreCase("NULL"))
                                contrat.setDateFin(new SimpleDateFormat("yyyy-MM-dd").parse(date_fin));

                            if (duree !=null && !duree.isEmpty() && !duree.equalsIgnoreCase("NULL") && !duree.equalsIgnoreCase("null")) contrat.setDuree(Integer.parseInt(duree));

                            if (id_souscrpteur !=null && !id_souscrpteur.isEmpty() && !id_souscrpteur.equalsIgnoreCase("NULL") && !id_souscrpteur.equalsIgnoreCase("null")) contrat.setId_souscrpteur(Long.parseLong(id_souscrpteur.replaceAll("\\s","")));

                            if (id_assure != null && !id_assure.isEmpty() && !id_assure.equalsIgnoreCase("NULL")&& !id_assure.equalsIgnoreCase("null")) contrat.setId_assure(Long.parseLong(id_assure.replaceAll("\\s","")));

                            contrat.setPolice(code_contrat);

                            if (prime != null && !prime.isEmpty() && !prime.equalsIgnoreCase("NULL")&& !prime.equalsIgnoreCase("null")) contrat.setPrime(Double.parseDouble(prime.replaceAll("\\s","")));

                            if (produit_code !=null && !produit_code.isEmpty() && !produit_code.equalsIgnoreCase("NULL")&& !produit_code.equalsIgnoreCase("null")) contrat.setProduitCode(Long.parseLong(produit_code));

                            contratRepository.save(contrat);
                            System.out.println(j+" contrat traitée");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public void supprimeDoublons(ContratRepository contratRepository){
        contratRepository.findAll().forEach(contrat -> {
            List<Contrat> list = contratRepository.findAllByPolice(contrat.getPolice());

            if (list.size() > 1){
                Contrat cont_1 = list.get(0);
                list.stream().filter(cont_2 -> !cont_2.getId().equals(cont_1.getId())).collect(Collectors.toList())
                        .forEach(cont -> {
                            if (cont.getId() <= cont_1.getId()) contratRepository.delete(cont.getId());
                            else contratRepository.delete(cont_1.getId());
                        });
            }
        });
    }
}
