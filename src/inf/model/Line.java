package inf.model;

import java.awt.*;

import inf.vec.*;

//A 3D line segment object which can draw itself with prespective using
//the camera position and direction from the SceneCanvas class.
public class Line extends Shape {

    private Vec start;
    private Vec end;

    public Line(Model model, double a, double b, double c,
            double d, double e, double f, Color color) {
        super(model, color);

        start = new Vec(a, b, c);
        end = new Vec(d, e, f);
    }

    public Line(Model model, Color color, Vec a, Vec z) {
        super(model, color);
        start = a;
        end = z;
    }

    public void setStartEl(int i, double entry) {
        if ((i >= 0) && (i < 3)) {
            start.setElement(i, entry);
        }
    }

    public void setEndEl(int i, double entry) {
        if ((i >= 0) && (i < 3)) {
            end.setElement(i, entry);
        }
    }

    public double getStartEl(int i) {
        return start.getElement(i);
    }

    public Vec getStart() {
        return start;
    }

    public double getEndEl(int i) {
        return end.getElement(i);
    }

    public Vec getEnd() {
        return end;
    }

    public void setStart(Vec ns) {
        start = ns;
    }

    public void setEnd(Vec ne) {
        end = ne;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        Camera camera = model.getCamera();

        Vec x, y, cd;
        Vec k = new Vec(0, 0, 1);
        cd = camera.getDirection().unitize();
        x = (cd.cross(k)).unitize();
        y = (cd.cross(x)).unitize();

        Vec v1, v2;
        v1 = model.intersection(start);
        v1 = v1.add(cd.negate()).add(camera.getPosition().negate());
        v2 = model.intersection(end);
        v2 = v2.add(cd.negate()).add(camera.getPosition().negate());

        double x1, y1, x2, y2;
        x1 = v1.dot(x);
        x2 = v2.dot(x);
        y1 = v1.dot(y);
        y2 = v2.dot(y);

        int x1pixel, y1pixel, x2pixel, y2pixel;
        double xmin = camera.getXmin();
        double ymin = camera.getYmin();
        double w1 = model.getWidth() / (camera.getXmax() - camera.getXmin());
        double h1 = model.getHeight() / (camera.getYmax() - camera.getYmin());
        x1pixel = (int) ((x1 - xmin) * w1);
        x2pixel = (int) ((x2 - xmin) * w1);
        y1pixel = (int) ((y1 - ymin) * h1);
        y2pixel = (int) ((y2 - ymin) * h1);

        g.drawLine(x1pixel, y1pixel, x2pixel, y2pixel);
    }
}
