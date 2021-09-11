package com.digital.controller.api;

import com.digital.model.*;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.model.response.UserMessagesDetailReponse;
import com.digital.model.tchat.Greeting;
import com.digital.model.tchat.Message;
import com.digital.repository.*;
import com.digital.service.SMSHelper;
import com.digital.util.Constant;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.LongStream;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
@Api(value = "TCHAT", description = "TCHAT API")
public class TchatController {

    private final UserRepository userRepository;
    private final ContratRepository contratRepository;
    private final PersonneRepository personneRepository;
    private final QuitanceRepository quitanceRepository;
    private final ObjetsRepository objetsRepository;
    private final MessageRepository messageRepository;
    private final SituationCompteRepository situationCompteRepository;
    private final SessionModel session;
    @Autowired
    public TchatController(UserRepository userRepository,ContratRepository contratRepository,
                          PersonneRepository personneRepository,MessageRepository messageRepository,
                          QuitanceRepository quitanceRepository,ObjetsRepository objetsRepository,
                           SessionModel session, SituationCompteRepository situationCompteRepository) {
        this.userRepository = userRepository;
        this.contratRepository = contratRepository;
        this.quitanceRepository = quitanceRepository;
        this.personneRepository = personneRepository;
        this.objetsRepository = objetsRepository;
        this.messageRepository = messageRepository;
        this.situationCompteRepository = situationCompteRepository;
        this.session = session;
    }

    @RequestMapping(value = "/user-message", method = RequestMethod.POST)
    ResponseBody<UserMessagesDetailReponse> getUserMessage(@RequestParam Long id) {
        System.out.println("id API "+id);
        RestResponse restResponse = new RestResponse();
        UserMessagesDetailReponse userMessagesDetailReponse = new UserMessagesDetailReponse();
        System.out.println("id =>"+id);
        List<Message> messages = null;
        List<Greeting> greetings = new ArrayList<>();
        List<User> usersConnected = userRepository.findAllByState(Constant.CONNECTED);
        if (id >0 ){
            User user_destinaire = userRepository.findOne(id);
            User user_expediteur= userRepository.findFirstByUsername(session.getUsername());
            if (user_destinaire != null ){
                 Personne dest = personneRepository.findByReference(Long.parseLong(user_destinaire.getUsername())-10000000);
                 //est ce un corporate ?
                if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == (Long.parseLong(user_destinaire.getUsername())-10000000))){
                    System.out.println("c est un corporate");

                    //situation de compte globale ou le compte de resultat
                }else {
                    System.out.println("c'est pas un corporate");
                    SituationCompte situationCompte = situationCompteRepository.findByCodeContrat(contratRepository.findByAssure(dest).get(0).getPolice());
                    if (situationCompte !=null){
                        userMessagesDetailReponse.setCode_contrat(situationCompte.getCodeContrat());
                        userMessagesDetailReponse.setCotisationAnnuelle(situationCompte.getCotisationAnnuelle());
                        userMessagesDetailReponse.setCapitalAcquisJanv(situationCompte.getCapitalAcquisJanv());
                        userMessagesDetailReponse.setCumulPrimeAu31122016(situationCompte.getCumulPrimeAu31122016());
                        userMessagesDetailReponse.setPrimeNette(situationCompte.getPrimeNette());
                        userMessagesDetailReponse.setInteret(situationCompte.getInteret());
                        userMessagesDetailReponse.setPrestation(situationCompte.getPrestation());
                        userMessagesDetailReponse.setCapitalAu(situationCompte.getCapitalAu());
                    }

                }
                 if (dest != null){
                     List<Contrat> contrats = contratRepository.findBySouscripteur(dest);
                     contrats.addAll(contratRepository.findByAssure(dest));
                     List<Contrat> listWithoutDuplicates = new ArrayList<>(new HashSet<>(contrats));
                     userMessagesDetailReponse.setExpediteurContratList(listWithoutDuplicates);
                     userMessagesDetailReponse.setDest_firstName(dest.getFirstname());
                     userMessagesDetailReponse.setDest_lastName(dest.getLastname());
                 }else {
                     userMessagesDetailReponse.setDest_firstName(user_destinaire.getFirstname());
                     userMessagesDetailReponse.setDest_lastName(user_destinaire.getLastname());
                 }
                userMessagesDetailReponse.setDest_username(user_destinaire.getUsername());

                userMessagesDetailReponse.setExp_firstName(user_expediteur.getFirstname());
                System.out.println("Exp_firstName "+user_expediteur.getFirstname());
                userMessagesDetailReponse.setExp_username(user_expediteur.getUsername());
                System.out.println("Exp_username "+user_expediteur.getUsername());
                userMessagesDetailReponse.setExp_lastName(user_destinaire.getLastname());
                System.out.println("Exp_lastName "+user_expediteur.getLastname());

                userMessagesDetailReponse.setUsername(user_destinaire.getUsername());
                userMessagesDetailReponse.setBirthdate(user_destinaire.getBirthdate());
                userMessagesDetailReponse.setPhone(user_destinaire.getPhone());
                userMessagesDetailReponse.setMatricule(user_destinaire.getMatricule());
                userMessagesDetailReponse.setAddress(user_destinaire.getAddress());
                userMessagesDetailReponse.setEmail(user_destinaire.getEmail());
               // messages = (List<Message>) messageRepository.findByExpediteurOrderByIdAsc(user_destinaire);
                messages = (List<Message>) messageRepository.findByExpediteurOrDestinataireAndExpediteurOrderByIdAsc(user_destinaire,user_destinaire,user_expediteur);
                if (messages != null && messages.size() >0){
                    System.out.println("taille message "+messages.size());
                    for ( Message m :messages){
                        System.out.println( "date du message :" );
                        m.setState(true);
                        messageRepository.save(m);
                        greetings.add(new Greeting(m.getExpediteur().getUsername(),m.content,m.getDestinataire().getUsername()));
                    }
                    userMessagesDetailReponse.setMessages(greetings);
                }
                System.out.println("user selected :"+user_destinaire.getLastname()+" prenom :"+user_destinaire.getFirstname());
               // model.addAttribute("userMesg",user);
            }
        }
       // model.addAttribute("connected",usersConnected);
        return new ResponseBody<>(userMessagesDetailReponse, restResponse);
    }

    @RequestMapping(value = "user/search", method = RequestMethod.POST)
    ResponseBody<List<User>> searchUser(@RequestParam String name){
        RestResponse restResponse = new RestResponse();
        List<User> users = new ArrayList<>();
        System.out.println("val de recherche :"+name);
        if (name != null && !name.isEmpty()){
            users = userRepository.findAllByStateAndLastnameContainingIgnoreCaseOrFirstnameContainingIgnoreCase(Constant.CONNECTED,name,name);
        }else {
            users = userRepository.findAllByStateAndUsernameIsNotOrderByMessageNonLuAsc(Constant.CONNECTED,Constant.USERNAME_MANAGER_SAHAM);
        }
        System.out.println(users.size()+" userscontainsIgnore trouvé");
        return new ResponseBody<>(users, restResponse);

    }


}
