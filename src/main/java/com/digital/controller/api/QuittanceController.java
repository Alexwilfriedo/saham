package com.digital.controller.api;

import com.digital.model.Contrat;
import com.digital.model.Personne;
import com.digital.model.Quitance;
import com.digital.model.User;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.ContratRepository;
import com.digital.repository.PersonneRepository;
import com.digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
public class QuittanceController {

    private final UserRepository userRepository;
    private final ContratRepository contratRepository;
    private final PersonneRepository personneRepository;

    @Autowired
    public QuittanceController(UserRepository userRepository, ContratRepository contratRepository, PersonneRepository personneRepository) {
        this.userRepository = userRepository;
        this.contratRepository = contratRepository;
        this.personneRepository = personneRepository;
    }

    @GetMapping(value = "/user/quittanceOfContrat/{codeContrat}")
    public ResponseBody<List<Quitance>> quittanceUserByContrat(@PathVariable String codeContrat){
        RestResponse restResponse = new RestResponse();
        Contrat contrat = contratRepository.findFirstByPolice(codeContrat);
        List<Quitance> quitances = null;
        if (contrat == null){
            restResponse.setError(true);
            restResponse.setMessage("contrat inexistant");
        }else {
            restResponse.setError(false);
            restResponse.setMessage("les quittances");
             quitances = (List<Quitance>) contrat.getQuitances();

        }
        return new ResponseBody<List<Quitance>>(quitances, restResponse);
    }

    @GetMapping(value = "/user/all/quittance/{username}")
    public ResponseBody<List<Collection<Quitance>>> allQuittanceUser(@PathVariable String username){
        HashMap<String, Object> reponse = new HashMap<>();
        List<Contrat>  contrats = new ArrayList<>();
        RestResponse restResponse = new RestResponse();
        List<Collection<Quitance>> quitances = new ArrayList<>();
        User user =userRepository.findFirstByUsername(username);
        if (user !=null) {
            Long ref = user.getReference() - 10000000;
            Personne personne = personneRepository.findByReference(ref);
            contrats = (List<Contrat>) personne.getContrats();
            for (Contrat contrat : contrats) {
                quitances.add(contrat.getQuitances());
            }
            restResponse.setError(false);
            restResponse.setMessage("toutes es quittances");

        }else {
            restResponse.setError(true);
            restResponse.setMessage("identifiant inconnu");
        }
        return new ResponseBody<List<Collection<Quitance>>>(quitances, restResponse);
    }
}
