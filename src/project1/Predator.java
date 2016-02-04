package project1;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by paulpm on 03/02/16.
 */
public class Predator extends Boid {

    public Predator() {
        speed = Constants.PREDATOR_SPEED;
        sep_radius = Constants.PREDATOR_SEP_RADIUS;
    }

    @Override
    public void update(HashMap<WorldObject, Double> distances) {
        Vector2D[] forces = {
                calculateSeparation(distances).scale(Constants.PREDATOR_SEPARATION_WEIGHT),
                calculateFlee(distances).scale(Constants.PREDATOR_FLEE_WEIGHT)
        };

        velocity = velocity.add(forces);
        limitVelocity();

        this.x = Utils.wrap((int) (this.x + velocity.getX()), Constants.WIDTH);
        this.y = Utils.wrap((int) (this.y + velocity.getY()), Constants.HEIGHT);
    }


    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(
                (int) getX() - Constants.AGENT_SIZE/2,
                (int) getY() - Constants.AGENT_SIZE/2,
                Constants.AGENT_SIZE,
                Constants.AGENT_SIZE
        );

        g.setColor(Color.black);
        g.drawLine(
                (int) getX(),
                (int) getY(),
                (int) (getX() + velocity.getX()),
                (int) (getY() + velocity.getY())
        );
    }
}
