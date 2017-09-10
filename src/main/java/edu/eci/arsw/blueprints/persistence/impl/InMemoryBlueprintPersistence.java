/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("Juan", "plano1",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
        Point[] pts1=new Point[]{new Point(140, 140),new Point(120, 120)};
        Blueprint bp1=new Blueprint("Juan", "plano2",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        
        Point[] pts2=new Point[]{new Point(140, 140),new Point(125, 125)};
        Blueprint bp2=new Blueprint("Camilo", "plano3",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        System.out.println(author+" - "+bprintname);
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> res = new HashSet<Blueprint>();
        for (Map.Entry<Tuple<String, String>, Blueprint> entry : blueprints.entrySet()) {
            Tuple<String, String> key = entry.getKey();
            Blueprint value = entry.getValue();
            if (key.o1.equals(author)){
                res.add(value);
            }
        }
        if (res.size()!=0){
            return res;
        }else{
            throw new BlueprintNotFoundException("Not such author");
        }
        
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> bpset = new HashSet<Blueprint>();
        for (Map.Entry<Tuple<String, String>, Blueprint> entry : blueprints.entrySet()) {
            Tuple<String, String> key = entry.getKey();
            Blueprint value = entry.getValue();
            bpset.add(value);
        }
        if (bpset.size()!=0){return bpset;}
        else{throw new BlueprintNotFoundException("There are no blueprints");}
    }

    @Override
    public void update(String author, String name, Blueprint blueprint) throws BlueprintNotFoundException{
        Blueprint bp = getBlueprint(author, name);
        bp.setAuthor(blueprint.getAuthor());
        bp.setName(blueprint.getName());
        bp.setPoints(blueprint.getPoints());
    }

    
    
}
