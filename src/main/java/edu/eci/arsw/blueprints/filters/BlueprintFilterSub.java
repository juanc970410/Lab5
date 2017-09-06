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

/**
 *
 * @author 2103021
 */
public class BlueprintFilterSub implements BlueprintFilter{

    @Override
    public Blueprint filter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        Blueprint rbp = new Blueprint(bp.getAuthor(), bp.getName());
        for (int i = 0; i < points.size(); i++) {
            if(i%2!=0){
                rbp.addPoint(points.get(i));
            }
        }
        return rbp;
    }
    
}
