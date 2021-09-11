package com.digital.controller.api;

import com.digital.model.Demande;
import com.digital.model.Operation;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.ContratRepository;
import com.digital.repository.DemandeRepository;
import com.digital.repository.OperationRepository;
import com.digital.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
public class InformationController {

    private final DemandeRepository demandeRepository;

    @Autowired
    public InformationController(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    @GetMapping(value = "/user/information/{username}")
    public ResponseBody<List<Demande>> getDemandeByUser(@PathVariable String username){
        RestResponse restResponse = new RestResponse();
        List<Demande> demandes = demandeRepository.findAllByUsername(username);
        restResponse.setError(false);
        restResponse.setMessage("les informations");
        return new ResponseBody<List<Demande>>(demandes, restResponse);
    }
}
