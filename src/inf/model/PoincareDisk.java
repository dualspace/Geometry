package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;

public class PoincareDisk extends Model {

    private ArrayList<Circle> points;
    private ArrayList<DLine> dlines;

    public PoincareDisk(Camera camera) {
        super(camera);

        points = new ArrayList<Circle>();
        dlines = new ArrayList<DLine>();

        /*
        Color gridColor = new Color(248,238,148);
        for (int i = 0; i < 25; i++) {
            double temp1 = ((i % 5) - 2) * .5;
            double temp2 = ((i / 5) - 2) * .5;
            addShape(new Line(this, 1, temp1, temp2, -1, temp1, temp2, gridColor));
            addShape(new Line(this, temp1, 1, temp2, temp1, -1, temp2, gridColor));
            addShape(new Line(this, temp1, temp2, 1, temp1, temp2, -1, gridColor));
        }
         
        
        Color axisColor = new Color(195, 214, 225);
        addShape(new Line(this, -1, 0, 0, 0, 0, 0, axisColor));
        addShape(new Line(this, 0, -1, 0, 0, 0, 0, axisColor));
        addShape(new Line(this, 0, 0, -1, 0, 0, 0, axisColor));
        addShape(new Line(this, 0, 0, 0, 1, 0, 0, axisColor));
        addShape(new Line(this, 0, 0, 0, 0, 1, 0, axisColor));
        addShape(new Line(this, 0, 0, 0, 0, 0, 1, axisColor));
        */


        Color circleColor = new Color(200, 50, 50);
        Circle unitCircle = new Circle(this, circleColor,
                new Vec(0.0, 0.0, 0.0), new Vec(0.0, 0.0, 1.0), 1.0);
        addShape(unitCircle);

        //figure parallels
        //DLine dline1 = addDLine(new Vec(0.8,0.1,0), new Vec(0.2,0.7,0), new Color(234,138,0));
        //Vec a = new Vec(-0.3,0.2,0);
        //DLine dline2 = dropPerpendicular(a, dline1, new Color(72,118,255));
        //dropParallel(a, dline1, true);
        //dropParallel(a, dline1, false);
        //addDLine(a, new Vec(-3.0,0.3,0), new Color(13,139,19));

        //figure angleofpara1
        //DLine dline1 = addDLine(new Vec(-0.3,0.55,0), new Vec(0,1.0,0));
        //Vec a = new Vec(0.6,0,0);
        //dropPerpendicular(a, dline1);
        //dropParallel(a, dline1, true);

        //figure angleofpara2
        //DLine dline1 = addDLine(new Vec(-0.3,0.55,0), new Vec(0.6,0,0));
        //Vec a = new Vec(0.0,-1.0,0);
        //DLine dline2 = dropPerpendicular(a, dline1);
        //dropParallel(new Vec(0.6,0,0), dline2, false);

        //figure angleofpara3
        //DLine dline1 = addDLine(new Vec(0,0.5,0), new Vec(1.0,0,0));

        //figure horocycle
        //Color sphereColor = new Color(139,171,239);
        //Sphere sphere = new Sphere(this, sphereColor, new Vec(0,0,0), 1.0);
        //addShape(sphere);
        //Color sphereColor2 = new Color(234,181,12);
        //Sphere sphere2 = new Sphere(this, sphereColor2, new Vec(0.3,0,0), 0.7);
        //addShape(sphere2);
        //Color horocycleColor = new Color(0,0,0);
        //Circle horocycle = new Circle(this, horocycleColor,
        //        new Vec(0.3, 0.0, 0.0), new Vec(0.0, 0.0, 1.0), 0.7);
        //addShape(horocycle);

        //metric
        /*
        Color contourColor = new Color(139,171,239);
        for (double i = 0.02; i < 1.0; i+= .02) {
            Circle contour = new Circle(this, contourColor,
                new Vec(0.0, 0.0, 0.5*Math.log((1+i)/(1-i))), new Vec(0.0, 0.0, 1.0), i);
            addShape(contour);
        }
        double temp = 0.02;
        for (double i = 0.02; i < 1.0; i+= .02) {
            double temp2 = 0.5*Math.log((1+i)/(1-i));
            addShape(new Line(this, i-.02, 0, temp, i, 0, temp2, new Color(0,0,0)));
            temp = temp2;
        }
        addShape(new Line(this, 0, 0, 0, 0.8, 0, 0, new Color(0,0,0)));
        addShape(new Line(this, 0.8, 0, 0, 0.8, 0, 0.5*Math.log((1+.8)/(1-.8)), new Color(0,0,0)));
        */

        //off centre metric
        /*
        Color contourColor = new Color(139,171,239);
        double centre = 0.7;
        double radius = 0.0;
        double hyperbolicCentre = 0.5*Math.log((1+centre)/(1-centre));
        for (double i = 0.02; i < 1.5; i+= .04) {
            double x1 = hyperbolicCentre - i;
            double x2 = hyperbolicCentre + i;
            double temp = centre;
            centre = 0.5*((Math.exp(x1)-Math.exp(-x1))/(Math.exp(x1)+Math.exp(-x1)) +
                          (Math.exp(x2)-Math.exp(-x2))/(Math.exp(x2)+Math.exp(-x2)));
            radius = 0.5*((Math.exp(x2)-Math.exp(-x2))/(Math.exp(x2)+Math.exp(-x2)) -
                          (Math.exp(x1)-Math.exp(-x1))/(Math.exp(x1)+Math.exp(-x1)));
            if (i > .5 && i < .54) {
                contourColor = new Color(0,0,0);
                addShape(new Line(this, centre-radius, 0, i, centre, 0, i, new Color(0,0,0)));
                addShape(new Line(this, centre-radius, 0, 0, centre-radius, 0, i, new Color(0,0,0)));
            }
            Circle contour = new Circle(this, contourColor,
                new Vec(centre, 0.0, i), new Vec(0.0, 0.0, 1.0), radius);
            addShape(contour);
            if (i > .5) {
                contourColor = new Color(139,171,239);
            }
            addShape(new Line(this, temp, 0, i-0.04, centre, 0, i, new Color(0,0,0)));
        }

        addShape(new Line(this, 0, 0, 0, 0.7, 0, 0, new Color(0,0,0)));
        */

        //Stereographic projection...
        
        Color sphereColor = new Color(191,209,241);
        Sphere sphere = new Sphere(this, sphereColor, new Vec(0,0,0), 1.0);
        addShape(sphere);
        addDLine(new Vec(0.4,-0.1,0), new Vec(0.4,0.1,0), new Color(234,138,0));
        Color projColor = new Color(0,0,0);
        addShape(new Line(this, 0, 0, 1, .68376, -.17094, -.7094, projColor));
        addShape(new Line(this, .68376, -.17094, -.7094, .68376, -.17094, 0, projColor));
        addShape(new Line(this, 0, 0, 1, .68376, .17094, -.7094, projColor));
        addShape(new Line(this, .68376, .17094, -.7094, .68376, .17094, 0, projColor));
        addShape(new Line(this, 0, 0, 1, .68376, .7297, 0, projColor));
        addShape(new Line(this, 0, 0, 1, .68376, -.7297, 0, projColor));
        addShape(new Line(this, .68376, -.7297, 0, .68376, .7297, 0, new Color(0,255,0)));
        //addShape(unitCircle);
        

        //figure for Mobius raytracing...
        //DLine dline1 = addDLine(new Vec(0.8,0.871779,0), new Vec(0.8,-0.871779,0), new Color(0,0,0));
        //DLine dline2 = addDLine(new Vec(-0.8,0.871779,0), new Vec(-0.8,-0.871779,0), new Color(0,0,0));
        //DLine dline2 = addDLine(new Vec(.666666,0.74535,0), new Vec(-0.81481, 0.57972, 0), new Color(0,0,0));
        //DLine dline2 = addDLine(new Vec(0.871179,0.8,0), new Vec(-0.871779,0.8,0), new Color(0,0,0));

        //figure for pole and polars...
        /*
        addShape(new Line(this, 0, 1, 0, 2, 1, 0, new Color(0,0,0)));
        addShape(new Line(this, 0.8, -0.6, 0, 0, 1, 0, new Color(0,0,0)));
        addShape(new Line(this, 0.8, -.6, 0, 2, 1, 0, new Color(0,0,0)));
        addShape(new Line(this, 2, 0, 0, 0.5, 0.86602, 0, new Color(139,171,239)));
        addShape(new Line(this, 0.5, -0.86602, 0, 0.5, 0.86602, 0, new Color(139,171,239)));
        addShape(new Line(this, 2, 0, 0, 0.5, -0.86602, 0, new Color(139,171,239)));
        addShape(new Line(this, 2, -0.5, 0, 0.25849, -.966012, 0, new Color(234,138,0)));
        addShape(new Line(this, 2, -0.5, 0, 0.68268, .730717, 0, new Color(234,138,0)));
        addShape(new Line(this, 0.25849, -.966012, 0, 0.68268, .730717, 0, new Color(234,138,0)));
        addShape(new Line(this, 2, -2, 0, 2, 2, 0, new Color(25,140,30)));
        */
    }

    public void addPoint(Circle point) {
        points.add(point);
        updateDLines();
    }

    private void updateDLines() {
        // Make sure each point is connected to every other with a d-line.
        if (points.size() == 2) {
            Circle newestPoint = points.get(points.size() - 1);
            for (int i = 0; i < points.size() - 1; i++) {
                addDLine(points.get(i).getCentre(), newestPoint.getCentre());
            }
        }

        if (points.size() == 3) {
            //dropPerpendicular(points.get(2).getCentre(), dlines.get(0));
            dropParallel(points.get(2).getCentre(), dlines.get(0), true);
        }

        if (points.size() == 4) {
            //dropPerpendicular(points.get(2).getCentre(), dlines.get(0));
            dropParallel(points.get(3).getCentre(), dlines.get(1), true);
        }

        if (points.size() > 4) {
            for (Circle point : points) {
                shapes.remove(point);
            }

            for (DLine dline : dlines) {
                shapes.remove(dline);
            }

            points = new ArrayList<Circle>();
            dlines = new ArrayList<DLine>();            
        }
    }

    private DLine addDLine(Vec point1, Vec point2) {
        return addDLine(point1, point2, new Color(0,0,0));
    }

    private DLine addDLine(Vec point1, Vec point2, Color color) {
        double x1 = point1.getElement(0);
        double y1 = point1.getElement(1);

        double alpha = 2 * x1;
        double beta = 2 * y1;
        double p = x1 * x1 + y1 * y1 + 1;

        double x2 = point2.getElement(0);
        double y2 = point2.getElement(1);

        double gamma = 2 * x2;
        double delta = 2 * y2;
        double q = x2 * x2 + y2 * y2 + 1;

        double determinant = 1.0 / (alpha * delta - beta * gamma);

        double a = determinant * (delta * p - beta * q);
        double b = determinant * (alpha * q - gamma * p);

        Vec centre = new Vec(a, b, 0);
        double radius = Math.sqrt( a * a + b * b - 1);

        System.out.println("Adding d-line: a = " + a + ", b = " + b);
        
        Circle dlineCircle = new Circle(this, color, centre, new Vec(0.0, 0.0, 1.0), radius);
        DLine dline = new DLine(this, dlineCircle);
        dlines.add(dline);
        addShape(dline);

        return dline;
    }

    private DLine dropPerpendicular(Vec point, DLine dline) {
        return dropPerpendicular(point, dline, new Color(0,0,0));
    }

    private DLine dropPerpendicular(Vec point, DLine dline, Color color) {
        double x = point.getElement(0);
        double y = point.getElement(1);

        double alpha = dline.getCircle().getCentre().getElement(0);
        double beta = dline.getCircle().getCentre().getElement(1);
        double gamma = 2 * x;
        double delta = 2 * y;
        double q = x * x + y * y + 1;

        double determinant = 1.0 / (alpha * delta - beta * gamma);

        double a = determinant * (delta - beta * q);
        double b = determinant * (alpha * q - gamma);

        Vec centre = new Vec(a, b, 0);
        double radius = Math.sqrt( a * a + b * b - 1);

        System.out.println("Adding d-line: a = " + a + ", b = " + b);
        
        Circle dlineCircle = new Circle(this, color, centre, new Vec(0.0, 0.0, 1.0), radius);
        DLine perpendicular = new DLine(this, dlineCircle);
        dlines.add(perpendicular);
        addShape(perpendicular);
        return perpendicular;
    }
    
    private DLine dropParallel(Vec point, DLine dline, boolean isToFirstSide) {
        double x, y, a, b, p, q, r;

        a = dline.getCircle().getCentre().getElement(0);
        b = dline.getCircle().getCentre().getElement(1);
        
        p = 1 + (a*a)/(b*b);
        q = -2 * a/(b*b);
        r = 1/(b*b) - 1;

        if (isToFirstSide) {
            x = (-q + Math.sqrt(q*q - 4*p*r))/(2*p);
        } else {
            x = (-q - Math.sqrt(q*q - 4*p*r))/(2*p);
        }
        
        y = (1-a*x)/b;
        
        Vec boundaryPoint = new Vec(x,y,0);

        return addDLine(point, boundaryPoint);
    }
}
