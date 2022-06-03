package com.digital.controller;

import com.digital.facade.AuthenticationFacade;
import com.digital.model.*;
import com.digital.model.response.*;
import com.digital.model.tchat.Greeting;
import com.digital.model.tchat.HelloMessage;
import com.digital.model.tchat.Message;
import com.digital.repository.*;
import com.digital.service.JavaMailSenderHandler;
import com.digital.service.MailTemplateHandler;
import com.digital.util.Constant;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.digital.util.Constant.*;

@Controller
public class ClientDefauftController {
    public String usernameId;
    public  static final String[] monthNames = {"JANVIER", "FEVRIER", "MARS", "AVRIL", "MAI", "JUIN", "JUILLET", "AOUT", "SEPTEMBRE", "OCTOBRE", "NOVEMBRE", "DECEMBRE"};

    @Value("${dir.images}") // injecter une propriete de application.properties
    private String imageDir;

    @Autowired private SessionModel session;
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PersonneRepository personneRepository;
    @Autowired private ContratRepository contratRepository;
    @Autowired private QuittanceAnnuelRepository quittanceAnnuelRepository;
    @Autowired private VilleRepository villeRepository;
    @Autowired private CompteResultatRepository compteResultatRepository;
    @Autowired private SituationCompteGlobaleRepository situationCompteGlobaleRepository;
    @Autowired private QuittanceMatriceRepository quittanceMatriceRepository;
    @Autowired private ProduitRepository produitRepository;
    @Autowired private SituationCompteRepository situationCompteRepository;
    @Autowired private QuitanceRepository quitanceRepository;
    @Autowired private DemandeContratRepository demandeContratRepository;
    @Autowired private ObjetsRepository objetsRepository;
    @Autowired private DemandeRepository demandeRepository;
    @Autowired private JavaMailSenderHandler javaMailSenderHandler;
    @Autowired private MailTemplateHandler mailTemplateHandler;
    @Autowired private BeneficiaireRepository beneficiaireRepository;

    @Value("${dir.images}") private String imagesDirectoty;

    private User user = null;
    final AuthenticationFacade authenticationFacade;


    public ClientDefauftController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @ModelAttribute
    void injectUserModel(Model model) {
        System.out.println("Entrée : "+1);
        long debut = DateTime.now().getMillis();
        System.out.println(DateTime.now().getMillis());
        System.out.println("session.getIdentifiant() : "+session.getIdentifiant());

        user = user != null ? user : authenticationFacade.getAuthenticatedUser().orElse(null);

        Optional.ofNullable(user).ifPresent(u -> {
            System.out.println("Entrée : "+2);
            user = u;
            model.addAttribute("fullName",
                    String.format("%s %s", u.getFirstname(), u.getLastname()));
/*

            u.getRoles()
                    .stream()
                    .findFirst()
                    .ifPresent(role -> model.addAttribute("userRole", role.getRole().toUpperCase()));*/
        });
        if (session.getPersonne() == null){
            System.out.println("session.getPersonne() == null");
            session.setPersonne(personneRepository.findByReference(session.getIdentifiant()));
            //System.out.println("personne mise en session :"+session.getPersonne().getDisplayName());
        }
        if (session.getManager() == null){
            System.out.println("session.getManager() == null");
            session.setManager(userRepository.findFirstByUsername(Constant.USERNAME_MANAGER_SAHAM));
        }

        boolean isEnterprise = LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant());
        if(!isEnterprise){
            System.out.println("C'est un individuel");
            if (session.getIsOrNot() == 0){
                List<Contrat> contrats = contratRepository.getContratsOfSouscripteurOrAssure(session.getPersonne().getReference());
                //List<Contrat> contrats = contratRepository.findBySouscripteur(session.getPersonne());

                //contrats.addAll(contratRepository.findByAssure(session.getPersonne()));
                session.setContrats(contrats);
                if (contrats != null && !contrats.isEmpty()) {
                    if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrats.get(0).getId_souscrpteur())){
                        System.out.println(" un individuel corporate");
                        session.setIndivOfCorporate(LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrats.get(0).getId_souscrpteur()));
                        if (contrats.get(0).getId_souscrpteur() == Constant.idOrange){
                            session.setIndivOfOrange(LongStream.of(Constant.idOrange).anyMatch(x -> x == contrats.get(0).getId_souscrpteur()));
                            System.out.println(" un individuel de orange");
                        }
                    }
                    session.setIsOrNot(1);
                }
            }

        }
        else {
            Personne personne = personneRepository.findFirstByReference(session.getIdentifiant());

            if (personne != null){
                List<Contrat> contrats = contratRepository.findBySouscripteur(personne);
                session.setContrats(contrats);

                if (contrats != null && !contrats.isEmpty()) {
                    if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrats.get(0).getId_souscrpteur())){
                        System.out.println(" un individuel corporate");
                        session.setIndivOfCorporate(LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrats.get(0).getId_souscrpteur()));
                        if (contrats.get(0).getId_souscrpteur() == Constant.idOrange){
                            session.setIndivOfOrange(LongStream.of(Constant.idOrange).anyMatch(x -> x == contrats.get(0).getId_souscrpteur()));
                            System.out.println(" un individuel de orange");
                        }
                    }
                    session.setIsOrNot(1);
                }
            }
        }
        long fin = DateTime.now().getMillis();

        model.addAttribute("perso", session.getPersonne());
        model.addAttribute("idUsername",session.getManager() == null ? null :session.getManager().getId());
        model.addAttribute("expediteurUsername",session.getUsername());
        model.addAttribute("destinataireUsername",Constant.USERNAME_MANAGER_SAHAM);
        model.addAttribute("isEnterprise", LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant()));
        model.addAttribute("isEnterpriseOfDemande", LongStream.of(Constant.refCorporateOfAsk).anyMatch(x -> x == session.getIdentifiant()));
        model.addAttribute("indivOfOrange",session.isIndivOfOrange());
        model.addAttribute("isIndivOfCorporate",session.isIndivOfCorporate());

        System.out.println("Class : ClientDefauftController, methode : injectUserModel,  temps :"+(fin-debut));
    }

    @GetMapping("/portailClient/darshboard")
    public String index(Model model) {
        System.out.println("2");
        long debut;
        double cumul_impaye;
        double cumul_total_impaye = 0;
        double cumul_total_paye = 0;
        double cumul_paye;
        double cumul_instance;
        int statut_impaye = 0;
        int statut_paye = 0;
        int statut_instance = 0;
        int[] codes = {1, 6};
        usernameId = session.getUsername();
        Integer total_contratsouscrit = 0;
        List<Quitance> quitanceList = new ArrayList<>();
        List<Darshboard> darshboardsContrat = new ArrayList<>();
        List<Quitance> quitances;
        List<Contrat> contrats ;
        Map<String, Produit> produitMap = new HashMap<>();
        Date dateEch = contratRepository.getdateOfLastEcheance(session.getIdentifiant());
        //model.addAttribute("total_contrat", contratRepository.getContratNomberOfAssure(session.getIdentifiant()));
        int i = 0;
        //cas corporate
        if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant())) {
            total_contratsouscrit = contratRepository.getContratNomberOfSouscripteur(session.getIdentifiant());
            SituationCompteGlobale sitCpteGble = situationCompteGlobaleRepository.getByIdContactant(session.getIdentifiant());
            model.addAttribute("total_contrat", total_contratsouscrit);
            model.addAttribute("sitCpteGble", sitCpteGble);
            model.addAttribute("dateLastQuittance", dateEch);
            model.addAttribute("statut_impaye", statut_impaye);
            model.addAttribute("statut_paye", statut_paye);
            model.addAttribute("statut_instance", statut_instance);
        }
        else {
            Integer total_contratassure = contratRepository.getContratNomberOfAssure(session.getIdentifiant());

            model.addAttribute("total_contrat", (total_contratsouscrit == null ? 0: total_contratsouscrit) + (total_contratassure == null ? 0: total_contratassure));
            Personne personne = session.getPersonne();
                /*contrats = contratRepository.findBySouscripteur(personne);
                contrats.addAll(contratRepository.findByAssure(personne));*/
            List<Contrat> listWithoutDuplicates = new ArrayList<>(new HashSet<>(session.getContrats()));

            for (Contrat contrat : listWithoutDuplicates) {
                i++;
                System.out.println("contrat :"+i+"police : "+contrat.getPolice());
                cumul_impaye = 0;
                cumul_paye = 0;
                cumul_instance = 0;
                if (contrat.getProduit() != null){
                    produitMap.put(contrat.getProduit().getNomProduit(), contrat.getProduit());
                }
                Darshboard darshboard = new Darshboard(contrat.getPolice());
                quitances = quitanceRepository.findByContratAndObjetIn(contrat, objetsRepository.findByCodeIn(codes));
                if (quitances != null && quitances.size() > 0) {
                    System.out.println("nombre de quittance pour le contrat: "+contrat.getPolice()+" est :" + quitances.size());
                    quitanceList.addAll(quitances);
                    System.out.println("ajout a quitanceList");
                    for (Quitance quitance : quitances) {
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("impayé")) {
                            statut_impaye++;
                            cumul_impaye += (quitance.getMontantEmis());
                            cumul_total_impaye += (quitance.getMontantEmis());
                            System.out.println("cumul_impaye: " + cumul_impaye);
                        }
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("payé")){
                            statut_paye++;
                            cumul_paye += quitance.getMontantPaye();
                            cumul_total_paye += quitance.getMontantPaye();
                            System.out.println("cumul_paye :" + cumul_paye);
                        }
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("en instance")){
                            statut_instance++;
                            cumul_instance += quitance.getMontantPaye();
                            System.out.println("cumul_instance :" + cumul_impaye);
                        }
                    }
                    darshboard.setCumulImpaye(cumul_impaye);
                    darshboard.setCumulPaye(cumul_paye);
                    darshboard.setPercent();
                    darshboardsContrat.add(darshboard);
                }
            }
            model.addAttribute("quittances", quitanceList);
            model.addAttribute("contratDashboard", darshboardsContrat);
        }

        model.addAttribute("cumul_impaye", (int)cumul_total_impaye);
        model.addAttribute("cumul_paye", (int) cumul_total_paye);
        model.addAttribute("statut_impaye", statut_impaye);
        model.addAttribute("statut_paye", statut_paye);
        model.addAttribute("statut_instance", statut_instance);
        return "client/index";
    }

    @GetMapping("/portailClient/contrat")
    public String contrat(Model model) {
        double cumul_impaye = 0;
        double cumul_paye = 0;
        Personne personne = session.getPersonne();
        System.out.println("Personne ==> "+((personne != null)?personne.getReference():""));
        if (personne != null) {
            if (!LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant())){
               /* List<Contrat> contrats = contratRepository.findBySouscripteur(personne);
                contrats.addAll(contratRepository.findByAssure(personne));*/
                List<Contrat> listWithoutDuplicates = new ArrayList<>(new HashSet<>(session.getContrats()));

                Map<String, Produit> produitMap = new HashMap<>();
                for (Contrat contrat : listWithoutDuplicates) {
                    produitMap.put(contrat.getProduit().getNomProduit(), contrat.getProduit());
                }
                System.out.println("contrat size " + listWithoutDuplicates.size());
                Contrat contrat = listWithoutDuplicates.get(0);
                List<Quitance> quitances = (List<Quitance>) quitanceRepository.findByContratAndObjetOrderByEcheanceDesc(contrat, objetsRepository.findFirstByNomObjet("Prime"));

                for (Quitance quitance : quitances) {
                    if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("impayé")) {
                        cumul_impaye += (quitance.getMontantEmis());
                    }
                    if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("payé")){
                        cumul_paye += quitance.getMontantPaye();
                    }
                }
                model.addAttribute("products", produitMap.values());
                model.addAttribute("quitanceReleve", quitances);
                model.addAttribute("contrats", listWithoutDuplicates);
                model.addAttribute("contrat", contrat);

            }
            else {
                model.addAttribute("products", contratRepository.getProduitCorporate(session.getIdentifiant()));
                SituationCompteGlobale sitgble = situationCompteGlobaleRepository.getByIdContactant(session.getIdentifiant());
                int anneeAnterieur = Integer.parseInt(new SimpleDateFormat("dd/MM/yyyy").format(sitgble.getDateSituation()).split("/")[2]) - 1;
                System.out.println("Id Contratant ==> "+session.getIdentifiant());
                model.addAttribute("sitgble",sitgble);
                model.addAttribute("anneeAnterieur", anneeAnterieur);
            }

            model.addAttribute("cumul_paye", cumul_paye);
            model.addAttribute("cumul_impaye", cumul_impaye);
        }

        return "client/contrat";
    }

    @GetMapping("/portailClient/contrat/af")
    public String contratAf() {
        return "client/contrat_af";
    }

    @GetMapping("/portailClient/annuel")
    public String situation_annuel() {
        return "client/situation_annuel";
    }

    @GetMapping("/portailClient/releve")
    public String releve_cotisation() {
        return "client/releve_cotisation";
    }

    @GetMapping("/portailClient/prestations")
    public String prestations() {
        return "client/prestations";
    }

    @GetMapping("/portailClient/situation")
    public String situation_compte() {
        return "client/situation_compte";
    }

    @GetMapping("/portailClient/annuel_1")
    public String situation_annuel_1() {
        return "client/sit_annuel_1";
    }

    @GetMapping("/portailClient/annuel_2")
    public String situation_annuel_2() {
        return "client/sit_annuel_2";
    }

    @GetMapping("/portailClient/annuel_3")
    public String situation_annuel_3() {
        return "client/sit_annuel_3";
    }

    @GetMapping("/portailClient/cotisation")
    public String cotisation(Model model) {
        int[] codes = {1, 6};
        List<Quitance> quitanceList = new ArrayList<>();
        // List<Quitance> quitanceListReverse = new ArrayList<>();
        List<Quitance> quitances;
        Personne personne = personneRepository.findByReference(session.getIdentifiant());
        if (personne != null) {
            //List<Contrat> contrats = (List<Contrat>) personne.getContrats();
            List<Contrat> contrats = contratRepository.findBySouscripteur(personne);
            //Collections.reverse(contrats);
            for (Contrat contrat : contrats) {
                System.out.println("police " + contrat.getPolice());
                quitances = quitanceRepository.findByContratAndObjetInOrderByEcheanceDesc(contrat, objetsRepository.findByCodeIn(codes));
                if (quitances != null && quitances.size() > 0) {
                    System.out.println("size: " + quitances.size());
                    /*Collections.reverse(quitances);*/
                    quitanceList.addAll(quitances);
                }
            }
            //Collections.reverse(quitanceList);
            for (Quitance quitance : quitanceList) {
                System.out.println("Id " + quitance.getId() + " echeance " + quitance.getEcheance());
            }

            model.addAttribute("quittances", quitanceList);
        }
        return "client/cotisation";
    }

    @GetMapping("/portailClient/employes")
    public String employe(Model model, @RequestParam(name = "page",defaultValue = "0") int p, @RequestParam(name = "motCle",defaultValue = "") String mc) {

        if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant())) {
            Pageable pageable = new PageRequest(p,10);
            Page<Personne> pagePersonnes = personneRepository.getEmployes(session.getIdentifiant(),"%"+mc+"%",pageable);
            // pour la paginatio
            int pagesCount = pagePersonnes.getTotalPages(); // nombre de page
            int [] pages = new int[pagesCount];
            for (int i = 0; i<pagesCount;i++) pages[i] = i; // un tableau avec les numero de pages
            model.addAttribute("pagePersonnes",pagePersonnes);
            model.addAttribute("motCle",mc);
            model.addAttribute("pages",pages);
            model.addAttribute("pageCourante",p);

        }
        else {
            Personne personne = session.getPersonne();
            //List<Contrat> contrats = contratRepository.findBySouscripteur(personne);
            List<Contrat> contrats = contratRepository.getContratsOfSouscripteurOrAssure(personne.getReference());
            if (contrats != null) System.out.println("Contrat size " + contrats.size());
            List<Personne> personnes = new ArrayList<>();

            if (contrats != null && contrats.size() > 0){
                for (Contrat contrat : contrats) {
                    if (contrat.getAssure() != null && contrat.getAssure().getFirstname() != null) {
                        System.out.println("id :"+contrat.getAssure().getId()+"Assure :"+contrat.getAssure().getLastname()+" Matricule :"+contrat.getAssure().getMatricule());
                        personnes.add(contrat.getAssure());
                    }
                    else personnes.add(personneRepository.findByReference(contrat.getId_assure()));

                }
            }
        }

        // System.out.println("personne size :" + personnes.size());
        //model.addAttribute("personnes", personnes);

        return "client/employes";
    }

    @GetMapping("/portailClient/demandes")
    public String demande(Model model) {
        return "client/demande";
    }

    public EtatAnnuel etat(Contrat contrat) throws ParseException {
        System.out.println("---------------------------ok");
        int year = Calendar.getInstance().get(Calendar.YEAR);
      /*  if (contrat.getSouscripteur().getReference() != 3000008 && contrat.getSouscripteur().getReference() != 3000027 && contrat.getSouscripteur().getReference() != 3000003 && contrat.getSouscripteur().getReference() != 3000018 && contrat.getSouscripteur().getReference() != 3000010){
            System.out.println(" annee en cours " + (year));
            year--;

        }*/
        year --;
        System.out.println(" annee d'evaluation " + (year));
        String dateV = String.format("01/01/%s", String.valueOf(year));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<MonthAndAmount> monthAndAmounts = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            monthAndAmounts.add(new MonthAndAmount(monthNames[i],0));
        }
        EtatAnnuel etatAnnuel;

        Date date = formatter.parse(dateV);
        etatAnnuel = new EtatAnnuel(date, String.valueOf(year));
        /*            List< Quitance> quitancess = (List<Quitance>) quitanceRepository.findByContratAndObjetAndEcheanceAfterOrderByEcheance(contrat,objetsRepository.findFirstByNomObjet("Prime"),date);*/
        List<QuittanceAnnuel>  quittanceAnnuels = quittanceAnnuelRepository.getQuitAnnuel("Prime",contrat.getPolice(),year);

     /* Quitance qJanv = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/01/%s", String.valueOf(year))), formatter.parse(String.format("31/01/%s", String.valueOf(year))));
        Quitance qFrev = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/02/%s", String.valueOf(year))), formatter.parse(String.format("29/02/%s", String.valueOf(year))));
        Quitance qMars = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/03/%s", String.valueOf(year))), formatter.parse(String.format("31/03/%s", String.valueOf(year))));
        Quitance qAvril = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/04/%s", String.valueOf(year))), formatter.parse(String.format("30/04/%s", String.valueOf(year))));
        Quitance qMai = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/05/%s", String.valueOf(year))), formatter.parse(String.format("31/05/%s", String.valueOf(year))));
        Quitance qJuin = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/06/%s", String.valueOf(year))), formatter.parse(String.format("30/06/%s", String.valueOf(year))));
        Quitance qJull = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/07/%s", String.valueOf(year))), formatter.parse(String.format("31/07/%s", String.valueOf(year))));
        Quitance qAout = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/08/%s", String.valueOf(year))), formatter.parse(String.format("31/08/%s", String.valueOf(year))));
        Quitance qSept = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/09/%s", String.valueOf(year))), formatter.parse(String.format("30/09/%s", String.valueOf(year))));
        Quitance qOct = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/10/%s", String.valueOf(year))), formatter.parse(String.format("31/10/%s", String.valueOf(year))));
        Quitance qNov = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/11/%s", String.valueOf(year))), formatter.parse(String.format("30/11/%s", String.valueOf(year))));
        Quitance qDec = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/12/%s", String.valueOf(year))), formatter.parse(String.format("31/12/%s", String.valueOf(year))));
     */
/*
 if (quitancess != null) System.out.println("quitances de l annee encours "+quitancess.size());
 */
        int i=0;
        if (quittanceAnnuels !=null && quittanceAnnuels.size() >0){
   /*  for (QuittanceAnnuel quittanceAnnuel :quittanceAnnuels){
        if (quittanceAnnuel !=null){
            LocalDate localDate = quittanceAnnuel.getEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            monthAndAmounts.add(new MonthAndAmount(monthNames[localDate.getMonthValue()-1],quittanceAnnuel.getMontantPaye()));
        }else {
            monthAndAmounts.add(new MonthAndAmount(monthNames[i],quittanceAnnuel.getMontantPaye()));
        }
        i++;
    }*/

            for (int j = 0; j < quittanceAnnuels.size(); j++) {
                LocalDate localDate = quittanceAnnuels.get(j).getEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                monthAndAmounts.get(j).setMonth(monthNames[localDate.getMonthValue()-1]);
                monthAndAmounts.get(j).setAmount(quittanceAnnuels.get(j).getMontantPaye());

            }



/*       for (int j = 0; j < quittanceAnnuels.size(); j++) {
           if (quittanceAnnuels.get(j) !=null && quittanceAnnuels.size() >0){
               LocalDate localDate = quittanceAnnuels.get(j).getEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
               monthAndAmounts.add(new MonthAndAmount(monthNames[localDate.getMonthValue()-1],quittanceAnnuels.get(j).getMontantPaye()));
           }else {
               monthAndAmounts.add(new MonthAndAmount(monthNames[j],quittanceAnnuels.get(j).getMontantPaye()));
           }

       }*/
        }


 /*     if (qJanv != null) {
            monthAndAmounts.add(new MonthAndAmount("JANVIER", qJanv.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("JANVIER", 0));
        }
        if (qFrev != null) {
            monthAndAmounts.add(new MonthAndAmount("FEVRIER", qFrev.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("FEVRIER", 0));
        }
        if (qMars != null) {
            monthAndAmounts.add(new MonthAndAmount("MARS", qMars.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("MARS", 0));
        }
        if (qAvril != null) {
            monthAndAmounts.add(new MonthAndAmount("AVRIL", qAvril.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("AVRIL", 0));
        }
        if (qMai != null) {
            monthAndAmounts.add(new MonthAndAmount("MAI", qMai.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("MAI", 0));
        }
        if (qJuin != null) {
            monthAndAmounts.add(new MonthAndAmount("JUIN", qJuin.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("JUIN", 0));
        }
        if (qJull != null) {
            monthAndAmounts.add(new MonthAndAmount("JUILLET", qJull.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("JUILLET", 0));
        }
        if (qAout != null) {
            monthAndAmounts.add(new MonthAndAmount("AOUT", qAout.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("AOUT", 0));
        }
        if (qSept != null) {
            monthAndAmounts.add(new MonthAndAmount("SEPTEMBRE", qSept.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("SEPTEMBRE", 0));
        }
        if (qOct != null) {
            monthAndAmounts.add(new MonthAndAmount("OCTOBRE", qOct.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("OCTOBRE", 0));
        }
        if (qNov != null) {
            monthAndAmounts.add(new MonthAndAmount("NOVEMBRE", qNov.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("NOVEMBRE", 0));
        }
        if (qDec != null) {
            monthAndAmounts.add(new MonthAndAmount("DECEMBRE", qDec.getMontantPaye()));
        } else {
            monthAndAmounts.add(new MonthAndAmount("DECEMBRE", 0));
        }
        */
        if(!LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrat.getSouscripteur().getReference())){
            //VALEUR DE RACHAT
            Quitance qvra = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(270), formatter.parse(String.format("01/01/%s", String.valueOf(year))));
            if (qvra != null) {
                etatAnnuel.setValeurDeRachat(qvra.getMontantPaye());
                etatAnnuel.setValeurDeRachatYear(qvra.getDateValeur());
            }
            //VALEUR DE REDUCTION
            Quitance qvre = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(210), formatter.parse(String.format("01/01/%s", String.valueOf(year))));
            if (qvre != null) {
                etatAnnuel.setValeurReduction(qvre.getMontantPaye());
                etatAnnuel.setValeurReductionYear(qvre.getDateValeur());
            }
            //PRIME DE FIDELITÉ
            Quitance prime = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(260), formatter.parse(String.format("01/01/%s", String.valueOf(year))));
            if (prime != null) {
                etatAnnuel.setPrimeFidelite(prime.getMontantPaye());
            }

        }
        // RACHAT PARTIEL
        Quitance rp = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(2), formatter.parse(String.format("01/01/%s", String.valueOf(year))));
        if (rp != null) {
            etatAnnuel.setRachatPartiel(rp.getMontantPaye());
            etatAnnuel.setRachatPartielYear(rp.getDateValeur());
        }
        etatAnnuel.setMonthAndAmounts(monthAndAmounts);

        return etatAnnuel;
    }

    @RequestMapping(value = "/cotisation-employe", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String getCotisation(Long id,String lastname, Model model) {
        long debut ;
        long debu = DateTime.now().getMillis();
        System.out.println("id " + id);
        System.out.println("lastname " + lastname);
        double cumul_impaye = 0;
        double cumul_paye = 0;
        // Personne personne = personneRepository.findOne(id);
        debut = DateTime.now().getMillis();

        Contrat contrat = contratRepository.findAllContratOfEmploye(id).get(0);

        SituationCompte situationCompte = situationCompteRepository.findFirstByCodeContrat(contrat.getPolice());

        List<Quitance> quitances = (List<Quitance>) quitanceRepository.getQuittancesOfEmploye(contrat.getId());

        if (quitances != null && quitances.size()>0){
            model.addAttribute("dateOfLastQuittance",quitances.get(0).getEcheance());

            for (Quitance quitance : quitances) {
                if (quitance != null && quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null && quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase(Constant.IMPAYE)) {
                    cumul_impaye += (quitance.getMontantEmis());
                }
                if (quitance != null && quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null && quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase(Constant.PAYE)) {
                    cumul_paye += quitance.getMontantPaye();
                }
            }
        }

        model.addAttribute("contrat", contrat);
        model.addAttribute("quitanceReleve", quitances);
        model.addAttribute("lastname", lastname);
        model.addAttribute("cumul_paye", cumul_paye);
        model.addAttribute("cumul_impaye", cumul_impaye);
        model.addAttribute("situationCompte", situationCompte);

        if (situationCompte !=null && situationCompte.getTauxRevaloEpargne() != null){
            //Float taux = Float.parseFloat(situationCompte.getTauxRevaloEpargne().replace(",","."));
            Float taux = Float.parseFloat(situationCompte.getTauxRevaloEpargne().split("%")[0].replace(",","."));
            model.addAttribute("taux", String.format("%.2f", taux*100));
        }
        System.out.println( "total :"+(DateTime.now().getMillis()-debu));
        return "client/vue-releve";
    }

    @RequestMapping(value = "/etat-employe", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String getEtat(Long id,String lastname, Model model) {
        System.out.println("id " + id);
        double cumul_impaye = 0;
        double cumul_paye = 0;

        Contrat contrat = contratRepository.findAllContratOfEmploye(id).get(0);
        System.out.println("Police ==> "+contrat.getPolice());
        Beneficiaire beneficiaire = beneficiaireRepository.findByContrat(contrat.getId()).orElse(null);
        QuittanceMatrice quittanceMatrice = quittanceMatriceRepository.findByCodeContrat(contrat.getPolice());
        SituationCompte situationCompte = situationCompteRepository.findByCodeContrat(contrat.getPolice());
        model.addAttribute("contrat", contrat);
        model.addAttribute("beneficiaire", beneficiaire);

        if (situationCompte != null && situationCompte.getTauxRevaloEpargne() != null){
            model.addAttribute("situationCompte", situationCompte);
            //Float taux = Float.parseFloat(situationCompte.getTauxRevaloEpargne().replace(",","."));
            Float taux = Float.parseFloat(situationCompte.getTauxRevaloEpargne().split("%")[0].replace(",","."));
            //model.addAttribute("taux", String.format("%.2f", taux*100));
            model.addAttribute("taux", String.format("%.2f", taux));
        }
        else {
            model.addAttribute("situationCompte", new SituationCompte(contrat.getPolice(),"Retraite complémentaire",contrat.getPeriodicite().getPeriodicite(),contrat.getSouscripteur().getLastname(),
                    contrat.getAssure().getLastname(),new Date(),0,0,
                    0,0,0,0,0,"0"));
        }
        if (quittanceMatrice != null) {
            model.addAttribute("quittanceMatrice", quittanceMatrice);
            model.addAttribute("totalVersement",
                    quittanceMatrice.getJanvier()
                            + quittanceMatrice.getFevrier()
                            + quittanceMatrice.getMars()
                            + quittanceMatrice.getAvril()
                            + quittanceMatrice.getMai()
                            + quittanceMatrice.getJuin()
                            + quittanceMatrice.getJuillet()
                            + quittanceMatrice.getAout()
                            + quittanceMatrice.getSeptembre()
                            + quittanceMatrice.getOctobre()
                            + quittanceMatrice.getNovembre()
                            + quittanceMatrice.getDeceembre());
        }
        model.addAttribute("cumul_paye", cumul_paye);
        model.addAttribute("cumul_impaye", cumul_impaye);
        model.addAttribute("lastname", lastname);
        return "client/vue-etat";
    }

    @RequestMapping(value = "/ajax-07", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String ajax07(int num, Long id, Model model) {

        boolean isParticulirOfCorporate;
        SituationCompte situationCompte;
        List<Quitance> quitances = new ArrayList<>();
        double cumul_impaye = 0;
        double cumul_paye = 0;
        Contrat contrat = contratRepository.findOne(id);
        String[] tableau = {"avance", "Rachat partiel"};
        if (!LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant())){
            quitances = (List<Quitance>) quitanceRepository.findByContratAndObjetOrderByEcheanceDesc(contrat, objetsRepository.findFirstByNomObjet("Prime"));
            for (Quitance quitance : quitances) {
                if (quitance != null && quitance.getStatutQuitance() !=null && quitance.getStatutQuitance().getStatutQuit() !=null && quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("impayé")) {
                    cumul_impaye += (quitance.getMontantEmis());
                }
                if (quitance != null && quitance.getStatutQuitance() !=null && quitance.getStatutQuitance().getStatutQuit() !=null && quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("payé")) {
                    cumul_paye += quitance.getMontantPaye();
                }
            }
        }
        System.out.println("Contrat " + contrat.getPolice());
        model.addAttribute("contrat", contrat);
        switch (num) {
            case 1:
                System.out.println("case 1 :client/vue-releve ");
                model.addAttribute("quitanceReleve", quitances);
                model.addAttribute("cumul_paye", cumul_paye);
                model.addAttribute("cumul_impaye", cumul_impaye);
                return "client/vue-releve";
            case 2: //situation de compte annuelle
                System.out.println("case 2 : client/vue-compte");
                isParticulirOfCorporate = LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrat.getSouscripteur().getReference());
                if (!isParticulirOfCorporate){
                    Quitance quitanceValeurRachat = quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(270));
                    Quitance quitancePm = quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(3));
                    Quitance quitanceValeurReduction = quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(210));
                    model.addAttribute("quitanceValeurRachat", quitanceValeurRachat);
                    model.addAttribute("quitanceValeurReduction", quitanceValeurReduction);
                    model.addAttribute("quitancePm", quitancePm);
                }
                situationCompte = situationCompteRepository.findByCodeContrat(contrat.getPolice());
                model.addAttribute("situationCompte", situationCompte);
                model.addAttribute("isParticulirOfCorporate", isParticulirOfCorporate);

                return "client/vue-compte";
            case 3:
                System.out.println("case 3 : client/vue-prest");
                quitances = (List<Quitance>) quitanceRepository.findByContratAndObjetIn(
                        contrat, objetsRepository.findByNomObjetIn(Arrays.asList(tableau)));
                model.addAttribute("quitancePresta", quitances);
                return "client/vue-prest";
            case 4:
                System.out.println("case 4 : client/vue-annuel");
                model.addAttribute("cumul_paye", cumul_paye);
                System.out.println("taille quitance " + quitances.size());
                try {
                    model.addAttribute("etat", etat(contrat));
                    System.out.println("souscripteur etat =>"+contrat.getSouscripteur().getReference());
                    isParticulirOfCorporate = LongStream.of(Constant.refCorporate).anyMatch(x -> x == contrat.getSouscripteur().getReference());

                    situationCompte = situationCompteRepository.findByCodeContrat(contrat.getPolice());
                    if (situationCompte !=null && situationCompte.getTauxRevaloEpargne() !=null){
                        float taux = Float.parseFloat(situationCompte.getTauxRevaloEpargne().replace(",","."));
                        model.addAttribute("tauxR", String.format("%.2f", taux*100));
                    }
                    model.addAttribute("situationCompte", situationCompte);
                    model.addAttribute("isParticulirOfCorporate", isParticulirOfCorporate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return "client/vue-annuel";
            case 5:
                System.out.println("case 5 : client/vue-compte-resulta");
                CompteResultat compteResultat = compteResultatRepository.getByIdContactant(session.getIdentifiant());
                model.addAttribute("compteResultat",compteResultat);
                return "client/vue-compte-resultat";
            case 6:
                System.out.println("case 6 : client/vue-situation-compte-global");
                SituationCompteGlobale sitgble = situationCompteGlobaleRepository.getByIdContactant(session.getIdentifiant());
                int anneeAnterieur = Integer.parseInt(new SimpleDateFormat("dd/MM/yyyy").format(sitgble.getDateSituation()).split("/")[2]) - 1;
                model.addAttribute("sitgble",sitgble);
                model.addAttribute("anneeAnterieur", anneeAnterieur);
                return "client/vue-situation-compte-global";
            default:
                System.out.println("default : ");
                return "redirect:/portailClient/contrat";
        }
    }

    @RequestMapping(value = "/demande-init", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public String demandeInit(int num, Model model) {
        // num : numéro de page
        System.out.println("num " + num);
        Personne personne = session.getPersonne();

        if (personne != null) model.addAttribute("perso", personne);

        switch (num) {
            case 1:
                return "client/demande-generale";
            case 2:
                return "client/demande-contrat";
            case 3:
                return "client/demande-rachat_partiel";
            case 4:
                return "client/demande-rachat_partiel_remb";
            case 5:
                return "client/demande-avance_police";
            case 6:
                return "client/demande-rachat_total";
            case 7:
                return "client/demande-terme_contrat";
            case 8:
                model.addAttribute("sinistreObseqPlus",new SintreObseqPlus());
                return "client/sinistre-obseqplus";
            case 9:
                model.addAttribute("sinistreRenteEduc",new SintreRenteEduc());
                return "client/sinistre-rente-education";
            case 10:
                model.addAttribute("sintreTempDeces",new SintreTempDeces());
                return "client/sinistre-temporaire-deces";
            default:
                return "redirect:/portailClient/demandes";
        }
    }

    @RequestMapping(value = "/demande/sinistreTempDeces", method = RequestMethod.POST)
    public String askSinistreTempDeces(Model model, @ModelAttribute SintreTempDeces sintreTempDeces,String police){
        try {
            Context context = new Context();
            context.setVariable("std",sintreTempDeces);
            context.setVariable("police",police);
            Future<String> content = mailTemplateHandler.build("mailTemplates/temporaireDeces",context);

            javaMailSenderHandler.send(Constant.mail_dev,"Declaration de sinistre de temporaire deces ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"Declaration de sinistre de temporaire deces ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("sintreTempDeces"+sintreTempDeces.toString());
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        Demande demande =  new Demande(police,"Temporaire Deces","Initiée",personneRepository.findByReference(session.getIdentifiant()).getLastname(),"Declaration de sinistre",session.getUsername());
        demandeRepository.save(demande);
        return "/portailClient/demande";
    }

    @RequestMapping(value = "/demande/sinistreObseqPlus", method = RequestMethod.POST)
    public String askSinistreObseqPluss(Model model, @ModelAttribute SintreObseqPlus sinistreObseqPlus, String police){
        try {
            Context context = new Context();
            context.setVariable("std",sinistreObseqPlus);
            context.setVariable("police",police);
            Future<String> content = mailTemplateHandler.build("mailTemplates/obseqPlus",context);
            javaMailSenderHandler.send(Constant.mail_dev,"Declaration de sinistre ObseqPlus ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"Declaration de sinistre ObseqPlus ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        Demande demande =  new Demande(police,"ObseqPlus","Initiée",personneRepository.findByReference(session.getIdentifiant()).getLastname(),"Declaration de sinistre",session.getUsername());
        demandeRepository.save(demande);
        return "/portailClient/demande";
    }

    @RequestMapping(value = "/demande/sinistreRenteEduc", method = RequestMethod.POST)
    public String askSinistreRenteEduc(Model model, @ModelAttribute SintreRenteEduc sinistreRenteEduc,String police){
        try {
            Context context = new Context();
            context.setVariable("std",sinistreRenteEduc);
            context.setVariable("police",police);
            Future<String> content = mailTemplateHandler.build("mailTemplates/renteEducation",context);
            javaMailSenderHandler.send(Constant.mail_dev,"Declaration de sinistre Rente education ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"Declaration de sinistre Rente education ",content.get().toString(),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        Demande demande =  new Demande(police,"Rente education","Initiée",personneRepository.findByReference(session.getIdentifiant()).getLastname(),"Declaration de sinistre",session.getUsername());
        demandeRepository.save(demande);
        return "/portailClient/demande";
    }

    @RequestMapping(value = "/rachatTotal/corporate",method = RequestMethod.POST)
    public String demandeRachatTotalCorporate(Model model, @RequestParam(name = "pictures") MultipartFile[] files) throws IOException{
        System.out.println("tentative d'envoi de mail avec piece jointe");
        try {
            Context context = new Context();
            context.setVariable("askeds",session.getAskeds());
            Future<String> content = mailTemplateHandler.build("mailTemplates/rachatTotalCorporate",context);
            List<FileSystemResource> fileSystemResources = new ArrayList<>();
            if ( files.length>0) {
                for (MultipartFile fi :files){
                    fi.transferTo(new File(imageDir + fi.getOriginalFilename())); // 2e solution ,on enregistre avec le id de l'etudiant pour s'assurer de l'unicité de la photo et aussi pour recuper la photo connaissant l'id de l'etudiant: meilleur
                    File f = new File(imageDir + fi.getOriginalFilename());
                    fileSystemResources.add(new FileSystemResource(f.getAbsolutePath()));
                }
                javaMailSenderHandler.sendWithAtta(mail_dev,"demande de rachat total ",content.get().toString(),true,fileSystemResources);
                javaMailSenderHandler.sendWithAtta(mail_prod,"demande de rachat total ",content.get().toString(),true,fileSystemResources);

            }else {
                javaMailSenderHandler.send(mail_dev,"demande de rachat total ",content.get().toString(),true);
                javaMailSenderHandler.send(mail_prod,"demande de rachat total ",content.get().toString(),true);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        session.getAskeds().clear();
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        return "client/demande";
    }

    @RequestMapping(value = "/termeContrat/corporate",method = RequestMethod.POST)
    public String demandeTermeContratCorporate(Model model){
        try {
            Context context = new Context();
            context.setVariable("termes",session.getTermesContrat());

            Future<String> content = mailTemplateHandler.build("mailTemplates/termeContratCorporate",context);
            javaMailSenderHandler.send(mail_prod,"demande de terme de contrat ",content.get().toString(),true);
            javaMailSenderHandler.send(mail_dev,"demande de terme de contrat ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        session.getAskeds().clear();
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        return "client/demande";
    }

    /**
    @RequestMapping(value = "/demande/generale",method = RequestMethod.POST)
    public String sendDemandeGeneral(Long id,String ctrat, String nom, String prenom, String profesion, String adress, String phone, Model model){

        try {
            Context context = new Context();
            Personne personne = personneRepository.findOne(id);
            context.setVariable("phone",phone);
            context.setVariable("ctrat",ctrat);
            context.setVariable("adress",adress);
            context.setVariable("profesion",profesion);
            context.setVariable("prenom",prenom);
            context.setVariable("nom",nom);
            context.setVariable("personne",personne);

            Future<String> content = mailTemplateHandler.build("mailTemplates/demandeGenerale",context);
            //javaMailSenderHandler.send(Constant.mail_prod,"demande generale ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_dev,"demande generale ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande generale ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");
        Demande demande =  new Demande(ctrat,"demande generale","Initiée",personneRepository.findByReference(session.getIdentifiant()).getLastname(),"Demande de modification générale",session.getUsername());
        demandeRepository.save(demande);
        return "client/demande";
    }
    **/

    @RequestMapping(value = "/demande/generale",method = RequestMethod.POST)
    public String sendDemandeGeneral(String ctrat, String nom, String prenom, String profesion, String adress, String phone, Model model){
        Contrat contrat = contratRepository.getOne(Long.parseLong(ctrat));

        if (contrat != null){
            Personne personne = personneRepository.findFirstByReference(contrat.getId_assure());

            if (personne != null){
                try {
                    Context context = new Context();


                    context.setVariable("oldPhone",personne.getPhone());
                    context.setVariable("newPhone",phone);
                    context.setVariable("ctrat",contrat.getPolice());
                    context.setVariable("oldAdress",personne.getAddress());
                    context.setVariable("newAdress",adress);
                    context.setVariable("oldProfesion",personne.getFonction());
                    context.setVariable("newProfesion",profesion);
                    context.setVariable("oldPrenom",personne.getFirstname());
                    context.setVariable("newPrenom",prenom);
                    context.setVariable("oldNom",personne.getLastname());
                    context.setVariable("newNom",nom);
                    context.setVariable("personne",personne.getReference());

                    Future<String> content = mailTemplateHandler.build("mailTemplates/demandeGenerale",context);
                    //javaMailSenderHandler.send(Constant.mail_prod,"demande generale ",content.get().toString(),true);
                    javaMailSenderHandler.send(Constant.mail_test,"demande generale ",content.get().toString(),true);
                    //javaMailSenderHandler.send(Constant.mail_prod,"demande generale ",content.get().toString(),true);
                } catch (MessagingException | InterruptedException | ExecutionException e) {
                    //e.printStackTrace();
                    model.addAttribute("success",false);
                    model.addAttribute("title","Info");
                    model.addAttribute("message","Erreur système dans l\'envoi du mail");
                }

                model.addAttribute("success",true);
                model.addAttribute("title","Info");
                model.addAttribute("message","Demande envoyée avec succes");
                Demande demande =  new Demande(ctrat,"demande generale","Initiée",personneRepository.findByReference(session.getIdentifiant()).getLastname(),"Demande de modification générale",session.getUsername());
                demandeRepository.save(demande);
            }
            else {
                model.addAttribute("success",false);
                model.addAttribute("title","Info");
                model.addAttribute("message","Cet assuré n\'exite pas dans la base");
            }
        }
        else {
            model.addAttribute("success",false);
            model.addAttribute("title","Info");
            model.addAttribute("message","Ce contrat n\'existe pas");
        }

        return "client/demande";
    }

    @RequestMapping(value = "/demande/info",method = RequestMethod.POST)
    public String sendDemandeInfo(Long id,String newPrime,
                                  String newDateEffet, String newPeriodicite,
                                  String newRib, String newSusProv,
                                  String newDateSusProv,String newBenef,
                                  Model model, @RequestParam(name = "newFileRib") MultipartFile file) {
        System.out.println("file "+file);
        System.out.println("newDateSusProv "+newDateSusProv);
        System.out.println("newBenef "+newBenef);
        System.out.println("newSusProv "+newSusProv);
        System.out.println("newRib "+newRib);
        System.out.println("newPeriodicite "+newPeriodicite);
        System.out.println("newDateEffet "+newDateEffet);
        System.out.println("newPrime "+newPrime);
        System.out.println("id "+id);

        try {
            Context context = new Context();
            if (id != null && id>0){
                Personne personne = personneRepository.findOne(id);
                context.setVariable("personne",personne);
            }
            context.setVariable("newDateSusProv",newDateSusProv);
            context.setVariable("newPrime",newPrime);
            context.setVariable("newSusProv",newSusProv);
            context.setVariable("newRib",newRib);
            context.setVariable("newPeriodicite",newPeriodicite);
            context.setVariable("newDateEffet",newDateEffet);
            context.setVariable("newBenef",newBenef);

            // FileSystemResource file = new FileSystemResource(new File(newFileRib));


            Future<String> content = mailTemplateHandler.build("mailTemplates/infoContrat",context);
            //javaMailSenderHandler.sendWithAtta(Constant.mail_prod,"demande generale ",content.get().toString(),true, file);
            if (!file.isEmpty()){
                if (file.getSize() <= 10485760L){
                    System.out.println("home "+imagesDirectoty);
                    file.transferTo(new File(imagesDirectoty+file.getOriginalFilename()));
                    FileSystemResource fichier = new FileSystemResource(imagesDirectoty+file.getOriginalFilename());
                    javaMailSenderHandler.sendWithAtta(mail_test,"demande generale ",content.get().toString(),true, fichier);
                    //javaMailSenderHandler.sendWithAtta(mail_prod,"demande generale ",content.get().toString(),true, fichier);
                    //javaMailSenderHandler.sendWithAtta(mail_dev,"demande generale ",content.get().toString(),true, fichier);
                }
                else {
                    model.addAttribute("success",false);
                    model.addAttribute("title","Info");
                    model.addAttribute("message","Taille du fichier trop grande");
                }

            }else {
                javaMailSenderHandler.send(mail_test,"demande generale ",content.get().toString(),true);
                //javaMailSenderHandler.send(Constant.mail_dev,"demande generale ",content.get().toString(),true);
                //javaMailSenderHandler.send(Constant.mail_prod,"demande generale ",content.get().toString(),true);

            }
        } catch (MessagingException | ExecutionException | InterruptedException | IOException e) {
            //e.printStackTrace();
            model.addAttribute("success",false);
            model.addAttribute("title","Info");
            model.addAttribute("message","Erreur syystème dans l\'envoie de la demande");
        }

        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","Demande envoyée avec succes");

        return "client/demande";
    }

    @RequestMapping(value = "/demande/rachatPartiel", method = RequestMethod.POST)
    public String demandeRachat(Model model, String police, String ville, String montant) throws MessagingException {
        System.out.println(" police:" + police);
        System.out.println(" ville:" + ville);
        System.out.println("montant :" + montant);
        Demande demande = new Demande();
        demande.setMotif("Rachat partiel");

        demande.setPersonne(personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demande.setStatut("Initiée");
        demande.setUsername(session.getUsername());
        demande.setContenu("demande de rachat partiel d'un montant de :" + montant + " du contrat :" + police + "dans la ville :" + ville + " effectuée par :" + personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demandeRepository.save(demande);
        try {
            Context context = new Context();
            context.setVariable("police",police);
            context.setVariable("ville",ville);
            context.setVariable("montant",montant);
            context.setVariable("perso",personneRepository.findByReference(session.getIdentifiant()));
            Future<String> content = mailTemplateHandler.build("mailTemplates/rachatPartiel",context);
            javaMailSenderHandler.send(Constant.mail_dev,"demande de rachat partiel ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande de rachat partiel ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "client/demande";
    }

    @RequestMapping(value = "/demande/rachatPartielRemb", method = RequestMethod.POST)
    public String demandeRachatetRemboursement(Model model, String police, String ville, String montant, String periode,String mountantRembour) throws MessagingException {
        System.out.println(" police:" + police);
        System.out.println(" ville:" + ville);
        System.out.println("montant :" + montant);
        Demande demande = new Demande();
        demande.setMotif("Rachat partiel + Remboursement");
        demande.setContrat(police);
        demande.setPersonne(personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demande.setVille(ville);
        demande.setStatut("Initiée");
        demande.setUsername(session.getUsername());
        demande.setContenu("demande de rachat partiel d'un montant de :" + montant + " du contrat :" + police + "dans la ville :" + ville + " effectuée par :" + personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demandeRepository.save(demande);
        try {
            Context context = new Context();
            context.setVariable("police",police);
            context.setVariable("ville",ville);
            context.setVariable("montant",montant);
            context.setVariable("periode",periode);
            context.setVariable("mountantRembour",mountantRembour);
            Future<String> content = mailTemplateHandler.build("mailTemplates/rachatPartielAvecRemboursement",context);
            javaMailSenderHandler.send(Constant.mail_dev,"demande de rachat partiel + Remboursement ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande de rachat partiel + Remboursement ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "client/demande";
    }

    @RequestMapping(value = "/demande/rachatTotal",method = RequestMethod.POST)
    public String askTotal(Model model, String police, String ville){
        System.out.println(" police:" + police);
        System.out.println(" ville:" + ville);
        Demande demande = new Demande();
        demande.setMotif("Rachat Total");

        demande.setPersonne(personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demande.setStatut("Initiée");
        demande.setUsername(session.getUsername());
        demande.setContenu("demande de rachat total du contrat :" + police + "dans la ville :" + ville + " effectuée par :" + personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demandeRepository.save(demande);
        try {
            Context context = new Context();
            context.setVariable("police",police);
            context.setVariable("ville",ville);
            context.setVariable("perso",personneRepository.findByReference(session.getIdentifiant()));
            Future<String> content = mailTemplateHandler.build("mailTemplates/rachatTotal",context);
            javaMailSenderHandler.send(Constant.mail_dev,"demande de rachat partiel ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande de rachat partiel ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "client/demande";
    }

    @RequestMapping(value = "/demande/avanceSurPolice",method = RequestMethod.POST)
    public String askAvanceSurPolice(Model model, String police, String ville){
        System.out.println(" police:" + police);
        System.out.println(" ville:" + ville);
        Demande demande = new Demande();
        demande.setMotif("Rachat Total");

        demande.setPersonne(personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demande.setStatut("Initiée");
        demande.setUsername(session.getUsername());
        demande.setContenu("demande de rachat total du contrat :" + police + "dans la ville :" + ville + " effectuée par :" + personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demandeRepository.save(demande);
        try {
            Context context = new Context();
            context.setVariable("police",police);
            context.setVariable("ville",ville);
            context.setVariable("perso",personneRepository.findByReference(session.getIdentifiant()));
            Future<String> content = mailTemplateHandler.build("mailTemplates/avanceSurPolice",context);
            javaMailSenderHandler.send(Constant.mail_dev,"demande d'avance sur police ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande d'avance sur police ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "client/demande";
    }

    @RequestMapping(value = "/demande/termeContrat",method = RequestMethod.POST)
    public String askTermeContrat(Model model, String police, String ville){
        System.out.println(" police:" + police);
        System.out.println(" ville:" + ville);
        Demande demande = new Demande();
        demande.setMotif("Rachat Total");

        demande.setPersonne(personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demande.setStatut("Initiée");
        demande.setUsername(session.getUsername());
        demande.setContenu("demande de rachat total du contrat :" + police + "dans la ville :" + ville + " effectuée par :" + personneRepository.findByReference(session.getIdentifiant()).getLastname());
        demandeRepository.save(demande);
        try {
            Context context = new Context();
            context.setVariable("police",police);
            context.setVariable("ville",ville);
            context.setVariable("perso",personneRepository.findByReference(session.getIdentifiant()));
            Future<String> content = mailTemplateHandler.build("mailTemplates/termeContrat",context);
            javaMailSenderHandler.send(Constant.mail_dev,"demande terme contrat ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"demande terme contrat ",content.get().toString(),true);
        } catch (MessagingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "client/demande";
    }

    //TCHAT
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        messageRepository.save(new Message(userRepository.findFirstByUsername(message.getExpediteur()),userRepository.findFirstByUsername(message.getDestinataire()),message.getContenu(),false));
        // System.out.println("session id => "+session.getUsername());
        return new Greeting(message.getExpediteur(),message.getContenu(),message.getDestinataire());
    }
}
