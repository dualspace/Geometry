/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;

public class Circle extends Shape {
    private Vec centre;
    private Vec normal;
    private double radius;
    private Polyline circumference;

    public Circle(Model model, Color color, Vec centre, Vec normal, double radius) {
        super(model, color);

        this.centre = centre;
        this.normal = normal.unitize();
        this.radius = radius;

        constructPolyline();
    }

    private void constructPolyline() {
        circumference = new Polyline(model, color);

        Vec ivec = new Vec(1.0,0.0,0.0);
        Vec jvec = new Vec(0.0,1.0,0.0);
        Vec kvec = new Vec(0.0,0.0,1.0);

        double test1 = normal.dot(ivec);
        double test2 = normal.dot(jvec);
        double test3 = normal.dot(kvec);

        Vec radial = null;
        if (test1*test1 <= test2*test2 && test1*test1 <= test3*test3) {
            radial = normal.cross(ivec).unitize().scale(radius);
        } else if (test2*test2 <= test1*test1 && test2*test2 <= test3*test3) {
            radial = normal.cross(jvec).unitize().scale(radius);
        } else {
            radial = normal.cross(kvec).unitize().scale(radius);
        }        

        circumference.addPoint(centre.add(radial));

        double angleIncrement = 0.01d;
        Matrix rotation = Matrix.getRotationMatrix(normal, angleIncrement);
        //System.out.println("rotation matrix:\n" + rotation);

        Vec nextRadial = null;
        for (double angle = angleIncrement; angle < 6.28; angle+=angleIncrement) {
            nextRadial = radial.multimat(rotation);
            circumference.addPoint(centre.add(nextRadial));
            radial = nextRadial;
            //System.out.println("radial: " +radial);
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        circumference.draw(g);
    }

    public Vec getCentre() {
        return centre;
    }

    public Polyline getDLineArc() {
        Polyline arc = new Polyline(model, color);

        ArrayList<Vec> points = circumference.getPoints();
        for (Vec point : points) {
            if (point.mag() < 1) {
                arc.addPoint(point);
            }
        }

        return arc;
    }
}
