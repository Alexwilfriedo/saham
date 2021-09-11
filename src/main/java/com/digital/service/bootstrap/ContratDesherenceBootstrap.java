package com.digital.service.bootstrap;

import com.digital.model.DesherenceContrat;
import com.digital.repository.ContratDesherenceRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ContratDesherenceBootstrap {
    public void seed(String filePath, ContratDesherenceRepository contratDesherenceRepository){

        if (contratDesherenceRepository.count() ==0){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String s = "";
            BufferedReader br = null ;
            DesherenceContrat desherenceContrat;
            int j=0;
            try {
                br = new BufferedReader(new FileReader(filePath));
                br.readLine(); // lecture de l'entet
                while ((s = br.readLine()) !=null){
                    j++;
                    desherenceContrat = new DesherenceContrat();
                    if (s.split(",").length>0){

                        String chContrat = s.split(",")[0];
                        if (chContrat !=null  && !chContrat.isEmpty()){
                            desherenceContrat.setChContrat(chContrat);
                        }
                        String nmCodiProduit =  s.split(",")[1];
                        if (nmCodiProduit !=null && !nmCodiProduit.isEmpty()){
                            desherenceContrat.setNmCodiProduit(nmCodiProduit);
                        }

                        String statutContrat = s.split(",")[2];
                        if ( statutContrat !=null && !statutContrat.isEmpty()){
                            desherenceContrat.setStatutContrat(statutContrat);

                        }
                        String dtEffetCrt = s.split(",")[3];
                        if (dtEffetCrt !=null && !dtEffetCrt.isEmpty()){
                            Date date = formatter.parse(dtEffetCrt);
                            desherenceContrat.setDtEffetCrt(date);
                        }

                        String duree = s.split(",")[4];
                        if ( duree !=null && !duree.isEmpty()){
                            desherenceContrat.setDuree(Integer.parseInt(duree));
                        }

                        String dtFinCrt = s.split(",")[5];
                        if (dtFinCrt !=null && !dtFinCrt.isEmpty()){
                            Date date = formatter.parse(dtFinCrt);
                            desherenceContrat.setDtFinCrt(date);
                        }
                        String nomAssure = s.split(",")[6];
                        if (nomAssure !=null && !nomAssure.isEmpty()){
                            desherenceContrat.setNomAssure(nomAssure);
                        }
                        String dtNaisAssure = s.split(",")[7];
                        if (dtNaisAssure !=null && !dtNaisAssure.isEmpty()){
                            Date date = formatter.parse(dtNaisAssure);
                            desherenceContrat.setDtNaisAssure(date);
                        }
                        String dtNaisSouscript = s.split(",")[8];
                        if (dtNaisSouscript !=null && !dtNaisSouscript.isEmpty()){
                            Date date = formatter.parse(dtNaisSouscript);
                            desherenceContrat.setDtNaisSouscript(date);
                        }
                        String nomSousc = s.split(",")[9];
                        if (nomSousc != null && !nomSousc.isEmpty()){
                            desherenceContrat.setNomSousc(nomSousc);
                        }
                        String periodicite = s.split(",")[10];
                        if (periodicite != null && !periodicite.isEmpty()){
                            desherenceContrat.setPeriodicite(periodicite);
                        }
                        String nmMontant = s.split(",")[11];
                        if (nmMontant != null && !nmMontant.isEmpty()){
                            System.out.println(nmMontant);
                            desherenceContrat.setNmMontant(nmMontant);
                        }
                        if (s.split(",").length >12){
                            String cellulaire = s.split(",")[12];
                            if (cellulaire != null && !cellulaire.isEmpty()){
                                System.out.println(cellulaire);
                                desherenceContrat.setCellulaire(cellulaire);
                            }
                        }
                        contratDesherenceRepository.save(desherenceContrat);
                        System.out.println(j+" contrat en desherence");
                    }
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }  finally {
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
}
