package com.digital.service.bootstrap;

import com.digital.model.Beneficiaire;
import com.digital.model.Contrat;
import com.digital.model.Personne;
import com.digital.model.StatutContrat;
import com.digital.repository.BeneficiaireRepository;
import com.digital.repository.ContratRepository;
import com.digital.repository.PersonneRepository;
import com.digital.repository.StatutContratRepository;
import com.digital.util.LoadCsvFile;

import java.text.SimpleDateFormat;
import java.util.List;

public class BeneficiaireBootstrap {

    public  void seed(
            String pathName,
            BeneficiaireRepository beneficiaireRepository,
            PersonneRepository personneRepository,
            ContratRepository contratRepository,
            StatutContratRepository statutContratRepository
    ){
        if (beneficiaireRepository.count() == 0L){
            List<String> data = LoadCsvFile.getData(pathName);
            System.out.println("Mise a jour de la table Bénéficiaire");
            Beneficiaire beneficiaire;

            for(int j = 1; j < data.size(); j++) {
                beneficiaire = new Beneficiaire();
                String s = data.get(j);

                try {
                    if (s.split(",").length>0){
                        String reference       = s.split(",")[0];
                        String assure          = s.split(",")[1];
                        String matricule       = s.split(",")[2];
                        String date_naissance  = s.split(",")[3];
                        String etat            = s.split(",")[4];
                        String beneficiaires   = s.split(",")[5];

                        if (reference != null && !reference.isEmpty() && !reference.equalsIgnoreCase("null") && !reference.equalsIgnoreCase("NULL")) {
                            Personne personne = personneRepository.findByReference(Long.parseLong(reference));
                            beneficiaire.setReference(Long.parseLong(reference));

                            if (assure != null && !assure.isEmpty() && !assure.equalsIgnoreCase("null") && !assure.equalsIgnoreCase("NULL")) beneficiaire.setBenefVie(assure);
                            if (beneficiaires != null && !beneficiaires.isEmpty() && !beneficiaires.equalsIgnoreCase("null") && !beneficiaires.equalsIgnoreCase("NULL")) beneficiaire.setBenefdeces(beneficiaires);

                            if (personne != null){
                                if (date_naissance != null && !date_naissance.isEmpty() && !date_naissance.equalsIgnoreCase("null") && !date_naissance.equalsIgnoreCase("NULL")) personne.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(date_naissance));
                                if (matricule != null && !matricule.isEmpty() && !matricule.equalsIgnoreCase("null") && !matricule.equalsIgnoreCase("NULL")) personne.setMatricule(matricule);

                                List<Contrat> contrats = contratRepository.findAllContratOfEmploye(personne.getId());

                                if (contrats.size() > 0){
                                    if (etat != null && !etat.isEmpty() && !etat.equalsIgnoreCase("null") && !etat.equalsIgnoreCase("NULL")){
                                        contrats.forEach(c -> {
                                            StatutContrat statutContrat = statutContratRepository.findByStatutContrat(etat);

                                            if (statutContrat != null){
                                                c.setStatutContrat(statutContrat);
                                                contratRepository.save(c);
                                            }
                                        });
                                    }
                                }

                                personneRepository.save(personne);
                            }
                        }

                        beneficiaireRepository.save(beneficiaire);
                        //System.out.println("Bénéficiaire_" +j+ " : ("+beneficiaire.getReference()+", "+beneficiaire.getBenefVie()+", "+beneficiaire.getBenefdeces()+")");
                        System.out.println(j+" Bénéficiaire mise a jour ");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }

    }

}
