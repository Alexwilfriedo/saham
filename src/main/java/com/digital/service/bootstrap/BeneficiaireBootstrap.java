package com.digital.service.bootstrap;

import com.digital.model.Beneficiaire;
import com.digital.model.Contrat;
import com.digital.repository.BeneficiaireRepository;
import com.digital.repository.ContratRepository;
import com.digital.util.LoadCsvFile;

import java.util.List;

public class BeneficiaireBootstrap {

    public  void seed(String filePath, BeneficiaireRepository beneficiaireRepository, ContratRepository contratRepository){

       if (beneficiaireRepository.count() >0){
           List<String> data = LoadCsvFile.getData(filePath);
           Beneficiaire beneficiaire;
           for(int j = 110194; j < data.size(); ++j) {
               beneficiaire = new Beneficiaire();
               String t = data.get(j);
               String s = t.concat(";0;0");
               try {
                   if (s.split(";").length>0){
                       String ID_BENEF = s.split(";")[5];
                       if (ID_BENEF !=null && !ID_BENEF.isEmpty()&& !ID_BENEF.equalsIgnoreCase("NULL")){
                           System.out.println("ID_BENEF :"+ID_BENEF);
                           beneficiaire.setReference(Long.parseLong(ID_BENEF));
                       }
                       String police = s.split(";")[4];
                       if (police !=null && !police.isEmpty()&& !police.equalsIgnoreCase("NULL") ){
                           Contrat contrat = contratRepository.findFirstByPolice(police);
                           if (contrat != null){
                               beneficiaire.setContrat(contrat);
                           }
                       }

                       String benefVie = s.split(";")[3];
                       if ( benefVie !=null && !benefVie.isEmpty()&& !benefVie.equalsIgnoreCase("NULL")){
                           beneficiaire.setBenefVie(benefVie);
                       }
                       String benefDeces = s.split(";")[2];
                       if (benefDeces !=null && !benefDeces.isEmpty() && !benefDeces.equalsIgnoreCase("NULL") ){
                           beneficiaire.setBenefdeces(benefDeces);
                       }
                   }
               } catch (Exception e){
                   e.printStackTrace();
               }
               beneficiaireRepository.save(beneficiaire);
               System.out.println(j+" beneficiaire traitée");
           }

       }

    }

}
