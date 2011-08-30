/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf.gui;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;

/**
 *
 * @author paul
 */
public class GeometryMediator {
    private JDesktopPane desktop;
    private JPanel displayPanel;
    private SceneCanvas scene;

    public GeometryMediator() {

    }

    public void setDisplayPanel(JPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public JPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setScene(SceneCanvas scene) {
        this.scene = scene;
    }

    public SceneCanvas getScene() {
        return scene;
    }

    public void setDesktop(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

}
