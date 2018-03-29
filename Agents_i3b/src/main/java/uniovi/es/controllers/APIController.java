package uniovi.es.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uniovi.es.entities.Agent;
import uniovi.es.entities.AgentLogin;
import uniovi.es.services.AgentsService;


@RestController
public class APIController {

	@Autowired
    private AgentsService agentsService;

	@RequestMapping(value = "/checkAgent", method = RequestMethod.POST)
    public ResponseEntity user(@RequestBody AgentLogin login) {
        // If the combination of email, password and kindCode is correct, 200 OK is returned
        // If not, 404 NOT FOUND is returned
        Agent user = agentsService.getAgent(login.getLogin(), login.getPassword(),login.getKindCode());
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}