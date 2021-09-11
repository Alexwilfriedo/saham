package com.digital.service.bootstrap;

import com.digital.model.Personne;
import com.digital.model.Profils;
import com.digital.repository.PersonneRepository;
import com.digital.repository.ProfilsRepository;
import com.digital.util.LoadCsvFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PersonneSocieteBootstrap {
    public  void seed(String pathname, PersonneRepository personneRepository, ProfilsRepository profilsRepository) {
        //if (personneRepository.count()==0){
            List<String> data = LoadCsvFile.getData(pathname);
            System.out.println("Mise a jour de la table personne");
            Personne personne;
            for(int j = 1; j < data.size(); j++) {
                personne = new Personne();
                String s = data.get(j);

                //id,Adresse,Date_naissance,Boitepostale,Civilite,
                // display_name,Email,Prenom,Fonction,Nom,marital_statut,
                // Matricule,Cellulaire,Codepostal,Reference,second_adress,
                // Sexe,Ville,Id_profil

                try {
                    if (s.split(",").length>0){
                        String adress           = s.split(",")[2];
                        String boitePostal      = s.split(",")[3];
                        String phone            = s.split(",")[4];
                        String civilite         = s.split(",")[5];
                        String codePostal       = s.split(",")[6];
                        String dateV            = s.split(",")[7];
                        String fullname         = s.split(",")[8];
                        String email            = s.split(",")[9];
                        String fonction         = s.split(",")[10];
                        String ID_PERS          = s.split(",")[11];
                        String id_profil        = s.split(",")[12];
                        String marital_statut   = s.split(",")[13];
                        String matricul         = s.split(",")[14];
                        String lastname         = s.split(",")[15];
                        String firstname        = s.split(",")[16];
                        String reference        = s.split(",")[17];
                        String Other_adress     = s.split(",")[18];
                        String sexe             = s.split(",")[19];
                        String ville            = s.split(",")[20];
                        String[] limitPersonne  = s.split(",");

                        if (ID_PERS != null  && !ID_PERS.isEmpty() && !ID_PERS.equalsIgnoreCase("null") && !ID_PERS.equalsIgnoreCase("NULL")){
                            personne.setReference(Long.parseLong(ID_PERS.replaceAll("\\s","")));

                            if (ville != null && !ville.isEmpty()) personne.setVille(ville);

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
                }
                catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        //}

    }

}
