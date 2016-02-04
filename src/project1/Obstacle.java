package project1;

import java.awt.*;

/**
 * Created by paulpm on 04/02/16.
 */
public class Obstacle extends WorldObject {

    public void draw(Graphics g) {
        g.setColor(Color.gray);
        g.fillOval(
                (int) getX() - Constants.OBSTACLE_SIZE/2,
                (int) getY() - Constants.OBSTACLE_SIZE/2,
                Constants.OBSTACLE_SIZE,
                Constants.OBSTACLE_SIZE
        );
    }
}
