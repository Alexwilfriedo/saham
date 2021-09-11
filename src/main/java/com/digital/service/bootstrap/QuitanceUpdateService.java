package com.digital.service.bootstrap;

import com.digital.SahamvieApplication;
import com.digital.model.Quitance;
import com.digital.repository.QuitanceRepository;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QuitanceUpdateService {

    public void seed(String filePath, QuitanceRepository quitanceRepository) {
        System.out.println("chargement des quittances");
        String s = "";
        BufferedReader br = null;
        PrintWriter pw = null;
        int j = 0;

        try {
            URL url = SahamvieApplication.class.getClassLoader().getResource(filePath);
            File resource = new File(URLDecoder.decode(url != null ? url.getFile() : "", "UTF-8"));
            br = new BufferedReader(new FileReader(resource));
            br.readLine(); // lecture de l'entet
            while ((s = br.readLine()) != null) {
                if (s.split(",").length > 0) {
                    j++;

                    String codeObjet = s.split(",")[0];
                    String statut = s.split(",")[1];
                    String numQuit = s.split(",")[2];
                    String codeContrat = s.split(",")[3];
                    String echeance = s.split(",")[4];
                    String dateV = s.split(",")[5];
                    String dateCr = s.split(",")[6];
                    String dateC = s.split(",")[7];
                    String dateEmis = s.split(",")[8];
                    String montantpaye = s.split(",")[9];
                    String montantEmis = s.split(",")[10];
                    Quitance quitance = quitanceRepository.findByNumQuittance(numQuit);

                    if (quitance != null)
                        otherValues(quitance, echeance, dateV, dateCr, dateC, dateEmis, montantpaye, montantEmis);
                    else {
                        quitance = new Quitance();
                        quitance.setObj(Long.parseLong(codeObjet));
                        quitance.setStatut(Long.parseLong(statut));
                        quitance.setNumQuittance(numQuit);
                        quitance.setPolice(codeContrat);
                        otherValues(quitance, echeance, dateV, dateCr, dateC, dateEmis, montantpaye, montantEmis);
                    }

                    quitanceRepository.save(quitance);
                }
            }
        } catch (Exception e) {
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

    private void otherValues(Quitance quitance, String echeance, String dateV, String dateCr, String dateC, String dateEmis, String montantpaye, String montantEmis) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            quitance.setEcheance(formatter.parse(echeance));
            quitance.setDateValeur(formatter.parse(dateV));
            quitance.setDateCreation(formatter.parse(dateCr));
            quitance.setDateComptable(formatter.parse(dateC));
            quitance.setDateEmission(formatter.parse(dateEmis));
            quitance.setMontantPaye(Double.parseDouble(montantpaye));
            quitance.setMontantEmis(Double.parseDouble(montantEmis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
