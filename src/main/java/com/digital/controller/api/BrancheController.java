package com.digital.controller.api;

import com.digital.model.Branche;
import com.digital.model.response.ResponseBody;
import com.digital.model.response.RestResponse;
import com.digital.repository.BrancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sahamvie")
public class BrancheController {

    @Autowired
    private BrancheRepository brancheRepository;
    @RequestMapping(value = "/branches",method = RequestMethod.GET)
    public ResponseBody<List<Branche>> branches(){
        List<Branche> branches = brancheRepository.findAll();
        return new ResponseBody<List<Branche>>(branches, new RestResponse(false,"les produits par branche"));
    }
}
