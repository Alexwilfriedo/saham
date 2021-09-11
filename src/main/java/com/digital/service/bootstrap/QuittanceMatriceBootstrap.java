package com.digital.service.bootstrap;

import com.digital.model.Quitance;
import com.digital.model.QuittanceMatrice;
import com.digital.repository.QuittanceMatriceRepository;
import com.digital.util.LoadCsvFile;

import java.util.List;

public class QuittanceMatriceBootstrap {
    public void seed(String filePath, QuittanceMatriceRepository quittanceMatriceRepository) throws Exception {
        if (quittanceMatriceRepository.count() ==0){
            List<String>  data = LoadCsvFile.getData(filePath);
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                //String s = t.concat(";0;0");
                i++;
                try {
                    if (s.split(",").length>0){
                       QuittanceMatrice quitance = new QuittanceMatrice();

                        String aout          = s.split(",")[2];
                        String avril         = s.split(",")[3];
                        String codeContrat   = s.split(",")[4];
                        String codeProduit   = s.split(",")[5];
                        String dec           = s.split(",")[6];
                        String fev           = s.split(",")[7];
                        String janv          = s.split(",")[8];
                        String juillet       = s.split(",")[9];
                        String juin          = s.split(",")[10];
                        String mai           = s.split(",")[11];
                        String mars          = s.split(",")[12];
                        String nov           = s.split(",")[13];
                        String oct           = s.split(",")[14];
                        String sept          = s.split(",")[15];
                        String societe       = s.split(",")[16];
                        String typeContrat   = s.split(",")[17];

                        if (codeContrat != null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("NULL") && !codeContrat.equalsIgnoreCase("null")){
                            if (aout != null && !aout.isEmpty() && !aout.equalsIgnoreCase("null"))
                                quitance.setAout(Double.parseDouble(aout.trim().replaceAll("\\s","")));

                            if (avril != null && !avril.isEmpty() && !avril.equalsIgnoreCase("null"))
                                quitance.setAvril(Double.parseDouble(avril.trim().replaceAll("\\s","")));

                            quitance.setCodeContrat(codeContrat.trim().replaceAll("\\s",""));

                            if (codeProduit !=null && !codeProduit.isEmpty() && !codeProduit.equalsIgnoreCase("null"))
                                quitance.setCodeProduit(Long.parseLong(codeProduit.trim().replaceAll("\\s","")));

                            if (dec != null && !dec.isEmpty()&& !dec.equalsIgnoreCase("null"))
                                quitance.setDeceembre(Double.parseDouble(dec.trim().replaceAll("\\s","").trim()));

                            if (fev != null && !fev.isEmpty() && !fev.equalsIgnoreCase("null"))
                                quitance.setFevrier(Double.parseDouble(fev.trim().replaceAll("\\s","").trim()));

                            if (janv != null && !janv.isEmpty() && !janv.equalsIgnoreCase("null"))
                                quitance.setJanvier(Double.parseDouble(janv.trim().replaceAll("\\s","").trim()));

                            if (juillet != null && !juillet.isEmpty() && !juillet.equalsIgnoreCase("null"))
                                quitance.setJuillet(Double.parseDouble(juillet.trim().replaceAll("\\s","").trim()));

                            if (juin != null && !juin.isEmpty() && !juin.equalsIgnoreCase("null"))
                                quitance.setJuin(Double.parseDouble(juin.trim().replaceAll("\\s","").trim()));

                            if (mai != null && !mai.isEmpty() && !mai.equalsIgnoreCase("null"))
                                quitance.setMai(Double.parseDouble(mai.trim().replaceAll("\\s","").trim()));

                            if (mars != null && !mars.isEmpty() && !mars.equalsIgnoreCase("null"))
                                quitance.setMars(Double.parseDouble(mars.trim().replaceAll("\\s","").trim()));

                            if (nov != null && !nov.isEmpty() && !nov.equalsIgnoreCase("null"))
                                quitance.setNovembre(Double.parseDouble(nov.trim().replaceAll("\\s","").trim()));

                            if (oct != null && !oct.isEmpty() && !oct.equalsIgnoreCase("null"))
                                quitance.setOctobre(Double.parseDouble(oct.trim().replaceAll("\\s","").trim()));

                            if (sept != null && !sept.isEmpty() && !sept.equalsIgnoreCase("null"))
                                quitance.setSeptembre(Double.parseDouble(sept.trim().replaceAll("\\s","").trim()));

                            if (societe !=null && !societe.isEmpty()&& !societe.equalsIgnoreCase("null")) quitance.setSociete(societe.trim());

                            if (typeContrat != null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null")) quitance.setTypeContrat(typeContrat.trim());


                            quittanceMatriceRepository.save(quitance);
                            System.out.println(j+" quittance matrice traitée");
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }

            }
        }
    }

    public void load(String filePath, QuittanceMatriceRepository quittanceMatriceRepository) throws Exception {
        List<String>  data = LoadCsvFile.getData(filePath);
        int i=0;
        for(int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            //String s = t.concat(";0;0");
            i++;
            try {
                if (s.split(",").length>0){
                    QuittanceMatrice quitance; // = new QuittanceMatrice();

                    /**
                    String codeContrat   = s.split(",")[0];
                    String societe       = s.split(",")[1];
                    String codeProduit   = s.split(",")[2];
                    String typeContrat   = s.split(",")[3];
                    String janv          = s.split(",")[4];
                    String fev           = s.split(",")[5];
                    String mars          = s.split(",")[6];
                    String avril         = s.split(",")[7];
                    String mai           = s.split(",")[8];
                    String juin          = s.split(",")[9];
                    String juillet       = s.split(",")[10];
                    String aout          = s.split(",")[11];
                    String sept          = s.split(",")[12];
                    String oct           = s.split(",")[13];
                    String nov           = s.split(",")[14];
                    String dec           = s.split(",")[15];
                    **/


                    String aout        = s.split(",")[0];
                    String avril       = s.split(",")[1];
                    String codeContrat = s.split(",")[2];
                    String codeProduit = s.split(",")[3];
                    String dec         = s.split(",")[4];
                    String fev         = s.split(",")[5];
                    String janv        = s.split(",")[6];
                    String juillet     = s.split(",")[7];
                    String juin        = s.split(",")[8];
                    String mai         = s.split(",")[9];
                    String mars        = s.split(",")[10];
                    String nov         = s.split(",")[11];
                    String oct         = s.split(",")[12];
                    String sept        = s.split(",")[13];
                    String societe     = s.split(",")[14];
                    String typeContrat = s.split(",")[15];

                    if (codeContrat != null && !codeContrat.isEmpty() && !codeContrat.equalsIgnoreCase("NULL") && !codeContrat.equalsIgnoreCase("null")){
                        quitance = quittanceMatriceRepository.findByCodeContrat(codeContrat.trim().replaceAll("\\s",""));

                        if (quitance == null) quitance = new QuittanceMatrice();

                        if (societe !=null && !societe.isEmpty()&& !societe.equalsIgnoreCase("null")) quitance.setSociete(societe.trim());

                        if (codeProduit !=null && !codeProduit.isEmpty() && !codeProduit.equalsIgnoreCase("null"))
                            quitance.setCodeProduit(Long.parseLong(codeProduit.trim().replaceAll("\\s","")));

                        if (typeContrat != null && !typeContrat.isEmpty() && !typeContrat.equalsIgnoreCase("null")) quitance.setTypeContrat(typeContrat.trim());

                        if (janv != null && !janv.isEmpty() && !janv.equalsIgnoreCase("null"))
                            quitance.setJanvier(Double.parseDouble(janv.trim().replaceAll("\\s","").trim()));

                        if (fev != null && !fev.isEmpty() && !fev.equalsIgnoreCase("null"))
                            quitance.setFevrier(Double.parseDouble(fev.trim().replaceAll("\\s","").trim()));

                        if (mars != null && !mars.isEmpty() && !mars.equalsIgnoreCase("null"))
                            quitance.setMars(Double.parseDouble(mars.trim().replaceAll("\\s","").trim()));

                        if (avril != null && !avril.isEmpty() && !avril.equalsIgnoreCase("null"))
                            quitance.setAvril(Double.parseDouble(avril.trim().replaceAll("\\s","")));

                        if (mai != null && !mai.isEmpty() && !mai.equalsIgnoreCase("null"))
                            quitance.setMai(Double.parseDouble(mai.trim().replaceAll("\\s","").trim()));

                        if (juin != null && !juin.isEmpty() && !juin.equalsIgnoreCase("null"))
                            quitance.setJuin(Double.parseDouble(juin.trim().replaceAll("\\s","").trim()));

                        if (juillet != null && !juillet.isEmpty() && !juillet.equalsIgnoreCase("null"))
                            quitance.setJuillet(Double.parseDouble(juillet.trim().replaceAll("\\s","").trim()));

                        if (aout != null && !aout.isEmpty() && !aout.equalsIgnoreCase("null"))
                            quitance.setAout(Double.parseDouble(aout.trim().replaceAll("\\s","")));

                        if (sept != null && !sept.isEmpty() && !sept.equalsIgnoreCase("null"))
                            quitance.setSeptembre(Double.parseDouble(sept.trim().replaceAll("\\s","").trim()));

                        if (oct != null && !oct.isEmpty() && !oct.equalsIgnoreCase("null"))
                            quitance.setOctobre(Double.parseDouble(oct.trim().replaceAll("\\s","").trim()));

                        if (nov != null && !nov.isEmpty() && !nov.equalsIgnoreCase("null"))
                            quitance.setNovembre(Double.parseDouble(nov.trim().replaceAll("\\s","").trim()));

                        if (dec != null && !dec.isEmpty() && !dec.equalsIgnoreCase("null"))
                            quitance.setDeceembre(Double.parseDouble(dec.trim().replaceAll("\\s","").trim()));

                        quitance.setCodeContrat(codeContrat.trim().replaceAll("\\s",""));

                        quittanceMatriceRepository.save(quitance);
                        System.out.println(j+" quittance matrice traitée");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                break;
            }

        }
    }

    public void local(String filePath, QuittanceMatriceRepository quittanceMatriceRepository) throws Exception {
        if (quittanceMatriceRepository.count() ==0){
            List<String>  data = LoadCsvFile.getData(filePath);
            int i=0;
            for(int j = 1; j < data.size(); ++j) {
                String s = data.get(j);
                //String s = t.concat(";0;0");
                i++;
                try {
                    if (s.split(",").length>0){
                        QuittanceMatrice quitance = new QuittanceMatrice();

                        String id           = s.split(",")[0];
                        String aout         = s.split(",")[1];
                        String avril        = s.split(",")[2];
                        String code_contrat = s.split(",")[3];
                        String code_produit = s.split(",")[4];
                        String deceembre    = s.split(",")[5];
                        String fevrier      = s.split(",")[6];
                        String janvier      = s.split(",")[7];
                        String juillet      = s.split(",")[8];
                        String juin         = s.split(",")[9];
                        String mai          = s.split(",")[10];
                        String mars         = s.split(",")[11];
                        String novembre     = s.split(",")[12];
                        String octobre      = s.split(",")[13];
                        String septembre    = s.split(",")[14];
                        String societe      = s.split(",")[15];
                        String type_contrat = s.split(",")[16];

                        if (code_contrat != null && !code_contrat.isEmpty() && !code_contrat.equalsIgnoreCase("NULL") && !code_contrat.equalsIgnoreCase("null")){
                            if (aout != null && !aout.isEmpty() && !aout.equalsIgnoreCase("null"))
                                quitance.setAout(Double.parseDouble(aout.trim().replaceAll("\\s","")));

                            if (avril != null && !avril.isEmpty() && !avril.equalsIgnoreCase("null"))
                                quitance.setAvril(Double.parseDouble(avril.trim().replaceAll("\\s","")));

                            quitance.setCodeContrat(code_contrat.trim().replaceAll("\\s",""));

                            if (code_produit !=null && !code_produit.isEmpty() && !code_produit.equalsIgnoreCase("null"))
                                quitance.setCodeProduit(Long.parseLong(code_produit.trim().replaceAll("\\s","")));

                            if (deceembre != null && !deceembre.isEmpty()&& !deceembre.equalsIgnoreCase("null"))
                                quitance.setDeceembre(Double.parseDouble(deceembre.trim().replaceAll("\\s","").trim()));

                            if (fevrier != null && !fevrier.isEmpty() && !fevrier.equalsIgnoreCase("null"))
                                quitance.setFevrier(Double.parseDouble(fevrier.trim().replaceAll("\\s","").trim()));

                            if (janvier != null && !janvier.isEmpty() && !janvier.equalsIgnoreCase("null"))
                                quitance.setJanvier(Double.parseDouble(janvier.trim().replaceAll("\\s","").trim()));

                            if (juillet != null && !juillet.isEmpty() && !juillet.equalsIgnoreCase("null"))
                                quitance.setJuillet(Double.parseDouble(juillet.trim().replaceAll("\\s","").trim()));

                            if (juin != null && !juin.isEmpty() && !juin.equalsIgnoreCase("null"))
                                quitance.setJuin(Double.parseDouble(juin.trim().replaceAll("\\s","").trim()));

                            if (mai != null && !mai.isEmpty() && !mai.equalsIgnoreCase("null"))
                                quitance.setMai(Double.parseDouble(mai.trim().replaceAll("\\s","").trim()));

                            if (mars != null && !mars.isEmpty() && !mars.equalsIgnoreCase("null"))
                                quitance.setMars(Double.parseDouble(mars.trim().replaceAll("\\s","").trim()));

                            if (novembre != null && !novembre.isEmpty() && !novembre.equalsIgnoreCase("null"))
                                quitance.setNovembre(Double.parseDouble(novembre.trim().replaceAll("\\s","").trim()));

                            if (octobre != null && !octobre.isEmpty() && !octobre.equalsIgnoreCase("null"))
                                quitance.setOctobre(Double.parseDouble(octobre.trim().replaceAll("\\s","").trim()));

                            if (septembre != null && !septembre.isEmpty() && !septembre.equalsIgnoreCase("null"))
                                quitance.setSeptembre(Double.parseDouble(septembre.trim().replaceAll("\\s","").trim()));

                            if (societe !=null && !societe.isEmpty()&& !societe.equalsIgnoreCase("null")) quitance.setSociete(societe.trim());

                            if (type_contrat != null && !type_contrat.isEmpty() && !type_contrat.equalsIgnoreCase("null")) quitance.setTypeContrat(type_contrat.trim());


                            quittanceMatriceRepository.save(quitance);
                            System.out.println(j+" quittance matrice traitée");
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

