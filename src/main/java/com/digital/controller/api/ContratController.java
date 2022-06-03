package com.digital.controller.api;

import com.digital.facade.AuthenticationFacade;
import com.digital.model.*;
import com.digital.model.api.*;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.*;
import com.digital.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
public class ContratController {

    private final UserRepository userRepository;
    private final ContratRepository contratRepository;
    private final PersonneRepository personneRepository;
    private final QuitanceRepository quitanceRepository;
    private final ObjetsRepository objetsRepository;
    private final StatutQuitanceRepository statutQuitanceRepository;
    private final SituationCompteRepository situationCompteRepository;
    private final DemandeContratRepository demandeContratRepository;
    private final VilleRepository villeRepository;
    private final SessionModel session;
    @Autowired
    public ContratController(UserRepository userRepository,
                             ContratRepository contratRepository,
                             PersonneRepository personneRepository,
                             QuitanceRepository quitanceRepository,
                             ObjetsRepository objetsRepository,
                             SituationCompteRepository situationCompteRepository,
                             StatutQuitanceRepository statutQuitanceRepository,
                             AuthenticationFacade authenticationFacade,
                             SessionModel session,DemandeContratRepository demandeContratRepository,
                             VilleRepository villeRepository) {
        this.userRepository = userRepository;
        this.contratRepository = contratRepository;
        this.personneRepository = personneRepository;
        this.quitanceRepository = quitanceRepository;
        this.objetsRepository = objetsRepository;
        this.situationCompteRepository =situationCompteRepository;
        this.statutQuitanceRepository =statutQuitanceRepository;
        this.session = session;
        this.demandeContratRepository = demandeContratRepository;
        this.villeRepository = villeRepository;
    }

    @GetMapping(value = "/user/contrat/{username}")
    public ResponseBody<List<ContratApi>> contratUser(@PathVariable String username){
        double cumul_impaye;
        double cumul_total_impaye = 0;
        double cumul_total_paye = 0;
        double cumul_paye;
        double cumul_instance;
        List<Quitance> quitances;
        List<Contrat> contrats ;
        int[] codes = {1, 6};
        System.out.println("username "+username);
        List<ContratApi> contratApiList = new ArrayList<>();
        ContratApi contratApi;
        User user = userRepository.findFirstByUsername(username);
        RestResponse restResponse = new RestResponse();
        if (user == null){
            System.out.println("user null ");
            restResponse.setError(true);
            restResponse.setMessage("identifiant incorrect");
        }else {
            System.out.println("user not null ");

            Personne personne = personneRepository.findByReference(Long.parseLong(username)-10000000);
            if(personne != null){
                System.out.println("personne not nul ");
                System.out.println("personne trouvé "+personne.getReference());
                restResponse.setError(false);
                restResponse.setMessage("les contrats");
                contrats = contratRepository.findBySouscripteur(personne);

                for (Contrat contrat : contrats) {
                    cumul_impaye = 0;
                    cumul_paye = 0;
                    cumul_instance = 0;
                    contratApi = new ContratApi();
                    contratApi.setContrat(contrat);
                    quitances = quitanceRepository.findByContratAndObjetIn(contrat, objetsRepository.findByCodeIn(codes));
                    if (quitances != null && quitances.size() > 0) {

                        for (Quitance quitance : quitances) {
                            if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("impayé")) {
                                cumul_impaye += (quitance.getMontantEmis());
                                cumul_total_impaye += (quitance.getMontantEmis());
                            }
                            if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("payé")){
                                cumul_paye += quitance.getMontantPaye();
                                cumul_total_paye += quitance.getMontantPaye();
                            }
                            if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("en instance")){
                                cumul_instance += quitance.getMontantPaye();
                            }
                        }
                        contratApi.setCumulImpaye((int)cumul_impaye);
                        contratApi.setCumulPaye((int)cumul_paye);
                        contratApiList.add(contratApi);
                    }
                }
            }else {
                restResponse.setError(true);
                System.out.println("personne null ");

                restResponse.setMessage("personne inexistante");
            }

        }
        return new ResponseBody<List<ContratApi>>(contratApiList, restResponse);

    }

    //modification de l'api de relevé de compte
    @GetMapping(value = "/contrat/releve/cotisation/{police}")
    public ResponseBody<ReleveApi> releveDeCotisation(@PathVariable String police){
        RestResponse restResponse = new RestResponse();
        List<Quitance> quitances = null;
        int[] codes = {1, 6};
        ReleveApi releveApi = new ReleveApi();
        if (police !=null){
            Contrat contrat = contratRepository.findFirstByPolice(police);
            if (contrat != null){
                releveApi.setContrat(contrat.getPolice());
                quitances = quitanceRepository.findByContratAndStatutQuitanceAndObjetInOrderByEcheance(contrat, statutQuitanceRepository.findByStatutQuit("impayé"),objetsRepository.findByCodeIn(codes));
                if (quitances != null && quitances.size() > 0) {
                    releveApi.setImpayes(quitances);
                }
                restResponse.setError(false);
                restResponse.setMessage("les quittances impayées");
            }else {
                restResponse.setError(true);
                restResponse.setMessage("contrat inexistant");
            }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("userneme ne peut etre null");
        }
        return new ResponseBody<ReleveApi>(releveApi,restResponse);
    }

    //situation de compte actuelle
    @GetMapping(value = "/contrat/situation/compte/actuelle/{police}")
    public ResponseBody<SituationCompteActuel> situationDeCompte(@PathVariable String police){
        RestResponse restResponse = new RestResponse();
        SituationCompteActuel situationCompteActuel = new SituationCompteActuel();
        if (police != null){
            Contrat contrat = contratRepository.findFirstByPolice(police);
            if (contrat != null){
                situationCompteActuel.setContrat(contrat.getPolice());
                situationCompteActuel.setValeurDeRachat(quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(270)));
                situationCompteActuel.setValeurDeReduction(quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(210)));
                situationCompteActuel.setPm(quitanceRepository.findByContratAndObjet(contrat, objetsRepository.findByCode(3)));
                restResponse.setError(false);
                restResponse.setMessage("situation de compte actuelle");
            }else {
                restResponse.setError(true);
                restResponse.setMessage("aucun contrat ne correspond");
            }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("police null");
        }
        return new ResponseBody<SituationCompteActuel>(situationCompteActuel,restResponse);
    }

    //situation de compte annuelle
    @GetMapping(value = "/contrat/situation/compte/annuelle/{police}")
    public ResponseBody<SituationCompteAnnuelle> situationDeCompteAnnuelle(@PathVariable String police){
        RestResponse restResponse = new RestResponse();
        SituationCompteAnnuelle situationCompteAnnuelle = new SituationCompteAnnuelle();
        if (police != null){
            Contrat contrat =  contratRepository.findFirstByPolice(police);
            if (contrat != null){
                situationCompteAnnuelle.setContrat(contrat.getPolice());
                situationCompteAnnuelle.setPrime(contrat.getPrime());
                try {
                    situationCompteAnnuelle.setEtatAnnuel(etat(contrat));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                restResponse.setMessage("situation de compte annuelle");
            }else {
                restResponse.setError(true);
                restResponse.setMessage("aucun contrat ne correspond");
            }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("la police ne peut etre null");
        }
        return new ResponseBody<SituationCompteAnnuelle>(situationCompteAnnuelle,restResponse);
    }

    @GetMapping(value = "/contrat/prestation/{police}")
    public ResponseBody<List<Quitance>> prestation(@PathVariable String police){
        RestResponse restResponse = new RestResponse();
        String[] tableau = { "Rachat partiel", "avance", "Rembousement avance", "Versement libre"};
        List<Quitance> quitances = null;
        Contrat contrat = contratRepository.findFirstByPolice(police);
        if (contrat == null){
            restResponse.setError(true);
            restResponse.setMessage("contrat inexistant");
        }else {
            restResponse.setError(false);
            restResponse.setMessage("les quitance sautre que prime");
            quitances = (List<Quitance>) quitanceRepository.findByContratAndObjetIn(
                    contrat,objetsRepository.findByNomObjetIn(Arrays.asList(tableau)));

        }
        return new ResponseBody<List<Quitance>>(quitances,restResponse);
    }

    @GetMapping(value = "/contrat/of/demande")
    public ResponseBody<List<DemandeContrat>> getPersonneOfContratDemande(){
        List<DemandeContrat> contrats = new ArrayList<>();

        session.getContrats().forEach(c-> System.out.println("Les contrats sont ==> "+c.getPolice()));
        /**
        if(LongStream.of(Constant.refCorporate).anyMatch(x -> x == session.getIdentifiant())){
            contrats  = demandeContratRepository.findBySouscripteur(session.getIdentifiant());
        }
        else {
            for (Contrat contrat: session.getContrats()){
                contrats.add(new DemandeContrat(contrat.getId(),contrat.getPolice(), contrat.getAssure().getFirstname(),contrat.getId_souscrpteur()));
            }
        }
        **/
        for (Contrat contrat: session.getContrats()){
            contrats.add(new DemandeContrat(contrat.getId(),contrat.getPolice(), contrat.getAssure().getFirstname(),contrat.getId_souscrpteur()));
        }
        RestResponse restResponse = new RestResponse();
        restResponse.setError(false);
        restResponse.setMessage("les contrats");
        return new ResponseBody<List<DemandeContrat>>(contrats,restResponse);
    }

    @GetMapping(value = "/ville/of/demande")
    public ResponseBody<List<Ville>> getVilleOfDemande(){
        return new ResponseBody<List<Ville>>(villeRepository.findAll(),new RestResponse(false,"Les villes"));
    }

    @GetMapping(value = "/getDetail/askeur")
    public ResponseBody<Askeur> detailDemandeur(boolean isEntreprise, Long id){
        Askeur askeur = new Askeur();
        Contrat contrat = contratRepository.findOne(id);
        if (contrat != null){
            askeur.setContrat(contrat);
            if (isEntreprise){
                askeur.setPersonne(contrat.getAssure());
            }else {
                askeur.setPersonne(contrat.getSouscripteur());

            }
        }
        return new ResponseBody<Askeur>(askeur,new RestResponse(false,"Personne"));
    }

    @GetMapping(value = "/ask/rachatTotal/corporate")
    public ResponseBody <List<AskedRachatTotalCorporate>> askRachatCorporate(String nom, Long contrat, String lieu,Model model) {
        boolean isPresent = false;
        Personne personne = session.getPersonne();
        if (personne != null) {
            model.addAttribute("perso", personne);
        }
        Contrat ctrat = contratRepository.findOne(contrat);
        AskedRachatTotalCorporate asked = new AskedRachatTotalCorporate();
        for ( AskedRachatTotalCorporate ask : session.getAskeds()){
            if (ask.getIdContrat() == contrat){
                isPresent = true;
            }
        }
        if (lieu != null ){
            asked.setVille(lieu);
        }
        if (ctrat != null ){
            asked.setNumContrat(ctrat.getPolice());
            asked.setIdContrat(ctrat.getId());
            if (ctrat.getAssure() !=null){
                asked.setPersonne(ctrat.getAssure().getLastname());
            }
        }
        if (!isPresent){
            session.getAskeds().add(asked);
        }
        return new ResponseBody<>(session.getAskeds(),new RestResponse(false,"askeds"));

    }


    @GetMapping(value = "/ask/termeContrat/corporate")
    public ResponseBody <List<AskedRachatTotalCorporate>>  askTermeCorporate(String nom, Long contrat, String lieu,Model model) {
        boolean isPresent = false;
        Personne personne = session.getPersonne();
        if (personne != null) {
            model.addAttribute("perso", personne);
        }
        Contrat ctrat = contratRepository.findOne(contrat);
        AskedRachatTotalCorporate asked = new AskedRachatTotalCorporate();
        for ( AskedRachatTotalCorporate ask : session.getTermesContrat()){
            if (ask.getIdContrat() == contrat){
                isPresent = true;
            }
        }
        if (lieu != null ){
            asked.setVille(lieu);
        }
        if (ctrat != null ){
            asked.setNumContrat(ctrat.getPolice());
            asked.setIdContrat(ctrat.getId());
            if (ctrat.getAssure() !=null){
                asked.setPersonne(ctrat.getAssure().getLastname());
            }
        }
        if (!isPresent){
            session.getTermesContrat().add(asked);
        }
        model.addAttribute("termes",session.getTermesContrat());
        return new ResponseBody<>(session.getTermesContrat(),new RestResponse(false,"termes"));


    }

    @GetMapping(value = "/deleteAskeur")
    public ResponseBody <List<AskedRachatTotalCorporate>> deleteDemandeur(Long ref){
        System.out.println("taille liste demandeur "+session.getAskeds().size());
        System.out.println("ref "+ref);

        for ( int i = session.getAskeds().size() - 1 ; i >= 0 ; i--){
            System.out.println("ref dans la session :"+session.getAskeds().get(i).getIdContrat());
            if (session.getAskeds().get(i).getIdContrat().equals(ref)){
                session.getAskeds().remove(session.getAskeds().get(i));

            }
        }
        return new ResponseBody<>(session.getAskeds(),new RestResponse(false,"askeds"));
    }

    @GetMapping(value = "/deleteAskeurTerme")
    public ResponseBody <List<AskedRachatTotalCorporate>>  deleteDemandeurTerme(Long ref, Model model){

        for ( int i = session.getTermesContrat().size() - 1 ; i >= 0 ; i--){
            if (session.getTermesContrat().get(i).getIdContrat().equals(ref)){
                session.getTermesContrat().remove(session.getTermesContrat().get(i));
            }
            Personne personne = session.getPersonne();
            if (personne != null) {
                model.addAttribute("perso", personne);
            }
        }
        model.addAttribute("termes",session.getTermesContrat());
        return new ResponseBody<>(session.getTermesContrat(),new RestResponse(false,"termes"));
    }


    @GetMapping(value = "/contrat/demande")
    public ResponseBody<List<Contrat>> contratOfDemande(){
        Personne personne = session.getPersonne();
        List<Contrat> contrats = contratRepository.findBySouscripteur(personne);
        RestResponse restResponse = new RestResponse();
        restResponse.setError(false);
        restResponse.setMessage("les contrats");
        return new ResponseBody<List<Contrat>>(contrats,restResponse);
    }



    @GetMapping(value = "/get/all/prestation")
    public ResponseBody<List<QuittancePrestation>> userPrestation(@RequestParam String username){
        RestResponse restResponse = new RestResponse();
        List<Quitance> quitances;
        String[] tableau = { "Rachat partiel", "avance", "Rembousement avance", "Versement libre"};
        List<QuittancePrestation> prestations = new ArrayList<>();
        QuittancePrestation prestation;
        List<Contrat> contrats ;
        if (username != null){
            Long identifiant = Long.parseLong(username)-10000000;
            Personne personne = personneRepository.findByReference(identifiant);
            if (personne !=null){
                contrats = contratRepository.findBySouscripteur(personne);
                if (contrats != null && contrats.size()>0){
                    for (Contrat contrat: contrats){
                        prestation = new QuittancePrestation();
                        prestation.setContrat(contrat.getPolice());
                        quitances =  quitanceRepository.findByContratAndObjetIn(
                                contrat,objetsRepository.findByNomObjetIn(Arrays.asList(tableau)));
                        for (Quitance quitance :quitances){
                            prestation.setQuitance(quitance);
                            prestations.add(prestation);
                        }

                    }
                    restResponse.setError(false);
                    restResponse.setMessage("les prstations par contrat");
                }else {
                    restResponse.setError(true);
                    restResponse.setMessage("Aucun contrat trouvé");
                }

            }else {
                restResponse.setError(true);
                restResponse.setMessage("Aucune personne ne correspond a cet indentifaint");
            }
        }else {
            restResponse.setError(true);
            restResponse.setMessage("username est obligatoire");
        }
        return new ResponseBody<List<QuittancePrestation>>(prestations,restResponse);
    }

    @RequestMapping(value = "/fileOfDemande", method = RequestMethod.POST)
    public HashMap<String,Object> fileOfDemande(@RequestParam(name = "file") MultipartFile file) throws IOException {
        boolean isPresent = false;
        HashMap<String,Object> results = new HashMap<>();
        System.out.println(file);
        if (!file.isEmpty()){
            System.out.println("file not empty");
            for (UploadFileResponse uploadFileResponse: session.getUploadFileResponse()){
                if (uploadFileResponse.getOriginalFilename().equals(file.getOriginalFilename())){
                    isPresent = true;
                }
            }
            if (!isPresent){
                System.out.println("file name is different");
                session.getUploadFileResponse().add(new UploadFileResponse(file.getOriginalFilename(),file.getContentType(),file.getSize()));
                System.out.println("file add");
            }
            results.put("error",false);
            results.put("message","un fichier ajouté");
            results.put("files",session.getUploadFileResponse());
        }else {
            results.put("error",true);
            results.put("message","aucun fichier");
        }
        return results;
    }


    @RequestMapping(value = "/DeletefileOfDemande", method = RequestMethod.POST)
    public HashMap<String,Object> deletefileOfDemande(@RequestParam(name = "fileName") String fileName) throws IOException {
        boolean isPresent = false;
        HashMap<String,Object> results = new HashMap<>();
        System.out.println(fileName);
        for (UploadFileResponse uploadFileResponse: session.getUploadFileResponse()){
            if (uploadFileResponse.getOriginalFilename().equals(fileName)){
                session.getUploadFileResponse().remove(uploadFileResponse);
                System.out.println("fichier supprimer "+fileName);
                results.put("message",String.format("le fichier %s a été supprimé",fileName));
                results.put("error",false);
                isPresent = true;
            }
        }
        if (!isPresent){
            System.out.println("file name is different");
            System.out.println("file add");
            results.put("error",true);
            results.put("message",String.format("le fichier %s est inexistant sur le server",fileName));
        }
        results.put("files",session.getUploadFileResponse());
        return results;
    }

    public EtatAnnuel etat(Contrat contrat) throws ParseException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(" annee encour " + (year-1));
        double cumul = 0;
        String dateV = String.format("01/01/%s", String.valueOf(year-1));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<MonthAndAmount> monthAndAmounts = new ArrayList<>();
        EtatAnnuel etatAnnuel;

        Date date = formatter.parse(dateV);
        etatAnnuel = new EtatAnnuel(date, String.valueOf(year-1));
        etatAnnuel.setId(contrat.getId());
        /*            List< Quitance> quitancess = (List<Quitance>) quitanceRepository.findByContratAndObjetAndEcheanceAfterOrderByEcheance(contrat,objetsRepository.findFirstByNomObjet("Prime"),date);*/
        Quitance qJanv = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/01/%s", String.valueOf(year-1))), formatter.parse(String.format("31/01/%s", String.valueOf(year-1))));
        Quitance qFrev = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/02/%s", String.valueOf(year-1))), formatter.parse(String.format("29/02/%s", String.valueOf(year-1))));
        Quitance qMars = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/03/%s", String.valueOf(year-1))), formatter.parse(String.format("31/03/%s", String.valueOf(year-1))));
        Quitance qAvril = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/04/%s", String.valueOf(year-1))), formatter.parse(String.format("30/04/%s", String.valueOf(year-1))));
        Quitance qMai = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/05/%s", String.valueOf(year-1))), formatter.parse(String.format("31/05/%s", String.valueOf(year-1))));
        Quitance qJuin = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/06/%s", String.valueOf(year-1))), formatter.parse(String.format("30/06/%s", String.valueOf(year-1))));
        Quitance qJull = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/07/%s", String.valueOf(year-1))), formatter.parse(String.format("31/07/%s", String.valueOf(year-1))));
        Quitance qAout = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/08/%s", String.valueOf(year-1))), formatter.parse(String.format("31/08/%s", String.valueOf(year-1))));
        Quitance qSept = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/09/%s", String.valueOf(year-1))), formatter.parse(String.format("30/09/%s", String.valueOf(year-1))));
        Quitance qOct = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/10/%s", String.valueOf(year-1))), formatter.parse(String.format("31/10/%s", String.valueOf(year-1))));
        Quitance qNov = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/11/%s", String.valueOf(year-1))), formatter.parse(String.format("30/11/%s", String.valueOf(year-1))));
        Quitance qDec = quitanceRepository.findByContratAndObjetAndEcheanceBetween(contrat, objetsRepository.findFirstByNomObjet("Prime"), formatter.parse(String.format("01/12/%s", String.valueOf(year-1))), formatter.parse(String.format("31/12/%s", String.valueOf(year-1))));
        /*            if (quitancess != null) System.out.println("quitances de l annee encours "+quitancess.size());*/

        if (qJanv != null) {
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
        Quitance qvra = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(270), formatter.parse(String.format("01/01/%s", String.valueOf(year-1))));
        if (qvra != null) {
            etatAnnuel.setValeurDeRachat(qvra.getMontantPaye());
            etatAnnuel.setValeurDeRachatYear(qvra.getDateValeur());
        }
        Quitance qvre = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(210), formatter.parse(String.format("01/01/%s", String.valueOf(year-1))));
        if (qvre != null) {
            etatAnnuel.setValeurReduction(qvre.getMontantPaye());
            etatAnnuel.setValeurReductionYear(qvre.getDateValeur());
        }
        Quitance prime = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(260), formatter.parse(String.format("01/01/%s", String.valueOf(year))));
        if (prime != null) {
            etatAnnuel.setPrimeFidelite(prime.getMontantPaye());
        }

        Quitance rp = quitanceRepository.findByContratAndObjetAndEcheanceGreaterThan(contrat, objetsRepository.findByCode(2), formatter.parse(String.format("01/01/%s", String.valueOf(year-1))));
        if (rp != null) {
            etatAnnuel.setRachatPartiel(rp.getMontantPaye());
            etatAnnuel.setRachatPartielYear(rp.getDateValeur());
        }
        etatAnnuel.setMonthAndAmounts(monthAndAmounts);

        return etatAnnuel;
    }

}
