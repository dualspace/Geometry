package inf.model;

import java.awt.*;
import java.util.*;

public class Shape {
    protected Model model;
    protected Color color;

    public Shape(Model model) {
        this.model = model;
        color = new Color(100,100,100);
    }

    public Shape(Model model, Color color) {
        this.model = model;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g) {
        //to be overridden. 
    }

    public Model getModel() {
        return model;
    }

    public Color getColor() {
        return color;
    }
}
