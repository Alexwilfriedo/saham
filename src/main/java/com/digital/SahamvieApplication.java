package com.digital;

import com.digital.config.AuditorAwareImplementation;
import com.digital.facade.AuthenticationFacade;
import com.digital.model.Beneficiaire;
import com.digital.model.Contrat;
import com.digital.model.User;
import com.digital.model.api.HttpSms;
import com.digital.repository.*;
import com.digital.service.SMSHelper;
import com.digital.service.bootstrap.*;
import com.digital.util.Constant;
import com.digital.util.LoadCsvFile;
import com.digital.util.LoadExcelFile;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableScheduling
public class SahamvieApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SahamvieApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SahamvieApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(BrancheRepository brancheRepository,
                                          TypesProduitRepository typesProduitRepository,
                                          BancassuranceRepository bancassuranceRepository,
                                          MiseJourMatriculeRepository miseJourMatriculeRepository,
                                          SituationCompteGlobaleRepository situationCompteGlobaleRepository,
                                          CompteResultatRepository compteResultatRepository,
                                          ProduitRepository produitRepository,
                                          PeriodiciteRepository periodiciteRepository,
                                          VilleRepository villeRepository,
                                          ObjetsRepository objetsRepository,
                                          StatutContratRepository statutContratRepository,
                                          StatutQuitanceRepository statutQuitanceRepository,
                                          TypeProfilsRepository typeProfilsRepository,
                                          ProfilsRepository profilsRepository,
                                          RoleRepository roleRepository,
                                          PersonneRepository personneRepository,
                                          ContratRepository contratRepository,
                                          BeneficiaireRepository beneficiaireRepository,
                                          QuitanceRepository quitanceRepository,
                                          RepriseQuitanceRepository reprisequitanceRepository,
                                          SituationCompteRepository situationCompteRepository,
                                          UserRepository userRepository,
                                          QuittanceMatriceRepository quittanceMatriceRepository,
                                          ContratDesherenceRepository contratDesherenceRepository,
                                          SMSHelper smsHelper) {
        return args -> {

            new UserBootstrap().seek(userRepository);
            //new ContratBootstrap().supprimeDoublons(contratRepository);

            // chargements des parametres generaux
            //new ObjetBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/objet.csv").getPath(), objetsRepository);
            //new PeriodiciteBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/periodicite.csv").getPath(), periodiciteRepository);
            //new TypeProduitBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/typeproduit.csv").getPath(),typesProduitRepository);
            //new BrancheBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/branche.csv").getPath(), brancheRepository);
            //new TypeProfilsBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/typeprofils.csv").getPath(), typeProfilsRepository);
            //new ProfilBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/profil.csv").getPath(), profilsRepository, typeProfilsRepository);
            //new ProduitBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/produit.csv").getPath(), produitRepository, brancheRepository, typesProduitRepository);
            //new StatutQuittanceBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/statutquittance.csv").getPath(), statutQuitanceRepository);
            //new StatutContratBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/statutcontrat.csv").getPath(), statutContratRepository);

            // simple
            //new SituationCompteGlobaleBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/situationcompteglobaleall.csv").getPath(),situationCompteGlobaleRepository);
            //new SituationCompteBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/situationcompteall.csv").getPath(),situationCompteRepository);
            //new QuittanceMatriceBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/quittancematriceall.csv").getPath(),quittanceMatriceRepository);
            //new CompteResultatBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/compte-resultatall.csv").getPath(),compteResultatRepository);

            //lourd
            //new ContratBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/contratall.csv").getPath(), contratRepository,personneRepository, produitRepository,statutContratRepository, periodiciteRepository);
            //new PersonneBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/personneall.csv").getPath(),personneRepository, profilsRepository);
            //new PersonneVueBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/compte-resultatall.csv").getPath(),personneRepository, profilsRepository);
            //new QuittanceBootstrap().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/quittanceall.csv").getPath(), "src/main/resources/Donnees_saham_29_05_2020/test.csv", contratRepository, objetsRepository, statutQuitanceRepository, quitanceRepository);
            //new QuitanceUpdateService().seed(ResourceUtils.getFile("classpath:data/Donnees_23_07_2020/quitance_all.csv").getPath(), quitanceRepository);


            //new PersonneBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/personne-xls.csv").getPath(), personneRepository);
            //new ContratBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/contrat-xls.csv").getPath(), contratRepository);
            //new SituationCompteGlobaleBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/situation-compte-global-xls.csv").getPath(), situationCompteGlobaleRepository);
            //new CompteResultatBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/compte-resultat-xls.csv").getPath(), compteResultatRepository);
            //new SituationCompteBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/situation-compte-xls.csv").getPath(), situationCompteRepository, contratRepository, statutContratRepository);
            //new QuittanceMatriceBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/quittance-matrice-xls.csv").getPath(), quittanceMatriceRepository);
            //new QuittanceBootstrap().erase(ResourceUtils.getFile("classpath:data/ORANGE_CI/quittance-xls.csv").getPath(), quitanceRepository);
            //new QuittanceBootstrap().load(ResourceUtils.getFile("classpath:data/ORANGE_CI/quittance-xls.csv").getPath(), quitanceRepository);
            //new QuittanceBootstrap().updateMontatEmis(ResourceUtils.getFile("classpath:data/quittance.csv").getPath(), quitanceRepository);

            /** Pour chargement local **/
            //new SituationCompteGlobaleBootstrap().local(ResourceUtils.getFile("classpath:data/situation_compte_globale.csv").getPath(), situationCompteGlobaleRepository);
            //new SituationCompteBootstrap().local(ResourceUtils.getFile("classpath:data/situation_compte.csv").getPath(),situationCompteRepository);
            //new QuittanceMatriceBootstrap().local(ResourceUtils.getFile("classpath:data/quittance_matrice.csv").getPath(),quittanceMatriceRepository);
            //new CompteResultatBootstrap().local(ResourceUtils.getFile("classpath:data/ORANGE_CI/compte-resultat-xls.csv").getPath(),compteResultatRepository);
            //new ContratBootstrap().local(ResourceUtils.getFile("classpath:data/contrat.csv").getPath(), contratRepository);
            //new PersonneBootstrap().local(ResourceUtils.getFile("classpath:data/personne.csv").getPath(),personneRepository);
            //new QuittanceBootstrap().local(ResourceUtils.getFile("classpath:data/quitance.csv").getPath(), quitanceRepository);
        };
    }

    public static void sendSmsCoqivoire(SMSHelper smsHelper) {
        for (String s : Constant.coqivoire_contacts) {
            //HttpSms httpSms = new HttpSms.Builder("Coqivoire",String.format("225%s",s),Constant.coqivoire_message,"UTF-8").build();
            HttpSms httpSms = new HttpSms.Builder("Coqivoire", String.format("225%s", s), Constant.coqivoire_message, "UTF-8").build();
            smsHelper.sendTo(httpSms);
        }

    }

    public static String bonFormatDate(String date) {
        if (Long.parseLong(date.substring(6)) < 19) {
            return date.substring(0, 6).concat(String.valueOf(Long.parseLong(date.substring(6)) + 2000));
        } else {
            return date.substring(0, 6).concat(String.valueOf(Long.parseLong(date.substring(6)) + 1900));
        }

    }

    public static void loadBenefFromCsv(String pathname, BeneficiaireRepository beneficiaireRepository, ContratRepository contratRepository) {
        List<String> data = LoadCsvFile.getData(pathname);
        Beneficiaire beneficiaire;
        for (int j = 67160; j < data.size(); ++j) {
            beneficiaire = new Beneficiaire();
            String s = data.get(j);
            try {
                if (s.split(";").length > 0) {
                    String ID_BENEF = s.split(";")[0];
                    if (!ID_BENEF.isEmpty() && ID_BENEF != null) {
                        System.out.println("ID_BENEF :" + s.split(";")[0]);
                        beneficiaire.setReference(Long.parseLong(ID_BENEF));
                    }
                    String police = s.split(";")[1];
                    if (!police.isEmpty() && police != null) {
                        Contrat contrat = contratRepository.findFirstByPolice(police);
                        if (contrat != null) {
                            beneficiaire.setContrat(contrat);
                            //System.out.println("Produit: "+pro.getNomProduit());
                        }
                    }

                    String benefVie = s.split(";")[2];
                    if (!benefVie.isEmpty() && benefVie != null) {
                        beneficiaire.setBenefVie(benefVie);
                    }
                    String benefDeces = s.split(";")[3];
                    if (!benefDeces.isEmpty() && benefDeces != null) {
                        beneficiaire.setBenefdeces(benefDeces);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            beneficiaireRepository.save(beneficiaire);
            System.out.println(j + " beneficiaire traitée");
        }

    }

    public static void loadBeneficiaireFromExcelfile(String pathname,
                                                     BeneficiaireRepository beneficiaireRepository,
                                                     ContratRepository contratRepository
    ) {

        String[][] data = LoadExcelFile.getData(pathname);
        Beneficiaire beneficiaire;
        for (int i = 1; i < data.length; i++) {
            beneficiaire = new Beneficiaire();
            if (!(data[i][0]).isEmpty() && (data[i][0]) != null) {
                // beneficiaire.setReference(Long.parseLong(String.format( "%.0f",Double.parseDouble(data[i][0]))));
                System.out.println("reference :" + data[i][0]);

            }
            if (!(data[i][1]).isEmpty() && (data[i][1]) != null) {
                String police = String.format("%.0f", Double.parseDouble(data[i][1]));
                System.out.println("police" + police);
                Contrat contrat = contratRepository.findFirstByPolice(police);
                if (contrat != null) {
                    beneficiaire.setContrat(contrat);
                    //System.out.println("Produit: "+pro.getNomProduit());
                }
            }

            if (!(data[i][2]).isEmpty() && (data[i][2]) != null) {
                System.out.println("benefvie " + data[i][2]);
                beneficiaire.setBenefVie(data[i][2]);
            }
            if (!(data[i][3]).isEmpty() && (data[i][3]) != null) {
                System.out.println("benefdeces " + data[i][3]);
                beneficiaire.setBenefdeces(data[i][3]);
            }
            beneficiaireRepository.save(beneficiaire);
            System.out.println(i + " beneficiaire enregistré");

        }

    }

    public static void loadCorporateAccount(String pathname, UserRepository userRepository) {
        List<String> data = LoadCsvFile.getData(pathname);
        User user;

        for (int j = 1; j < data.size(); ++j) {
            String s = data.get(j);
            user = new User();
            try {
                if (s.split(";").length > 0) {
                    String ID_PERS = s.split(";")[0];
                    if (!ID_PERS.isEmpty() && ID_PERS != null) {
                        System.out.println("ID_PERS :" + s.split(";")[0]);
                        user.setUsername(String.valueOf(10000000L + Long.parseLong(ID_PERS.replaceAll("\\s", ""))));
                        user.setEnabled(true);
                    }

                    String matricul = s.split(";")[12];
                    if (!matricul.isEmpty() && matricul != null) {
                        String motPasse = matricul.replaceAll("\\s", "");
                        user.setPassword(motPasse.toUpperCase());
                        user.setPasswordConfirmation(motPasse.toUpperCase());
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            userRepository.save(user);

            System.out.println(j + " user corporate traite");
        }
    }

    public static void loadManagerAccount(UserRepository userRepository) {
        User managerUser = new User();
        managerUser.setUsername(String.valueOf(10000000L + Long.parseLong("9000000".replaceAll("\\s", ""))));
        managerUser.setEnabled(true);
        managerUser.setPassword("MANAGER".toUpperCase());
        managerUser.setPasswordConfirmation("MANAGER".toUpperCase());
        userRepository.save(managerUser);
    }

    @Bean
    public AuditorAware<String> auditorAware(AuthenticationFacade authenticationFacade) {
        return new AuditorAwareImplementation(authenticationFacade);
    }

    @Bean
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception, FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:geolite2-country.mmdb");
        return new DatabaseReader.Builder(file).build();
    }
}