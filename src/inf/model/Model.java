package inf.model;

import java.awt.*;
import java.util.*;
import inf.vec.*;

public class Model {
    protected Camera camera;
    protected ArrayList<Shape> shapes;
    protected int width;
    protected int height;

    public Model(Camera camera) {
        this.camera = camera;
        shapes = new ArrayList<Shape>();
    }
    
    public void draw(Graphics2D g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public Camera getCamera() {
        return camera;
    }

    public Vec intersection(Vec point) {
        double a, b, lambda;
        Vec n = camera.getDirection();
        a = camera.getPosition().dot(n);
        b = point.dot(n);
        lambda = 1 / (b - a);
        Vec result;
        result = (camera.getPosition().scale(1 - lambda)).add((point.scale(lambda)));
        return result;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
