package inf.model;

import java.awt.*;
import inf.vec.*;
import java.util.*;


public class Polyline extends Shape {

    private ArrayList<Vec> points;

    public Polyline(Model model) {
        super(model);
        points = new ArrayList<Vec>();
    }

    public Polyline(Model model, Color color) {
        super(model, color);
        points = new ArrayList<Vec>();
    }

    public void addPoint(Vec point) {
        points.add(point);
    }

    public void draw(Graphics2D g) {
        g.setColor(color);

        Camera camera = model.getCamera();

        Vec x, y, cd;
        Vec k = new Vec(0, 0, 1);
        cd = camera.getDirection().unitize();
        x = (cd.cross(k)).unitize();
        y = (cd.cross(x)).unitize();

        ArrayList<Vec> intersections = new ArrayList<Vec>();
        for (Vec point : points) {
            Vec temp = model.intersection(point);
            intersections.add(temp.add(cd.negate()).add(camera.getPosition().negate()));
        }

        double xmin = camera.getXmin();
        double ymin = camera.getYmin();
        double w1 = model.getWidth() / (camera.getXmax() - camera.getXmin());
        double h1 = model.getHeight() / (camera.getYmax() - camera.getYmin());

        int [] xvalues = new int[points.size()];
        int [] yvalues = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            double xtemp = intersections.get(i).dot(x);
            double ytemp = intersections.get(i).dot(y);

            xvalues[i] = (int)((xtemp - xmin) * w1);
            yvalues[i] = (int)((ytemp - ymin) * h1);
        }

        g.drawPolyline(xvalues, yvalues, points.size());
    }

    public ArrayList<Vec> getPoints() {
        return points;
    }

}
