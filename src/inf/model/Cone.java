/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;

public class Cone extends Shape {
    private Vec apex;
    private Vec axis;
    private Vec side;
    private ArrayList<Line> slants;
    private ArrayList<Circle> circles;

    public Cone(Model model, Color color, Vec apex, Vec axis, Vec side) {
        super(model, color);

        this.apex = apex;
        this.axis = axis.unitize();
        this.side = side;

        slants = new ArrayList<Line>();
        circles = new ArrayList<Circle>();

        constructCone();
    }

    private void constructCone() {
        double height = side.add(apex.negate()).dot(axis);
        double increment = height/15.0;

        for (double distance = -1*height; distance < height; distance += increment) {
            if (distance*distance > 0.01) {
                Vec centre = apex.add(axis.scale(distance));
                double slantLength = side.add(apex.negate()).mag();
                double radius = Math.abs(distance) *
                                (Math.sqrt(slantLength*slantLength-height*height))/height;
                circles.add(new Circle(model, color, centre, axis, radius));
            }
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        for (Circle circle : circles) {
            circle.draw(g);
        }
        for (Line slant : slants) {
            slant.draw(g);
        }
    }
}
