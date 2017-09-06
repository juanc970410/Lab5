/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps = null;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprint() {
        try {

            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            //Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
