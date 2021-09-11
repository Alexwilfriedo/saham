package com.digital.controller.api;

import com.digital.model.*;
import com.digital.model.api.Darsboard;
import com.digital.model.api.HttpSms;
import com.digital.model.response.Darshboard;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.*;
import com.digital.service.SMSHelper;
import com.digital.util.Utility;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.digital.util.Utility.getRef;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
@Api(value = "Authentification", description = "AUTH API")
public class AuthController {


    private final UserRepository userRepository;
    private final ContratRepository contratRepository;
    private final PersonneRepository personneRepository;
    private final QuitanceRepository quitanceRepository;
    private final ObjetsRepository objetsRepository;
    private final SMSHelper smsHelper;

    @Autowired
    public AuthController(UserRepository userRepository,ContratRepository contratRepository,
                          PersonneRepository personneRepository,SMSHelper smsHelper,
                          QuitanceRepository quitanceRepository,ObjetsRepository objetsRepository) {
        this.userRepository = userRepository;
        this.contratRepository = contratRepository;
        this.quitanceRepository = quitanceRepository;
        this.personneRepository = personneRepository;
        this.objetsRepository = objetsRepository;
        this.smsHelper = smsHelper;
    }

    @PostMapping(value="/user/darshboard")
    ResponseBody<Darsboard> darshboard(@RequestParam String username){
        double cumul_impaye;
        double cumul_total_impaye = 0;
        double cumul_total_paye = 0;
        double cumul_paye;
        double cumul_instance;
        int statut_impaye = 0;
        int statut_paye = 0;
        int statut_instance = 0;
        int[] codes = {1, 6};
        Darsboard apidarsboard = new Darsboard();
        List<Quitance> quitanceList = new ArrayList<>();
        List<Darshboard> darshboardsContrat = new ArrayList<>();
        List<Quitance> quitances;
        List<Contrat> contrats ;
        Map<String, Produit> produitMap = new HashMap<>();
        Long identifiant = Long.parseLong(username)-10000000;
        RestResponse restResponse = new RestResponse();
        Personne personne = personneRepository.findByReference(identifiant);
        if (personne != null){
            System.out.println("firstName"+personne.getFirstname()+" LastName :"+personne.getLastname()+" reference :"+personne.getReference() );
            contrats = contratRepository.findBySouscripteur(personne);
            for (Contrat contrat : contrats) {
                System.out.println("contrat :"+contrat.getPolice());
                cumul_impaye = 0;
                cumul_paye = 0;
                cumul_instance = 0;
                produitMap.put(contrat.getProduit().getNomProduit(), contrat.getProduit());
                Darshboard darshboard = new Darshboard(contrat.getPolice());
                quitances = quitanceRepository.findByContratAndObjetIn(contrat, objetsRepository.findByCodeIn(codes));
                if (quitances != null && quitances.size() > 0) {
                    quitanceList.addAll(quitances);
                    for (Quitance quitance : quitances) {
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("impayé")) {
                            statut_impaye++;
                            cumul_impaye += (quitance.getMontantEmis());
                            cumul_total_impaye += (quitance.getMontantEmis());
                        }
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("payé")){
                            statut_paye++;
                            cumul_paye += quitance.getMontantPaye();
                            cumul_total_paye += quitance.getMontantPaye();
                        }
                        if (quitance.getStatutQuitance() != null && quitance.getStatutQuitance().getStatutQuit() != null &&  quitance.getStatutQuitance().getStatutQuit().equalsIgnoreCase("en instance")){
                            statut_instance++;
                            cumul_instance += quitance.getMontantPaye();
                        }
                    }
                    darshboard.setCumulImpaye(cumul_impaye);
                    darshboard.setCumulPaye(cumul_paye);
                    darshboard.setPercent();
                    darshboardsContrat.add(darshboard);
                }
            }
            apidarsboard.setTotal_contrat(contrats.size());
            apidarsboard.setDarshboardsContrat(darshboardsContrat);
            apidarsboard.setCumul_total_impaye((int)cumul_total_impaye);
            apidarsboard.setCumul_total_paye((int) cumul_total_paye);
            apidarsboard.setStatut_impaye(statut_impaye);
            apidarsboard.setStatut_paye(statut_paye);
            apidarsboard.setStatut_instance(statut_instance);
            restResponse.setError(false);
            restResponse.setMessage("les donnees");
        }else {
            restResponse.setError(true);
            restResponse.setMessage("Aucune personne ne correspond a cet indentifaint");
        }

        return new ResponseBody<>(apidarsboard, restResponse);
    }



    @PostMapping(value = "/user/authenticate")
    ResponseBody<User> authenticateUser(@RequestParam String username,
                                                 @RequestParam String password) {
        System.out.println(username + " " + password);
        User user;
        user =userRepository.findFirstByUsername(username);
        RestResponse restResponse = new RestResponse();
     if (user == null ||!new BCryptPasswordEncoder().matches(password, user.getPassword())){
         System.out.println("KIOKIO");
          Contrat contrat = contratRepository.findFirstByPolice(username);
          if (contrat == null || contrat.getSouscripteur() == null){
              restResponse.setError(true);
              restResponse.setMessage("contrat ou souscripteur incorrect");
          }else {
              user = userRepository.findFirstByUsername(Utility.getRef(contrat.getSouscripteur()));
              if (user == null || !new BCryptPasswordEncoder().matches(password, user.getPassword()) ){
                  restResponse.setError(true);
                  restResponse.setMessage("souscripteur ou mot de passe incorrect");
              }else {
                  restResponse.setError(false);
                  restResponse.setMessage("connexion effectuée avec succes");
              }
          }
     }else {
         /*restResponse.setError(false);
         System.out.println("nom "+user.getLastname()+" prenom "+user.getFirstname());
         restResponse.setMessage("connexion effectuée avec succes");*/
      }
        return new ResponseBody<>(user, restResponse);
    }

    @PostMapping(value = "/user/signup")
    ResponseBody<User> userSignup(
                                  @RequestParam String policy,
                                  @RequestParam String civility,
                                  @RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam String birthDate,
                                  @RequestParam String phone,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String cpassword) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        User user = new User(civility,firstName,lastName,formatter.parse(birthDate),phone,email,password,cpassword);
        Map<String, Object> errors = validateUserSubmission(user,policy,birthDate);
        RestResponse restResponse = new RestResponse();
        if (errors.isEmpty()) {
               // user.setRoles(Collections.singleton(roleRepository.findByRole("USER")));

           String contenu = String.format("Votre identifiant est :%s",getRef(personneRepository.findByReference(contratRepository.findFirstByPolice(policy).getId_souscrpteur())));
          //  SmsProvider.SendSmsViaInfoBip("Saham vie",contenu,String.format("225%s",user.getPhone()));
            HttpSms httpSms = new HttpSms.Builder("Saham vie",String.format("225%s",user.getPhone()),contenu,"UTF-8").build();
            smsHelper.sendTo(httpSms);
            user.setUsername(getRef(personneRepository.findByReference(contratRepository.findFirstByPolice(policy).getId_souscrpteur())));
            user.setPassword(password);
            user = userRepository.save(user);
            // eventPublisher.publishEvent(new RegistrationCompleteEvent("USER", user, request));
            restResponse.setError(false);
            restResponse.setMessage("enregistrement effectué avec succes");
        }
        else {
            restResponse.setError(true);
            restResponse.setMessage(errors.get("message").toString());
        }
        return new ResponseBody<User>(user, restResponse);
    }

    private Map<String, Object> validateUserSubmission(User user,String police,String birthDate) {
        Map<String, Object> errors = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        if (police == null || police.isEmpty() || contratRepository.findFirstByPolice(police) ==null) {
            errors.put("error", true);
            errors.put("message", "le numero de police est obligatoire");
        }
        Contrat contrat = contratRepository.findFirstByPolice(police);
        String identifiant = getRef(personneRepository.findByReference(contrat.getId_souscrpteur()));
        if (userRepository.findFirstByUsername(identifiant) != null) {
            errors.put("error", true);
            errors.put("message", "identifiant deja utilisé");
        }

        if (user.getFirstname() == null
                || user.getFirstname().isEmpty()
                ||user.getBirthdate() == null
                || user.getBirthdate().toString().isEmpty()
                || user.getLastname() == null
                || user.getLastname().isEmpty()) {
            errors.put("error", true);
            errors.put("message", "les champs nom,prenom et date de naissance sont obligatoire");
        }

        if (user.getPassword() == null
                || user.getPasswordConfirmation() == null
                || user.getPassword().isEmpty()
                || user.getPasswordConfirmation().isEmpty()
                || user.getPassword().compareTo(user.getPasswordConfirmation()) != 0) {
            System.out.println("user.getPassword() "+user.getPassword());
            System.out.println("user.getPasswordConfirmation() "+user.getPasswordConfirmation());
            errors.put("error", true);
            errors.put("message", "Password error. Veuiller verifier le mot de passe !!!");
        }
        Long id_souscr = contrat.getId_souscrpteur();
        System.out.println("ID SOUSCRIPTEUR ==> "+id_souscr);
        Personne personneOfContrat = personneRepository.findByReference(id_souscr);
        System.out.println("Personne = "+personneOfContrat.toString());
        if (personneOfContrat != null) {
            Date date = personneOfContrat.getBirthdate();
            if (!formatter.format(date).equalsIgnoreCase(birthDate.replaceAll("/","-"))){
                errors.put("error", true);
                errors.put("message", "aucun souscripteur pour cette date de naissance !!!");
            }
        }else {
            errors.put("error", true);
            errors.put("message", "Aucune personne trouvée pour ce contrat !!!");
        }
        return errors;
    }
}
