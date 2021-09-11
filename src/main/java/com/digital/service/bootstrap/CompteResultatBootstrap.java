package com.digital.service.bootstrap;

import com.digital.model.CompteResultat;
import com.digital.model.SituationCompteGlobale;
import com.digital.repository.CompteResultatRepository;
import com.digital.repository.SituationCompteGlobaleRepository;
import com.digital.util.LoadCsvFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CompteResultatBootstrap {

    public void seed(String pathname, CompteResultatRepository compteResultatRepository){
        List<String>  data = LoadCsvFile.getData(pathname);
        CompteResultat compteResultat;
        int i=0;
        if (compteResultatRepository.count() ==0){
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;

                try {
                    if (s.split(",").length>0){
                        String id =  s.split(",")[0];
                        if (id != null && !id.isEmpty()){
                            compteResultat = new CompteResultat();

                            String codeProduct                  = s.split(",")[2];
                            String contractGrpe                 = s.split(",")[3];
                            String cotisationNetteTaxe          = s.split(",")[4];
                            String dateCompteResultat           = s.split(",")[5];
                            String dateReportSolde              = s.split(",")[6];
                            String fraisGestionSurCotisation    = s.split(",")[7];
                            String fraisGestionSurEpargne       = s.split(",")[8];
                            String idcontractant                = s.split(",")[9];
                            String interetGaranti               = s.split(",")[10];
                            String participationOBenef          = s.split(",")[11];
                            String prestationPayees             = s.split(",")[12];
                            String reportDuSolde                = s.split(",")[13];
                            String societe                      = s.split(",")[14];
                            String soldeCrediteur               = s.split(",")[15];
                            String totalCredit                  = s.split(",")[16];
                            String totalDebit                   = s.split(",")[17];
                            String typeContrat                  = s.split(",")[18];

                            if (codeProduct != null && !codeProduct.isEmpty()) compteResultat.setCodeProduit(Integer.parseInt(codeProduct.replaceAll("\\s","")));
                            if (contractGrpe != null && !contractGrpe.isEmpty()) compteResultat.setContratGroupe(contractGrpe);
                            if (cotisationNetteTaxe != null && !cotisationNetteTaxe.isEmpty()) compteResultat.setCotisationNetteTaxe(Double.parseDouble(cotisationNetteTaxe.replaceAll("\\s","")));
                            if (dateCompteResultat != null && !dateCompteResultat.isEmpty()) compteResultat.setDateCompteResultat(new SimpleDateFormat("dd/MM/yyyy").parse(dateCompteResultat));
                            if (dateReportSolde != null && !dateReportSolde.isEmpty()) compteResultat.setDateReportSolde(new SimpleDateFormat("dd/MM/yyyy").parse(dateReportSolde));
                            if (fraisGestionSurCotisation != null && !fraisGestionSurCotisation.isEmpty()) compteResultat.setFraisGestionSurCotisation(Double.parseDouble(fraisGestionSurCotisation.replaceAll("\\s","")));
                            if (fraisGestionSurEpargne !=null && !fraisGestionSurEpargne.isEmpty()) compteResultat.setFraisGestionSurEpargne(Double.parseDouble(fraisGestionSurEpargne.replaceAll("\\s","")));
                            if (idcontractant != null && !idcontractant.isEmpty()) compteResultat.setIdContactant(Long.parseLong(idcontractant.replaceAll("\\s","")));
                            if (interetGaranti != null && !interetGaranti.isEmpty()) compteResultat.setInteretGaranti(Double.parseDouble(interetGaranti.replaceAll("\\s","")));
                            if (participationOBenef != null && !participationOBenef.isEmpty()) compteResultat.setParticipationOBenef(Double.parseDouble(participationOBenef.replaceAll("\\s","")));
                            if (prestationPayees != null && !prestationPayees.isEmpty()) compteResultat.setPrestationPayees(Double.parseDouble(prestationPayees.replaceAll("\\s","")));
                            if (reportDuSolde != null && !reportDuSolde.isEmpty()) compteResultat.setReportDuSolde(Double.parseDouble(reportDuSolde.replaceAll("\\s","")));
                            if (societe != null && !societe.isEmpty()) compteResultat.setSociete(societe);
                            if (soldeCrediteur != null && !soldeCrediteur.isEmpty()) compteResultat.setSoldeCrediteur(Double.parseDouble(soldeCrediteur.replaceAll("\\s","")));
                            if (totalCredit != null && !totalCredit.isEmpty()) compteResultat.setTotalCredit(Double.parseDouble(totalCredit.replaceAll("\\s","")));
                            if (totalDebit != null && !totalDebit.isEmpty()) compteResultat.setTotalDebit(Double.parseDouble(totalDebit.replaceAll("\\s","")));
                            if (typeContrat != null && !typeContrat.isEmpty()) compteResultat.setTypeContrat(typeContrat);

                            compteResultatRepository.save(compteResultat);
                            //System.out.println(compteResultat.toString());
                            System.out.println(j+"  Compte resultat traitée");

                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }
        }
    }

    public void load(String pathname, CompteResultatRepository compteResultatRepository){
        List<String>  data = LoadCsvFile.getData(pathname);
        int i=0;

        for(int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            i++;

            try {
                if (s.split(",").length > 0){
                    CompteResultat compteResultat;

                    /**
                    String idcontractant                = s.split(",")[0];
                    String societe                      = s.split(",")[1];
                    String typeContrat                  = s.split(",")[2];
                    String contractGrpe                 = s.split(",")[3];
                    String codeProduct                  = s.split(",")[4];
                    String totalDebit                   = s.split(",")[5];
                    String prestationPayees             = s.split(",")[6];
                    String totalCredit                  = s.split(",")[7];
                    String fraisGestionSurCotisation    = s.split(",")[8];
                    String fraisGestionSurEpargne       = s.split(",")[9];
                    String reportDuSolde                = s.split(",")[10];
                    String dateReportSolde              = s.split(",")[11];
                    String cotisationNetteTaxe          = s.split(",")[12];
                    String interetGaranti               = s.split(",")[13];
                    String participationOBenef          = s.split(",")[14];
                    String soldeCrediteur               = s.split(",")[15];
                    String dateCompteResultat           = s.split(",")[16];
                    **/


                    String codeProduct               = s.split(",")[0];
                    String contractGrpe              = s.split(",")[1];
                    String cotisationNetteTaxe       = s.split(",")[2];
                    String dateCompteResultat        = s.split(",")[3];
                    String dateReportSolde           = s.split(",")[4];
                    String fraisGestionSurCotisation = s.split(",")[5];
                    String fraisGestionSurEpargne    = s.split(",")[6];
                    String idcontractant             = s.split(",")[7];
                    String interetGaranti            = s.split(",")[8];
                    String participationOBenef       = s.split(",")[9];
                    String prestationPayees          = s.split(",")[10];
                    String reportDuSolde             = s.split(",")[11];
                    String societe                   = s.split(",")[12];
                    String soldeCrediteur            = s.split(",")[13];
                    String totalCredit               = s.split(",")[14];
                    String totalDebit                = s.split(",")[15];
                    String typeContrat               = s.split(",")[16];


                    if (idcontractant != null && !idcontractant.isEmpty() && !idcontractant.equalsIgnoreCase("null")){
                        compteResultat = compteResultatRepository.findByIdContactant(Long.parseLong(idcontractant.replaceAll("\\s","")));
                        if (compteResultat == null) compteResultat = new CompteResultat();

                        if (societe != null && !societe.isEmpty() && !societe.equalsIgnoreCase("null"))
                            compteResultat.setSociete(societe);

                        if (typeContrat != null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null"))
                            compteResultat.setTypeContrat(typeContrat);

                        if (contractGrpe != null && !contractGrpe.isEmpty() && !contractGrpe.equalsIgnoreCase("null"))
                            compteResultat.setContratGroupe(contractGrpe);

                        if (codeProduct != null && !codeProduct.isEmpty() && !codeProduct.equalsIgnoreCase("null"))
                            compteResultat.setCodeProduit(Integer.parseInt(codeProduct.replaceAll("\\s","")));

                        if (totalDebit != null && !totalDebit.isEmpty() && !totalDebit.equalsIgnoreCase("null"))
                            compteResultat.setTotalDebit(Double.parseDouble(totalDebit.replaceAll("\\s","")));

                        if (prestationPayees != null && !prestationPayees.isEmpty() && !prestationPayees.equalsIgnoreCase("null"))
                            compteResultat.setPrestationPayees(Double.parseDouble(prestationPayees.replaceAll("\\s","")));

                        if (totalCredit != null && !totalCredit.isEmpty() && !totalCredit.equalsIgnoreCase("null"))
                            compteResultat.setTotalCredit(Double.parseDouble(totalCredit.replaceAll("\\s","")));

                        if (fraisGestionSurCotisation != null && !fraisGestionSurCotisation.isEmpty() && !fraisGestionSurCotisation.equalsIgnoreCase("null"))
                            compteResultat.setFraisGestionSurCotisation(Double.parseDouble(fraisGestionSurCotisation.replaceAll("\\s","")));

                        if (fraisGestionSurEpargne !=null && !fraisGestionSurEpargne.isEmpty() && !fraisGestionSurEpargne.equalsIgnoreCase("null"))
                            compteResultat.setFraisGestionSurEpargne(Double.parseDouble(fraisGestionSurEpargne.replaceAll("\\s","")));

                        if (reportDuSolde != null && !reportDuSolde.isEmpty() && !reportDuSolde.equalsIgnoreCase("null"))
                            compteResultat.setReportDuSolde(Double.parseDouble(reportDuSolde.replaceAll("\\s","")));

                        if (dateReportSolde != null && !dateReportSolde.isEmpty() && !dateReportSolde.equalsIgnoreCase("null"))
                            compteResultat.setDateReportSolde(new SimpleDateFormat("dd/MM/yyyy").parse(dateReportSolde));

                        if (cotisationNetteTaxe != null && !cotisationNetteTaxe.isEmpty() && !cotisationNetteTaxe.equalsIgnoreCase("null"))
                            compteResultat.setCotisationNetteTaxe(Double.parseDouble(cotisationNetteTaxe.replaceAll("\\s","")));

                        if (interetGaranti != null && !interetGaranti.isEmpty() && !interetGaranti.equalsIgnoreCase("null"))
                            compteResultat.setInteretGaranti(Double.parseDouble(interetGaranti.replaceAll("\\s","")));

                        if (participationOBenef != null && !participationOBenef.isEmpty() && !participationOBenef.equalsIgnoreCase("null"))
                            compteResultat.setParticipationOBenef(Double.parseDouble(participationOBenef.replaceAll("\\s","")));

                        if (soldeCrediteur != null && !soldeCrediteur.isEmpty() && !soldeCrediteur.equalsIgnoreCase("null"))
                            compteResultat.setSoldeCrediteur(Double.parseDouble(soldeCrediteur.replaceAll("\\s","")));

                        if (dateCompteResultat != null && !dateCompteResultat.isEmpty() && !dateCompteResultat.equalsIgnoreCase("null"))
                            compteResultat.setDateCompteResultat(new SimpleDateFormat("dd/MM/yyyy").parse(dateCompteResultat));

                        compteResultat.setIdContactant(Long.parseLong(idcontractant.replaceAll("\\s","")));

                        compteResultatRepository.save(compteResultat);
                        System.out.println(compteResultat.toString());
                        System.out.println(j+"  Compte resultat traitée");

                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
    }

    public void local(String pathname, CompteResultatRepository compteResultatRepository){
        List<String>  data = LoadCsvFile.getData(pathname);
        CompteResultat compteResultat;
        int i=0;
        if (compteResultatRepository.count() == 0){
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                i++;

                try {
                    if (s.split(",").length>0){
                        compteResultat = new CompteResultat();

                        String id                           = s.split(",")[0];
                        String annee_exercice               = s.split(",")[1];
                        String code_produit                 = s.split(",")[2];
                        String contrat_groupe               = s.split(",")[3];
                        String cotisation_nette_taxe        = s.split(",")[4];
                        String date_compte_resultat         = s.split(",")[5];
                        String date_report_solde            = s.split(",")[6];
                        String frais_gestion_cotisation     = s.split(",")[7];
                        String frais_gestion_epargne        = s.split(",")[8];
                        String id_contactant                = s.split(",")[9];
                        String interet_garanti              = s.split(",")[10];
                        String participationobenef          = s.split(",")[11];
                        String prestation_payees            = s.split(",")[12];
                        String report_du_solde              = s.split(",")[13];
                        String societe                      = s.split(",")[14];
                        String solde_crediteur              = s.split(",")[15];
                        String total_credit                 = s.split(",")[16];
                        String total_debit                  = s.split(",")[17];
                        String type_contrat                 = s.split(",")[18];

                        if (code_produit != null && !code_produit.isEmpty()) compteResultat.setCodeProduit(Integer.parseInt(code_produit.replaceAll("\\s","")));
                        if (contrat_groupe != null && !contrat_groupe.isEmpty()) compteResultat.setContratGroupe(contrat_groupe);
                        if (cotisation_nette_taxe != null && !cotisation_nette_taxe.isEmpty()) compteResultat.setCotisationNetteTaxe(Double.parseDouble(cotisation_nette_taxe.replaceAll("\\s","")));
                        if (date_compte_resultat != null && !date_compte_resultat.isEmpty()) compteResultat.setDateCompteResultat(new SimpleDateFormat("yyyy-MM-dd").parse(date_compte_resultat));
                        if (date_report_solde != null && !date_report_solde.isEmpty()) compteResultat.setDateReportSolde(new SimpleDateFormat("yyyy-MM-dd").parse(date_report_solde));
                        if (frais_gestion_cotisation != null && !frais_gestion_cotisation.isEmpty()) compteResultat.setFraisGestionSurCotisation(Double.parseDouble(frais_gestion_cotisation.replaceAll("\\s","")));
                        if (frais_gestion_epargne !=null && !frais_gestion_epargne.isEmpty()) compteResultat.setFraisGestionSurEpargne(Double.parseDouble(frais_gestion_epargne.replaceAll("\\s","")));
                        if (id_contactant != null && !id_contactant.isEmpty()) compteResultat.setIdContactant(Long.parseLong(id_contactant.replaceAll("\\s","")));
                        if (interet_garanti != null && !interet_garanti.isEmpty()) compteResultat.setInteretGaranti(Double.parseDouble(interet_garanti.replaceAll("\\s","")));
                        if (participationobenef != null && !participationobenef.isEmpty()) compteResultat.setParticipationOBenef(Double.parseDouble(participationobenef.replaceAll("\\s","")));
                        if (prestation_payees != null && !prestation_payees.isEmpty()) compteResultat.setPrestationPayees(Double.parseDouble(prestation_payees.replaceAll("\\s","")));
                        if (report_du_solde != null && !report_du_solde.isEmpty()) compteResultat.setReportDuSolde(Double.parseDouble(report_du_solde.replaceAll("\\s","")));
                        if (societe != null && !societe.isEmpty()) compteResultat.setSociete(societe);
                        if (solde_crediteur != null && !solde_crediteur.isEmpty()) compteResultat.setSoldeCrediteur(Double.parseDouble(solde_crediteur.replaceAll("\\s","")));
                        if (total_credit != null && !total_credit.isEmpty()) compteResultat.setTotalCredit(Double.parseDouble(total_credit.replaceAll("\\s","")));
                        if (total_debit != null && !total_debit.isEmpty()) compteResultat.setTotalDebit(Double.parseDouble(total_debit.replaceAll("\\s","")));
                        if (type_contrat != null && !type_contrat.isEmpty()) compteResultat.setTypeContrat(type_contrat);

                        compteResultatRepository.save(compteResultat);
                        //System.out.println(compteResultat.toString());
                        System.out.println(j+"  Compte resultat traitée");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }
        }
    }
}
