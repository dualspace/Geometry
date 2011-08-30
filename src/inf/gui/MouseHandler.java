package inf.gui;

import inf.vec.*;
import inf.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MouseHandler extends MouseInputAdapter {

    private Model model;
    private JPanel scene;

    public MouseHandler(Model model, JPanel scene) {
        this.model = model;
        this.scene = scene;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Vec r = locateClickedPosition((double) e.getX(), (double) e.getY());

            Color circleColor = new Color(200, 50, 50);
            Circle circle = new Circle(model, circleColor, r, new Vec(0.0, 0.0, 1.0), 0.01);
            //model.addShape(circle);

            if (model instanceof PoincareDisk) {
                ((PoincareDisk)model).addPoint(circle);
            }

            scene.repaint();
        }
    }

    private Vec locateClickedPosition(double mousex, double mousey) {
        Camera camera = model.getCamera();

        double xmin = camera.getXmin();
        double xmax = camera.getXmax();
        double ymin = camera.getYmin();
        double ymax = camera.getYmax();

        double xScaleFactor = xmin + mousex * (xmax - xmin) / (model.getWidth());
        double yScaleFactor = ymin + mousey * (ymax - ymin) / (model.getHeight());

        Vec x, y, cd;
        Vec k = new Vec(0, 0, 1);
        cd = camera.getDirection().unitize();
        x = (cd.cross(k)).unitize();
        y = (cd.cross(x)).unitize();

        Vec v = camera.getPosition().add(cd).add(x.scale(xScaleFactor)).add(y.scale(yScaleFactor));

        double lambda = camera.getPosition().getElement(2) /
                (camera.getPosition().getElement(2) - v.getElement(2));

        return camera.getPosition().scale(1 - lambda).add(v.scale(lambda));
    }

    public void mouseDragged(MouseEvent e) {
        int mousex = e.getX();
        int mousey = e.getY();

        double camdist = model.getCamera().getPosition().mag();
        double camtheta = mousey * 3.14159f / 321;
        double camphi = mousex * 6.283f / 434;

        Vec polar = new Vec(camdist, camtheta, camphi);
        model.getCamera().setPosition(polar.convertToCartesian());
        model.getCamera().setDirection(model.getCamera().getPosition().negate());

        scene.repaint();
    }
}
