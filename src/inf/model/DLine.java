package inf.model;

import java.awt.*;

public class DLine extends Shape {
    private Circle circle;
    private Polyline arc;

    public DLine(Model model, Circle circle) {
        super(model, circle.getColor());
        this.circle = circle;

        arc = circle.getDLineArc();
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        arc.draw(g);        
    }

    public Circle getCircle() {
        return circle;
    }
}
