package project1;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by paulpm on 03/02/16.
 */
public class WorldObject {
    protected double x;
    protected double y;

    protected WorldObject() {
        this.x = Utils.generateRandomX();
        this.y = Utils.generateRandomY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(HashMap<WorldObject, Double> distances) {}
    public void draw(Graphics g) {}
}
