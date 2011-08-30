/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;

public class Sphere extends Shape {

    private Vec centre;
    private double radius;
    private ArrayList<Circle> latitudes;
    private ArrayList<Circle> longitudes;

    public Sphere(Model model, Color color, Vec centre, double radius) {
        super(model, color);

        this.centre = centre;
        this.radius = radius;

        latitudes = new ArrayList<Circle>();
        longitudes = new ArrayList<Circle>();

        constructSphere();
    }

    private void constructSphere() {
        Vec normal = new Vec(0.0, 0.0, 1.0).unitize();
        double increment = radius / 6.0;

        for (double distance = increment - radius; distance < radius; distance += increment) {
            Vec latitudeCentre = centre.add(normal.scale(distance));
            double latitudeRadius = Math.sqrt(radius * radius - distance * distance);
            latitudes.add(new Circle(model, color, latitudeCentre, normal, latitudeRadius));
        }

        for (double longitude = 0; longitude < 3.14; longitude += 0.4) {
            longitudes.add(new Circle(model, color, centre,
                    new Vec(Math.cos(longitude),Math.sin(longitude),0), radius));
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        for (Circle circle : latitudes) {
            circle.draw(g);
        }
        for (Circle circle : longitudes) {
            circle.draw(g);
        }
    }
}
