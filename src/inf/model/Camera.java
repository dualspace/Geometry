/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf.model;

import inf.vec.*;


public class Camera {
    private Vec position;
    private Vec direction;

    //array containing (in order): xmin, xmax, ymin, ymax.
    private double [] screenLimits;

    public Camera(Vec position, Vec direction, double xmin, double xmax,
            double ymin, double ymax) {
        this.position = position;
        this.direction = direction.unitize();

        screenLimits = new double[4];
        screenLimits[0] = xmin;
        screenLimits[1] = xmax;
        screenLimits[2] = ymin;
        screenLimits[3] = ymax;
    }

    public void setPosition(Vec position) {
        this.position = position;
    }

    public Vec getPosition() {
        return position;
    }

    public void setDirection(Vec direction) {
        this.direction = direction.unitize();
    }

    public Vec getDirection() {
        return direction;
    }

    public double getXmin() {
        return screenLimits[0];
    }

    public double getXmax() {
        return screenLimits[1];
    }

    public double getYmin() {
        return screenLimits[2];
    }

    public double getYmax() {
        return screenLimits[3];
    }

    public void setXmin(double xmin) {
        screenLimits[0] = xmin;
    }

    public void setXmax(double xmax) {
        screenLimits[1] = xmax;
    }

    public void setYmin(double ymin) {
        screenLimits[2] = ymin;
    }

    public void setYmax(double ymax) {
        screenLimits[3] = ymax;
    }
}
