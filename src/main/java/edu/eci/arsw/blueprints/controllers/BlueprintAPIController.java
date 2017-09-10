/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
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
import java.util.HashSet;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @RequestMapping(path = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> getByAuthorAndName(@PathVariable("author") String author, @PathVariable("bpname") String bpname) {
        try {
            return new ResponseEntity<>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostRecursoPlanos(@RequestBody Blueprint resource) {
        try {
            bps.addNewBlueprint(resource);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(path = "/{author}/{bpname}",method = RequestMethod.PUT)
    public ResponseEntity<?> actualizaPlano(@PathVariable String author, @PathVariable String bpname, @RequestBody Blueprint rs){
        try{
            bps.getBlueprint(author, bpname);
            bps.updateBlueprint(author, bpname, rs);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(BlueprintNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
