package com.digital.controller.api;

import com.digital.model.*;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.ContratRepository;
import com.digital.repository.OperationRepository;
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
public class OperationController {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationController(OperationRepository operationRepository, ContratRepository contratRepository, PersonneRepository personneRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping(value = "/user/operation/{username}")
    public ResponseBody<List<Operation>> getOpertationByUser(@PathVariable String username){
        RestResponse restResponse = new RestResponse();
        List<Operation> operations = operationRepository.findAllByUsername(username);
        restResponse.setError(false);
        restResponse.setMessage("les operations");
        return new ResponseBody<List<Operation>>(operations, restResponse);
    }
}
