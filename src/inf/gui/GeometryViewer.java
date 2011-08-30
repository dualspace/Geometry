package inf.gui;

import inf.model.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import java.util.*;
import inf.vec.*;

public class GeometryViewer extends JFrame {

    private GeometryMediator mediator;
    
    public GeometryViewer() {
        super("Geometry");

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                Window w = e.getWindow();
                w.setVisible(false);
                w.dispose();
                //Command.getCommandHistory().printHistory();
                System.exit(0);
            }
        });

        initialiseMediator();
        initialiseFrame();

    }

    public void initialiseMediator() {
        mediator = new GeometryMediator();

        //SceneCanvas scene = new SceneCanvas(25, 11, 16, -25, -11, -16);
        SceneCanvas scene = new SceneCanvas(2, 2, 2, -2, -2, -2);
        scene.setBackground(new Color(255, 255, 255));

        mediator.setScene(scene);
    }

    public void initialiseFrame() {
        JDesktopPane desktop = new JDesktopPane();
        desktop.setDesktopManager(new DefaultDesktopManager());
        mediator.setDesktop(desktop);

        setContentPane(desktop);

        //===============GUI layout
        //display Panel layout
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 1));
        displayPanel.add(mediator.getScene());
        mediator.setDisplayPanel(displayPanel);

        //applet layout
        setLayout(new BorderLayout());
        add(displayPanel, "Center");

        setSize(950, 725);
        setVisible(true);
    }

    public void start() {
        mediator.getScene().repaint();
    }

    public void paint(Graphics g) {
        mediator.getScene().paint(g);
    }

    public static void main(String [] args) {
        GeometryViewer viewer = new GeometryViewer();
    }
}

