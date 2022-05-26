package com.digital.service.bootstrap;

import com.digital.model.Beneficiaire;
import com.digital.model.Contrat;
import com.digital.model.Personne;
import com.digital.model.StatutContrat;
import com.digital.repository.*;
import com.digital.util.LoadCsvFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonneBootstrap {
    public void seed(String pathname, PersonneRepository personneRepository, ProfilsRepository profilsRepository) {
        if (personneRepository.count()==0){
            List<String> data = LoadCsvFile.getData(pathname);
            System.out.println("Mise a jour de la table personne");
            Personne personne;
            for(int j = 1; j < data.size(); j++) {
                personne = new Personne();
                String s = data.get(j);

                try {
                    if (s.split(",").length>0){
                        String adress           = s.split(",")[2];
                        String boitePostal      = s.split(",")[3];
                        String phone            = s.split(",")[4];
                        String civilite         = s.split(",")[5];
                        String codePostal       = s.split(",")[6];
                        String dateV            = s.split(",")[7];
                        String email            = s.split(",")[8];
                        String fonction         = s.split(",")[9];
                        String id_profil        = s.split(",")[10];
                        String matricul         = s.split(",")[11];
                        String lastname         = s.split(",")[12];
                        String firstname        = s.split(",")[13];
                        String ID_PERS          = s.split(",")[14];
                        String sexe             = s.split(",")[15];
                        String[] limitPersonne  = s.split(",");

                        if (limitPersonne.length >= 17){
                            String ville            = s.split(",")[16];
                            if (ville != null && !ville.isEmpty()) personne.setVille(ville);
                        }

                        if (ID_PERS != null  && !ID_PERS.isEmpty() && !ID_PERS.equalsIgnoreCase("null") && !ID_PERS.equalsIgnoreCase("NULL")){
                            personne.setReference(Long.parseLong(ID_PERS.replaceAll("\\s","")));

                            if (adress != null && !adress.isEmpty() && !adress.equalsIgnoreCase("null") && !adress.equalsIgnoreCase("NULL")) personne.setAddress(adress);
                            if (boitePostal != null && !boitePostal.isEmpty() && !boitePostal.equalsIgnoreCase("null") && !boitePostal.equalsIgnoreCase("NULL")) personne.setBoitePostale(boitePostal);
                            if (phone != null && !phone.isEmpty() && !phone.equalsIgnoreCase("null") && !phone.equalsIgnoreCase("NULL")) personne.setPhone(phone);
                            if (civilite != null && !civilite.isEmpty() && !civilite.equalsIgnoreCase("null") && !civilite.equalsIgnoreCase("NULL")) personne.setCivilite(civilite);
                            if (codePostal != null && !codePostal.isEmpty() && !codePostal.equalsIgnoreCase("null") && !codePostal.equalsIgnoreCase("NULL")) personne.setPostalCode(codePostal);
                            if (dateV != null && !dateV.isEmpty() && !dateV.equalsIgnoreCase("null") && !dateV.equalsIgnoreCase("NULL")) personne.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(dateV));
                            if (email != null && !email.isEmpty() && !email.equalsIgnoreCase("null") && !email.equalsIgnoreCase("NULL")) personne.setEmail(email);
                            if (fonction != null && !fonction.isEmpty() && !fonction.equalsIgnoreCase("null") && !fonction.equalsIgnoreCase("NULL")) personne.setFonction(fonction);
                            if (matricul != null && !matricul.isEmpty() && !matricul.equalsIgnoreCase("null") && !matricul.equalsIgnoreCase("NULL")) personne.setMatricule(matricul);
                            if (firstname != null && !firstname.isEmpty() && !firstname.equalsIgnoreCase("null") && !firstname.equalsIgnoreCase("NULL")) personne.setFirstname(firstname);
                            if (lastname != null && !lastname.isEmpty() && !lastname.equalsIgnoreCase("null") && !lastname.equalsIgnoreCase("NULL")) personne.setLastname(lastname);
                            if (sexe != null && !sexe.isEmpty() && !sexe.equalsIgnoreCase("null") && !sexe.equalsIgnoreCase("NULL")) personne.setSexe(sexe);
                            //if (id_profil != null && !id_profil.isEmpty() && !id_profil.equalsIgnoreCase("null") && !id_profil.equalsIgnoreCase("NULL")) personne.setProfile(profilsRepository.getOne(Long.parseLong(id_profil)));


                            personneRepository.save(personne);
                            //System.out.println(personne.toString());
                            System.out.println(j+" personne mise a jour ");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }

    }

    public void load(String pathname, PersonneRepository personneRepository){
        List<String> data = LoadCsvFile.getData(pathname);
        System.out.println("Mise a jour de la table personne pour ORANGE CI");
        Personne personne;
        int i = 0;

        for(int j = 1; j < data.size(); j++){
            String s = data.get(j);
            i++;

            try {
                if (s.split(",").length > 0){
                    /*
                    String codeContrat      = s.split(",")[0];
                    String assure           = s.split(",")[1];
                    String dateNaiss        = s.split(",")[2];
                    String matricul         = s.split(",")[3];
                    */

                    String adresse       = s.split(",")[0];
                    String boitePostale  = s.split(",")[1];
                    String cellulaire    = s.split(",")[2];
                    String civilite      = s.split(",")[3];
                    String codePostal    = s.split(",")[4];
                    String dateNaissance = s.split(",")[5];
                    String email         = s.split(",")[6];
                    String fonction      = s.split(",")[7];
                    String idProfil      = s.split(",")[8];
                    String matricule     = s.split(",")[9];
                    String nom           = s.split(",")[10];
                    String prenom        = s.split(",")[11];
                    String reference     = s.split(",")[12];
                    String sexe          = s.split(",")[13];
                    String ville         = s.split(",")[14];

                    if (reference != null  && !reference.isEmpty() && !reference.equalsIgnoreCase("null") && !reference.equalsIgnoreCase("NULL")){
                        /*
                        Contrat contrat = contratRepository.findByPolice(codeContrat);

                        if (contrat != null) {
                            if (contrat.getAssure() != null) personne = contrat.getAssure();
                            else personne = new Personne();
                        }
                        else personne = new Personne();
                        */
                        personne = personneRepository.findFirstByReference(Long.parseLong(reference));

                        if (personne == null) personne = new Personne();

                        personne.setReference(Long.parseLong(reference));

                        if (adresse != null && !adresse.isEmpty() && !adresse.equalsIgnoreCase("null") && !adresse.equalsIgnoreCase("NULL"))
                            personne.setAddress(adresse);

                        if (boitePostale != null && !boitePostale.isEmpty() && !boitePostale.equalsIgnoreCase("null") && !boitePostale.equalsIgnoreCase("NULL"))
                            personne.setBoitePostale(boitePostale);

                        if (cellulaire != null && !cellulaire.isEmpty() && !cellulaire.equalsIgnoreCase("null") && !cellulaire.equalsIgnoreCase("NULL"))
                            personne.setPhone(cellulaire);

                        if (civilite != null && !civilite.isEmpty() && !civilite.equalsIgnoreCase("null") && !civilite.equalsIgnoreCase("NULL"))
                            personne.setCivilite(civilite);

                        if (codePostal != null && !codePostal.isEmpty() && !codePostal.equalsIgnoreCase("null") && !codePostal.equalsIgnoreCase("NULL"))
                            personne.setPostalCode(codePostal);

                        if (dateNaissance != null && !dateNaissance.isEmpty() && !dateNaissance.equalsIgnoreCase("null") && !dateNaissance.equalsIgnoreCase("NULL")){
                            if ((dateNaissance.split("/")[2]).length()==2) personne.setBirthdate(new SimpleDateFormat("dd/MM/yy").parse(dateNaissance));
                            else personne.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance));
                        }

                        if (email != null && !email.isEmpty() && !email.equalsIgnoreCase("null") && !email.equalsIgnoreCase("NULL"))
                            personne.setEmail(email);

                        if (fonction != null && !fonction.isEmpty() && !fonction.equalsIgnoreCase("null") && !fonction.equalsIgnoreCase("NULL"))
                            personne.setFonction(fonction);

                        if (matricule != null && !matricule.isEmpty() && !matricule.equalsIgnoreCase("null") && !matricule.equalsIgnoreCase("NULL"))
                            personne.setMatricule(matricule);

                        if (prenom != null && !prenom.isEmpty() && !prenom.equalsIgnoreCase("null") && !prenom.equalsIgnoreCase("NULL")) {
                            personne.setFirstname(prenom);
                            personne.setDisplayName(prenom);
                        }

                        if (nom != null && !nom.isEmpty() && !nom.equalsIgnoreCase("null") && !nom.equalsIgnoreCase("NULL")) {
                            personne.setLastname(nom);
                            personne.setDisplayName(nom);
                        }

                        if (sexe != null && !sexe.isEmpty() && !sexe.equalsIgnoreCase("null") && !sexe.equalsIgnoreCase("NULL"))
                            personne.setSexe(sexe);

                        if (ville != null && !ville.isEmpty() && !ville.equalsIgnoreCase("null") && !ville.equalsIgnoreCase("NULL"))
                            personne.setVille(ville);

                        System.out.println("Personne en cours de traitement");
                        personneRepository.save(personne);
                        System.out.println(personne);
                        System.out.println(j+" personne traitée");
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void local(String pathname, PersonneRepository personneRepository) {
        if (personneRepository.count()==0){
            List<String> data = LoadCsvFile.getData(pathname);
            System.out.println("Mise a jour de la table personne");
            Personne personne;
            for(int j = 1; j < data.size(); j++) {
                personne = new Personne();
                String s = data.get(j);

                try {
                    if (s.split(",").length>0){
                        String id                = s.split(",")[0];
                        String adresse           = s.split(",")[1];
                        String date_naissance    = s.split(",")[2];
                        String boite_postale     = s.split(",")[3];
                        String civilite          = s.split(",")[4];
                        String display_name      = s.split(",")[5];
                        String email             = s.split(",")[6];
                        String prenoms           = s.split(",")[7];
                        String fonction          = s.split(",")[8];
                        String nom               = s.split(",")[9];
                        String marital_status    = s.split(",")[10];
                        String matricule         = s.split(",")[11];
                        String cellulaire        = s.split(",")[12];
                        String code_postale      = s.split(",")[13];
                        String reference         = s.split(",")[14];
                        String secondary_address = s.split(",")[15];
                        String sexe              = s.split(",")[16];
                        String ville             = s.split(",")[17];
                        String id_profil         = s.split(",")[18];

                        if (reference != null  && !reference.isEmpty() && !reference.equalsIgnoreCase("null") && !reference.equalsIgnoreCase("NULL")){
                            personne.setReference(Long.parseLong(reference.replaceAll("\\s","")));

                            if (adresse != null && !adresse.isEmpty() && !adresse.equalsIgnoreCase("null") && !adresse.equalsIgnoreCase("NULL")) personne.setAddress(adresse);
                            if (boite_postale != null && !boite_postale.isEmpty() && !boite_postale.equalsIgnoreCase("null") && !boite_postale.equalsIgnoreCase("NULL")) personne.setBoitePostale(boite_postale);
                            if (cellulaire != null && !cellulaire.isEmpty() && !cellulaire.equalsIgnoreCase("null") && !cellulaire.equalsIgnoreCase("NULL")) personne.setPhone(cellulaire);
                            if (civilite != null && !civilite.isEmpty() && !civilite.equalsIgnoreCase("null") && !civilite.equalsIgnoreCase("NULL")) personne.setCivilite(civilite);
                            if (code_postale != null && !code_postale.isEmpty() && !code_postale.equalsIgnoreCase("null") && !code_postale.equalsIgnoreCase("NULL")) personne.setPostalCode(code_postale);
                            if (date_naissance != null && !date_naissance.isEmpty() && !date_naissance.equalsIgnoreCase("null") && !date_naissance.equalsIgnoreCase("NULL")) personne.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(date_naissance));
                            if (email != null && !email.isEmpty() && !email.equalsIgnoreCase("null") && !email.equalsIgnoreCase("NULL")) personne.setEmail(email);
                            if (fonction != null && !fonction.isEmpty() && !fonction.equalsIgnoreCase("null") && !fonction.equalsIgnoreCase("NULL")) personne.setFonction(fonction);
                            if (matricule != null && !matricule.isEmpty() && !matricule.equalsIgnoreCase("null") && !matricule.equalsIgnoreCase("NULL")) personne.setMatricule(matricule);
                            if (prenoms != null && !prenoms.isEmpty() && !prenoms.equalsIgnoreCase("null") && !prenoms.equalsIgnoreCase("NULL")) personne.setFirstname(prenoms);
                            if (nom != null && !nom.isEmpty() && !nom.equalsIgnoreCase("null") && !nom.equalsIgnoreCase("NULL")) personne.setLastname(nom);
                            if (sexe != null && !sexe.isEmpty() && !sexe.equalsIgnoreCase("null") && !sexe.equalsIgnoreCase("NULL")) personne.setSexe(sexe);
                            if (ville != null && !ville.isEmpty() && !ville.equalsIgnoreCase("null") && !ville.equalsIgnoreCase("NULL")) personne.setVille(ville);
                            if (display_name != null && !display_name.isEmpty() && !display_name.equalsIgnoreCase("null") && !display_name.equalsIgnoreCase("NULL")) personne.setDisplayName(display_name);
                            //if (id_profil != null && !id_profil.isEmpty() && !id_profil.equalsIgnoreCase("null") && !id_profil.equalsIgnoreCase("NULL")) personne.setProfile(profilsRepository.getOne(Long.parseLong(id_profil)));


                            personneRepository.save(personne);
                            //System.out.println(personne.toString());
                            System.out.println(j+" personne mise a jour ");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }

    }
}
