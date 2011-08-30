/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;

public class Plane extends Shape {
    private Vec point;
    private Vec normal;

    private ArrayList<Line> gridu;
    private ArrayList<Line> gridv;

    public Plane(Model model, Color color, Vec point, Vec normal) {
        super(model, color);

        this.point = point;
        this.normal = normal.unitize();

        gridu = new ArrayList<Line>();
        gridv = new ArrayList<Line>();

        constructPlane();
    }

    private void constructPlane() {
        double size = 18.0;
        double increment = 0.5;

        Vec ivec = new Vec(1.0,0.0,0.0);
        Vec jvec = new Vec(0.0,1.0,0.0);

        double test1 = normal.dot(ivec);
        double test2 = normal.dot(jvec);

        Vec udirection = null;
        if (test1*test1 > test2*test2) {
            udirection = normal.cross(ivec).unitize();
        } else {
            udirection = normal.cross(jvec).unitize();
        }
        Vec vdirection = udirection.cross(normal);

        for (double distance = -1*size/2; distance<size/2; distance+=increment) {
            Vec ustart = point.add(udirection.scale(distance)).add(vdirection.scale(size/2));
            Vec uend = point.add(udirection.scale(distance)).add(vdirection.scale(size/2).negate());

            gridu.add(new Line(model, color, ustart, uend));

            Vec vstart = point.add(vdirection.scale(distance)).add(udirection.scale(size/2));
            Vec vend = point.add(vdirection.scale(distance)).add(udirection.scale(size/2).negate());

            gridv.add(new Line(model, color, vstart, vend));
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        for (Line line : gridu) {
            line.draw(g);
        }
        for (Line line : gridv) {
            line.draw(g);
        }
    }
}
