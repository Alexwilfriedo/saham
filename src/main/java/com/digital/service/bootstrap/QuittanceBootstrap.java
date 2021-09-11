package com.digital.service.bootstrap;

import com.digital.model.Personne;
import com.digital.model.Quitance;
import com.digital.repository.*;
import com.digital.util.LoadCsvFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuittanceBootstrap {

    public  void seed(String pathname, String pthnm,
                      ContratRepository contratRepository,
                      ObjetsRepository objetsRepository,
                      StatutQuitanceRepository statutQuitanceRepository,
                      QuitanceRepository quitanceRepository) {

        if (quitanceRepository.count() == 0){
            String s = "";
            BufferedReader br = null ;
            PrintWriter pw = null;

            int j=0;
            try {
                br = new BufferedReader(new FileReader(pathname));
                br.readLine(); // lecture de l'entet
                while ((s = br.readLine()) != null){
                    if (s.split(",").length>0){
                        j++;
                        Quitance quitance = new Quitance();
                        String codeContrat = s.split(",")[2];
                        String codeObjet   = s.split(",")[3];
                        String dateC       = s.split(",")[4];
                        String dateEmis    = s.split(",")[5];
                        String dateV       = s.split(",")[6];
                        String dateCr      = s.split(",")[7];
                        String echeance    = s.split(",")[8];
                        String montantEmis = s.split(",")[9];
                        String montantpaye = s.split(",")[10];
                        String numQuit     = s.split(",")[11];
                        String statut      = s.split(",")[12];

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy");



                        if (echeance != null && !echeance.isEmpty() && !echeance.equalsIgnoreCase("null") && !echeance.equalsIgnoreCase("NULL")){
                            if (codeContrat != null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("null") && !codeContrat.equalsIgnoreCase("NULL")) quitance.setPolice(codeContrat.replaceAll("\\s",""));

                            if (codeObjet !=null && !codeObjet.isEmpty() && !codeObjet.equalsIgnoreCase("null") && !codeObjet.equalsIgnoreCase("NULL")) quitance.setObj(Long.parseLong(codeObjet));

                            if (dateC !=null && !dateC.isEmpty() && !dateC.equalsIgnoreCase("null") && !dateC.equalsIgnoreCase("NULL")){
                                if ((dateC.split("/")[2]).length()==2) quitance.setDateComptable(formatter2.parse(dateC));
                                else quitance.setDateComptable(formatter.parse(dateC));
                            }
                            if (dateEmis !=null && !dateEmis.isEmpty() && !dateEmis.equalsIgnoreCase("null") && !dateEmis.equalsIgnoreCase("NULL")){
                                if ((dateEmis.split("/")[2]).length()==2) quitance.setDateEmission(formatter2.parse(dateEmis));
                                else quitance.setDateEmission(formatter.parse(dateEmis));
                            }
                            if (dateV !=null && !dateV.isEmpty() && !dateV.equalsIgnoreCase("null") && !dateV.equalsIgnoreCase("NULL")){
                                if ((dateV.split("/")[2]).length()==2) quitance.setDateValeur(formatter2.parse(dateV));
                                else quitance.setDateValeur(formatter.parse(dateV));
                            }
                            if (dateCr !=null && !dateCr.isEmpty() && !dateCr.equalsIgnoreCase("null") && !dateCr.equalsIgnoreCase("NULL")){
                                if ((dateCr.split("/")[2]).length()==2) quitance.setDateCreation(formatter2.parse(dateCr));
                                else quitance.setDateCreation(formatter.parse(dateCr));
                            }

                            if ((echeance.split("/")[2]).length()==2) quitance.setEcheance(formatter2.parse(echeance));
                            else quitance.setEcheance(formatter.parse(echeance));

                            if (montantEmis != null && !montantEmis.isEmpty() && !montantEmis.trim().equalsIgnoreCase("NULL") && !montantEmis.equalsIgnoreCase("null")) //quitance.setMontantEmis(Double.parseDouble(montantEmis.split(",")[0].replaceAll("\\s","")));
                                quitance.setMontantEmis(Double.parseDouble(montantEmis.replaceAll("\\s","")));

                            if (montantpaye != null && !montantpaye.isEmpty() && !montantpaye.trim().equalsIgnoreCase("NULL") && !montantpaye.equalsIgnoreCase("null")) //quitance.setMontantPaye(Double.parseDouble(montantpaye.split(",")[0].replaceAll("\\s","")));
                                quitance.setMontantPaye(Double.parseDouble(montantpaye.replaceAll("\\s","")));

                            if (statut !=null && !statut.isEmpty() && !statut.equalsIgnoreCase("null") && !statut.equalsIgnoreCase("NULL")) quitance.setStatut(Long.parseLong(statut));

                            if (numQuit !=null && !numQuit.isEmpty() && !numQuit.equalsIgnoreCase("null") && !numQuit.equalsIgnoreCase("NULL")) //quitance.setNumQuittance(String.valueOf(new Date().getTime()));
                                quitance.setNumQuittance(numQuit);


                            //if (quitanceRepository.findByNumQuittance(quitance.getNumQuittance()) == null){
                            System.out.println("Quittance en cours de traitement");
                            quitanceRepository.save(quitance);
                            //pw = new PrintWriter(new FileWriter(pthnm));
                            //pw.println(s);
                            System.out.println(j+" quittance traitée");
                            //}
                        }

                    }

                }
            }
            catch (Exception e){
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void erase(String pathname, QuitanceRepository quitanceRepository) {
        List<String> data = LoadCsvFile.getData(pathname);
        int i=0;

        for(int j = 1; j < data.size(); ++j){
            String s = data.get(j);
            i++;

            if (s.split(",").length > 0){

                String numQuit     = s.split(",")[2];
                String codeContrat = s.split(",")[3];

                if (numQuit != null && !numQuit.isEmpty() && !numQuit.equalsIgnoreCase("null")){
                    List<Quitance> quitances = quitanceRepository.findByPolice(codeContrat);

                    if (quitances.size() > 0){
                        quitances.forEach(quitance -> quitanceRepository.delete(quitance.getId()));
                    }
                }
            }
        }
    }

    public void load(String pathname, QuitanceRepository quitanceRepository) {
        List<String> data = LoadCsvFile.getData(pathname);
        int i=0;

        for(int j = 1; j < data.size(); ++j){
            String s = data.get(j);
            i++;

            try{
                if (s.split(",").length > 0){
                    Quitance quitance = new Quitance();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy");

                    String codeObjet   = s.split(",")[0];
                    String statut      = s.split(",")[1];
                    String numQuit     = s.split(",")[2];
                    String codeContrat = s.split(",")[3];
                    String echeance    = s.split(",")[4];
                    String dateValeur  = s.split(",")[5];
                    String dateCreat   = s.split(",")[6];
                    String dateCompt   = s.split(",")[7];
                    String dateEmis    = s.split(",")[8];
                    String montantpaye = s.split(",")[9];
                    String montantEmis = s.split(",")[10];

                    if (numQuit != null && !numQuit.isEmpty() && !numQuit.equalsIgnoreCase("null")){

                        quitance = quitanceRepository.findByNumQuittance(numQuit);

                        if (quitance == null) {
                            quitance = new Quitance();
                            if (codeContrat != null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("null") && !codeContrat.equalsIgnoreCase("NULL"))
                                quitance.setPolice(codeContrat.replaceAll("\\s",""));
                        }
                        //quitance = new Quitance();

                        if (codeObjet !=null && !codeObjet.isEmpty() && !codeObjet.equalsIgnoreCase("null") && !codeObjet.equalsIgnoreCase("NULL"))
                            quitance.setObj(Long.parseLong(codeObjet));

                        if (statut !=null && !statut.isEmpty() && !statut.equalsIgnoreCase("null") && !statut.equalsIgnoreCase("NULL"))
                            quitance.setStatut(Long.parseLong(statut));

                        /*
                        if (codeContrat != null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("null") && !codeContrat.equalsIgnoreCase("NULL"))
                            quitance.setPolice(codeContrat.replaceAll("\\s",""));
                        */

                        if (echeance != null && !echeance.isEmpty() && !echeance.equalsIgnoreCase("null") && !echeance.equalsIgnoreCase("NULL")){
                            if ((echeance.split("/")[2]).length()==2) quitance.setEcheance(formatter2.parse(echeance));
                            else quitance.setEcheance(formatter.parse(echeance));
                        }

                        if (dateValeur !=null && !dateValeur.isEmpty() && !dateValeur.equalsIgnoreCase("null") && !dateValeur.equalsIgnoreCase("NULL")){
                            if ((dateValeur.split("/")[2]).length()==2) quitance.setDateValeur(formatter2.parse(dateValeur));
                            else quitance.setDateValeur(formatter.parse(dateValeur));
                        }

                        if (dateCreat != null && !dateCreat.isEmpty() && !dateCreat.equalsIgnoreCase("null") && !dateCreat.equalsIgnoreCase("NULL")){
                            if ((dateCreat.split("/")[2]).length()==2) quitance.setDateCreation(formatter2.parse(dateCreat));
                            else quitance.setDateCreation(formatter.parse(dateCreat));
                        }

                        if (dateCompt !=null && !dateCompt.isEmpty() && !dateCompt.equalsIgnoreCase("null") && !dateCompt.equalsIgnoreCase("NULL")){
                            if ((dateCompt.split("/")[2]).length()==2) quitance.setDateComptable(formatter2.parse(dateCompt));
                            else quitance.setDateComptable(formatter.parse(dateCompt));
                        }

                        if (dateEmis !=null && !dateEmis.isEmpty() && !dateEmis.equalsIgnoreCase("null") && !dateEmis.equalsIgnoreCase("NULL")){
                            if ((dateEmis.split("/")[2]).length()==2) quitance.setDateEmission(formatter2.parse(dateEmis));
                            else quitance.setDateEmission(formatter.parse(dateEmis));
                        }

                        if (montantpaye != null && !montantpaye.isEmpty() && !montantpaye.trim().equalsIgnoreCase("NULL") && !montantpaye.equalsIgnoreCase("null")) //quitance.setMontantPaye(Double.parseDouble(montantpaye.split(",")[0].replaceAll("\\s","")));
                            quitance.setMontantPaye(Double.parseDouble(montantpaye.replaceAll("\\s","")));

                        if (montantEmis != null && !montantEmis.isEmpty() && !montantEmis.trim().equalsIgnoreCase("NULL") && !montantEmis.equalsIgnoreCase("null")) //quitance.setMontantEmis(Double.parseDouble(montantEmis.split(",")[0].replaceAll("\\s","")));
                            quitance.setMontantEmis(Double.parseDouble(montantEmis));

                        quitance.setNumQuittance(numQuit);

                        System.out.println("Quittance en cours de traitement");
                        quitanceRepository.save(quitance);
                        System.out.println(quitance);
                        System.out.println(j+" quittance traitée");
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

    }

    public  void local(String pathname, QuitanceRepository quitanceRepository) {

        System.out.println("chargement des quittances");
        String s = "";
        BufferedReader br = null ;
        PrintWriter pw = null;

        int j=0;
        try {
            br = new BufferedReader(new FileReader(pathname));
            br.readLine(); // lecture de l'entet
            while ((s = br.readLine()) != null){
                if (s.split(",").length>0){
                    j++;
                    Quitance quitance = new Quitance();
                    String id             = s.split(",")[0];
                    String date_comptable = s.split(",")[1];
                    String dt_creation    = s.split(",")[2];
                    String date_emission  = s.split(",")[3];
                    String date_valeur    = s.split(",")[4];
                    String echeance       = s.split(",")[5];
                    String montant_emis   = s.split(",")[6];
                    String montant_paye   = s.split(",")[7];
                    String num_quittance  = s.split(",")[8];
                    String obj            = s.split(",")[9];
                    String police         = s.split(",")[10];
                    String statut         = s.split(",")[11];
                    String code_contrat   = s.split(",")[12];
                    String code_objet     = s.split(",")[13];
                    String id_statut_quit = s.split(",")[14];
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    if (echeance != null  && !echeance.isEmpty() && !echeance.equalsIgnoreCase("null") && !echeance.equalsIgnoreCase("NULL")){
                        if (num_quittance != null  && !num_quittance.isEmpty() && !num_quittance.equalsIgnoreCase("null") && !num_quittance.equalsIgnoreCase("NULL")){
                            quitance.setNumQuittance(num_quittance.replaceAll("\\s",""));

                            if (police != null && !police.isEmpty() && !police.equalsIgnoreCase("null") && !police.equalsIgnoreCase("NULL")) quitance.setPolice(police.replaceAll("\\s",""));

                            if (obj !=null && !obj.isEmpty() && !obj.equalsIgnoreCase("null") && !obj.equalsIgnoreCase("NULL")) quitance.setObj(Long.parseLong(obj));

                            if (date_comptable !=null && !date_comptable.isEmpty() && !date_comptable.equalsIgnoreCase("null") && !date_comptable.equalsIgnoreCase("NULL"))
                                quitance.setDateComptable(formatter.parse(date_comptable));

                            if (date_emission !=null && !date_emission.isEmpty() && !date_emission.equalsIgnoreCase("null") && !date_emission.equalsIgnoreCase("NULL"))
                                quitance.setDateEmission(formatter.parse(date_emission));

                            if (date_valeur !=null && !date_valeur.isEmpty() && !date_valeur.equalsIgnoreCase("null") && !date_valeur.equalsIgnoreCase("NULL"))
                                quitance.setDateValeur(formatter.parse(date_valeur));

                            if (dt_creation !=null && !dt_creation.isEmpty() && !dt_creation.equalsIgnoreCase("null") && !dt_creation.equalsIgnoreCase("NULL"))
                                quitance.setDateCreation(formatter.parse(dt_creation));

                            quitance.setEcheance(formatter.parse(echeance));

                            if (montant_emis != null && !montant_emis.isEmpty() && !montant_emis.trim().equalsIgnoreCase("NULL") && montant_emis.equalsIgnoreCase("null")) //quitance.setMontantEmis(Double.parseDouble(montantEmis.split(",")[0].replaceAll("\\s","")));
                                quitance.setMontantEmis(Double.parseDouble(montant_emis.replaceAll("\\s","")));

                            if (montant_paye != null && !montant_paye.isEmpty() && !montant_paye.trim().equalsIgnoreCase("NULL") && !montant_paye.equalsIgnoreCase("null")) //quitance.setMontantPaye(Double.parseDouble(montantpaye.split(",")[0].replaceAll("\\s","")));
                                quitance.setMontantPaye(Double.parseDouble(montant_paye.replaceAll("\\s","")));

                            if (statut !=null && !statut.isEmpty() && !statut.equalsIgnoreCase("null") && !statut.equalsIgnoreCase("NULL")) quitance.setStatut(Long.parseLong(statut));

                            System.out.println("Quittance en cours de traitement");
                            quitanceRepository.save(quitance);
                            //pw = new PrintWriter(new FileWriter(pthnm));
                            //pw.println(s);
                            System.out.println(j+" quittance traitée");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
        if (quitanceRepository.count() == 0){
            List<String> data = LoadCsvFile.getData(pathname);
            System.out.println("Mise a jour de la table quitance");
            Quitance quitance;
            for(int j = 1; j < data.size(); j++) {
                quitance = new Quitance();
                String s = data.get(j);

                try {
                    if (s.split(",").length>0){
                        String id                = s.split(",")[0];
                        String date_comptable           = s.split(",")[1];
                        String dt_creation    = s.split(",")[2];
                        String date_emission     = s.split(",")[3];
                        String date_valeur          = s.split(",")[4];
                        String echeance      = s.split(",")[5];
                        String montant_emis             = s.split(",")[6];
                        String montant_paye           = s.split(",")[7];
                        String num_quittance          = s.split(",")[8];
                        String obj               = s.split(",")[9];
                        String police    = s.split(",")[10];
                        String statut         = s.split(",")[11];
                        String code_contrat        = s.split(",")[12];
                        String code_objet      = s.split(",")[13];
                        String id_statut_quit         = s.split(",")[14];
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                        if (echeance != null  && !echeance.isEmpty() && !echeance.equalsIgnoreCase("null") && !echeance.equalsIgnoreCase("NULL")){
                            if (num_quittance != null  && !num_quittance.isEmpty() && !num_quittance.equalsIgnoreCase("null") && !num_quittance.equalsIgnoreCase("NULL")){
                                quitance.setNumQuittance(num_quittance.replaceAll("\\s",""));

                                if (police != null && !police.isEmpty() && !police.equalsIgnoreCase("null") && !police.equalsIgnoreCase("NULL")) quitance.setPolice(police.replaceAll("\\s",""));

                                if (obj !=null && !obj.isEmpty() && !obj.equalsIgnoreCase("null") && !obj.equalsIgnoreCase("NULL")) quitance.setObj(Long.parseLong(obj));

                                if (date_comptable !=null && !date_comptable.isEmpty() && !date_comptable.equalsIgnoreCase("null") && !date_comptable.equalsIgnoreCase("NULL"))
                                    quitance.setDateComptable(formatter.parse(date_comptable));

                                if (date_emission !=null && !date_emission.isEmpty() && !date_emission.equalsIgnoreCase("null") && !date_emission.equalsIgnoreCase("NULL"))
                                    quitance.setDateEmission(formatter.parse(date_emission));

                                if (date_valeur !=null && !date_valeur.isEmpty() && !date_valeur.equalsIgnoreCase("null") && !date_valeur.equalsIgnoreCase("NULL"))
                                    quitance.setDateValeur(formatter.parse(date_valeur));

                                if (dt_creation !=null && !dt_creation.isEmpty() && !dt_creation.equalsIgnoreCase("null") && !dt_creation.equalsIgnoreCase("NULL"))
                                    quitance.setDateCreation(formatter.parse(dt_creation));

                                quitance.setEcheance(formatter.parse(echeance));

                                if (montant_emis != null && !montant_emis.isEmpty() && !montant_emis.trim().equalsIgnoreCase("NULL") && montant_emis.equalsIgnoreCase("null")) //quitance.setMontantEmis(Double.parseDouble(montantEmis.split(",")[0].replaceAll("\\s","")));
                                    quitance.setMontantEmis(Double.parseDouble(montant_emis.replaceAll("\\s","")));

                                if (montant_paye != null && !montant_paye.isEmpty() && !montant_paye.trim().equalsIgnoreCase("NULL") && !montant_paye.equalsIgnoreCase("null")) //quitance.setMontantPaye(Double.parseDouble(montantpaye.split(",")[0].replaceAll("\\s","")));
                                    quitance.setMontantPaye(Double.parseDouble(montant_paye.replaceAll("\\s","")));

                                if (statut !=null && !statut.isEmpty() && !statut.equalsIgnoreCase("null") && !statut.equalsIgnoreCase("NULL")) quitance.setStatut(Long.parseLong(statut));

                                System.out.println("Quittance en cours de traitement");
                                quitanceRepository.save(quitance);
                                //pw = new PrintWriter(new FileWriter(pthnm));
                                //pw.println(s);
                                System.out.println(j+" quittance traitée");
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }
        **/

    }

    public void updateMontatEmis(String pathname, QuitanceRepository quitanceRepository) {
        List<String> data = LoadCsvFile.getData(pathname);
        int i=0;

        for(int j = 1; j < data.size(); ++j){
            String s = data.get(j);
            i++;

            try{
                if (s.split(",").length > 0){
                    Quitance quitance = new Quitance();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yy");

                    String numQuit     = s.split(",")[2];
                    String montantpaye = s.split(",")[9];
                    String montantEmis = s.split(",")[10];

                    if (numQuit != null && !numQuit.isEmpty() && !numQuit.equalsIgnoreCase("null")){

                        quitance = quitanceRepository.findByNumQuittance(numQuit);

                        if (quitance == null) quitance = new Quitance();

                        if (montantpaye != null && !montantpaye.isEmpty() && !montantpaye.trim().equalsIgnoreCase("NULL") && !montantpaye.equalsIgnoreCase("null")) //quitance.setMontantPaye(Double.parseDouble(montantpaye.split(",")[0].replaceAll("\\s","")));
                            quitance.setMontantPaye(Double.parseDouble(montantpaye.replaceAll("\\s","")));

                        System.out.println("Montant Emis 0 : "+montantEmis);

                        if (montantEmis != null && !montantEmis.isEmpty() && !montantEmis.trim().equalsIgnoreCase("NULL") && !montantEmis.equalsIgnoreCase("null")) {
                            System.out.println("Montant Emis 1 : "+montantEmis);
                            quitance.setMontantEmis(Double.parseDouble(montantEmis));
                        }

                        //quitance.setNumQuittance(numQuit);

                        Quitance quitanceUpdated = quitanceRepository.save(quitance);

                        System.out.println("Numero quittance : "+quitanceUpdated.getNumQuittance());
                        System.out.println("Montant Emis 2 : "+quitanceUpdated.getMontantEmis());
                        System.out.println("Montant payé : "+quitanceUpdated.getMontantPaye());
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

    }
}
