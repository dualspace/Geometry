package inf.gui;

import java.awt.*;
import javax.swing.*;
import inf.model.*;
import inf.vec.*;

public class SceneCanvas extends JPanel {

    private Model model;

    public SceneCanvas(double a, double b, double c,
            double d, double e, double f) {
        Vec position = new Vec(a, b, c);
        Vec direction = new Vec(d, e, f);

        Camera camera = new Camera(position, direction, -0.7d, .7d, -.55d, .55d);
        model = new PoincareDisk(camera);
        //model = new Model(camera);

        MouseHandler mouseHandler = new MouseHandler(model, this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        constructModel();


    }

    private void constructModel() {
        /*
        Color sphereColor = new Color(250, 50, 100);
        Sphere sphere = new Sphere(model, sphereColor,
        new Vec(1.0,1.0,1.0), 4.5);
        model.addShape(sphere);
         */

        /*
        Color planeColor = new Color(20, 80, 20);
        Plane plane = new Plane(model, planeColor,
        new Vec(1.0,1.0,1.0), new Vec(1.0,1.0,4.0));
        model.addShape(plane);
         */

        /*
        Color coneColor = new Color(200, 50, 50);
        Cone cone = new Cone(model, coneColor,
        new Vec(1.0,1.0,1.0), new Vec(1.0,1.0,4.0), new Vec(7.0,7.0,3.0));
        model.addShape(cone);
         */
        //Desargue's theorem model...
        /*
        Color polyColor = new Color(210,130,20);

        Polyline line1 = new Polyline(model, polyColor);
        line1.addPoint(new Vec(-1.0,-1.0,-1.0));
        line1.addPoint(new Vec(1.0,1.0,1.0));

        Polyline line2 = new Polyline(model, polyColor);
        line2.addPoint(new Vec(-1.0,-1.0,-1.0));
        line2.addPoint(new Vec(0.0,0.0,1.0));
        
        Polyline line3 = new Polyline(model, polyColor);
        line3.addPoint(new Vec(-1.0,-1.0,-1.0));
        line3.addPoint(new Vec(1.0,0.0,0.0));

        Vec a1 = new Vec(.6,.6,.6);
        Vec a2 = new Vec(0.8,-.1,-.1);
        Vec a3 = new Vec(-.1,-.1,0.8);

        Vec b1 = new Vec(.45,.45,.45);
        Vec b2 = new Vec(0.0,-0.5,-0.5);
        Vec b3 = new Vec(-.3,-.3,0.4);

        Polyline line4 = new Polyline(model, new Color(200,10,10));
        line4.addPoint(a1);
        line4.addPoint(a2);
        line4.addPoint(a3);
        line4.addPoint(a1);
        
        Polyline line5 = new Polyline(model, new Color(200,10,10));
        line5.addPoint(b1);
        line5.addPoint(b2);
        line5.addPoint(b3);
        line5.addPoint(b1);

        Polyline line6 = new Polyline(model, new Color(30,30,150));
        line6.addPoint(a1);
        line6.addPoint(new Vec(.570297,.703960,.703960));
        line6.addPoint(b1);

        Polyline line7 = new Polyline(model, new Color(30,30,150));
        line7.addPoint(a1);
        line7.addPoint(new Vec(.997297,.997297,.486486));
        line7.addPoint(b1);

        Polyline line8 = new Polyline(model, new Color(30,30,150));
        line8.addPoint(a3);
        line8.addPoint(new Vec(-0.6,-0.1,1.3));
        line8.addPoint(b3);

        Polyline lineDesargues = new Polyline(model, new Color(0,0,0));
        lineDesargues.addPoint(new Vec(1.31675,1.21675,.323783));
        lineDesargues.addPoint(new Vec(-.919459,-.319459,1.462702));

        model.addShape(line1);
        model.addShape(line2);
        model.addShape(line3);
        model.addShape(line4);
        model.addShape(line5);
        model.addShape(line6);
        model.addShape(line7);
        model.addShape(line8);
        model.addShape(lineDesargues);
         */
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints renderHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(renderHints);

        setBackground(new Color(255, 255, 255));
        model.setWidth(getSize().width);
        model.setHeight(getSize().height);

        if (model instanceof PoincareDisk) {
            raytrace(g2d, getSize().width, getSize().height);
        }

        //model.draw(g2d);
    }

    public void raytrace(Graphics2D g, int width, int height) {
        Camera camera = model.getCamera();
        Vec campos = camera.getPosition();

        Vec x, y, cd;
        Vec k = new Vec(0, 0, 1);
        cd = camera.getDirection().unitize();
        x = (cd.cross(k)).unitize();
        y = (cd.cross(x)).unitize();

        double xmin = camera.getXmin();
        double ymin = camera.getYmin();
        double w1 = model.getWidth() / (camera.getXmax() - camera.getXmin());
        double h1 = model.getHeight() / (camera.getYmax() - camera.getYmin());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double xcoord = ((double) i) / w1 + xmin;
                double ycoord = ((double) j) / h1 + ymin;

                Vec pixel = campos.add(cd).add(x.scale(xcoord)).add(y.scale(ycoord));

                double lambda = campos.getElement(2) / (campos.getElement(2) - pixel.getElement(2));

                Vec xypoint = pixel.scale(lambda).add(campos.scale(1 - lambda));

                double rpolar = xypoint.mag();
                Vec inversionCentre1 = new Vec(1.5, 0, 0);
                double dlinedist1 = xypoint.add(inversionCentre1.negate()).mag();
                Vec inversionCentre2 = new Vec(-0.166666, 1.49071, 0);
                double dlinedist2 = xypoint.add(inversionCentre2.negate()).mag();

                if (rpolar < 1) {
                    /*
                    if (dlinedist1 < 1.11803) {
                    int red = ((int)(rpolar*1000))%255;
                    int blue = 0;
                    if (xypoint.getElement(0) == 0) {
                    blue = 0;
                    } else {
                    blue = ((int)(1000*Math.abs(Math.atan(xypoint.getElement(1)/xypoint.getElement(0)))))%255;
                    }
                    g.setColor(new Color(red,(red*40000)%255,blue));
                    //g.setColor(new Color(255,255,255));
                    } else {
                     */
                    double scaleFactor1 = 1.25 / (dlinedist1 * dlinedist1);
                    Vec inversion1 = inversionCentre1.add(xypoint.add(inversionCentre1.negate()).scale(scaleFactor1));
                    //double rpolarInversion1 = inversion1.mag();

                    double scaleFactor2 = 1.25 / (dlinedist2 * dlinedist2);
                    Vec inversion2 = inversionCentre2.add(inversion1.add(inversionCentre2.negate()).scale(scaleFactor2));
                    double rpolarInversion2 = inversion2.mag();

                    //if (inversion2.add(inversionCentre1.negate()).mag() < 1.11803) {

                        int red = ((int) (rpolarInversion2 * 1000)) % 255;
                        int blue = 0;
                        if (inversion2.getElement(0) == 0) {
                            blue = 0;
                        } else {
                            blue = ((int) (1000 * Math.abs(Math.atan(inversion2.getElement(1) / inversion2.getElement(0))))) % 255;
                        }


                        g.setColor(new Color(red, (red * 40000) % 255, blue));
                    //} else {
                    //    g.setColor(new Color(255,255,255));
                    //}
                        
                    //g.setColor(new Color(255,255,255));
                    //}

                    g.drawLine(i, j, i, j);
                }
            }
        }
    }
}

