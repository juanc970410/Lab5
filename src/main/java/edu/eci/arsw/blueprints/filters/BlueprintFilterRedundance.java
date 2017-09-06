/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2103021
 */
@Service
public class BlueprintFilterRedundance implements BlueprintFilter{

    @Override
    public Blueprint filter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        Blueprint rbp = new Blueprint(bp.getAuthor(), bp.getName());
        for (int i = 0; i < points.size()-1; i++) {
            if(!(points.get(i).equals(points.get(i+1)))){
                rbp.addPoint(points.get(i));
            }
        }
        
        rbp.addPoint(points.get(points.size()-1));
        return rbp;
    }
}
