package project1;

import com.sun.tools.internal.jxc.ap.Const;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by paulpm on 03/02/16.
 */
public class Boid extends WorldObject {
    protected Vector2D velocity = Utils.generateInitialVelocity();
    protected int speed = Constants.BOID_SPEED;
    protected int sep_radius = Constants.SEP_RADIUS;

    @Override
    public void update(HashMap<WorldObject, Double> distances) {
        Vector2D[] forces = {
                calculateSeparation(distances),
                calculateAlignment(distances),
                calculateCohesion(distances),
                calculateAvoidance(distances),
                calculateFlee(distances)
        };

        velocity = velocity.add(forces);
        limitVelocity();

        this.x = Utils.wrap((int) (this.x + velocity.getX()), Constants.WIDTH);
        this.y = Utils.wrap((int) (this.y + velocity.getY()), Constants.HEIGHT);
    }

    public void limitVelocity() {
        if (velocity.length() > speed) {
            velocity = velocity.scale(speed/velocity.length());
        }
    }

    protected Vector2D calculateSeparation(HashMap<WorldObject, Double> distances) {
        /*Vector2D separation = new Vector2D();
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid && !(object instanceof Predator)) {
                double distance = distances.get(object);
                if (distance > Constants.SEP_RADIUS) continue;

                separation.add(
                        x-object.getX() / Math.max(distance, 10) - 9,
                        y-object.getY() / Math.max(distance, 10) - 9
                );

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        return separation.scale(1 / boids);*/
        double sumX=0, sumY=0;
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid && !(object instanceof Predator)) {
                double distance = distances.get(object);
                if (distance > sep_radius) continue;

                sumX += (x-object.getX()) / (Math.max(distance, 10) - 9);
                sumY += (y-object.getY()) / (Math.max(distance, 10) - 9);

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        return new Vector2D(sumX/boids, sumY/boids).scale(Constants.BOID_SEPERATION_WEIGHT);
    }

    protected Vector2D calculateAlignment(HashMap<WorldObject, Double> distances) {
        /*Vector2D alignment = new Vector2D();
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid) {
                double distance = distances.get(object);
                if (distance > Constants.ALIGN_RADIUS) continue;

                alignment.add(
                        ((Boid) object).getVelocity().getX(),
                        ((Boid) object).getVelocity().getY()
                );

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        return alignment.scale(1 / boids);*/
        double sumX=0, sumY=0;
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid) {
                double distance = distances.get(object);
                if (distance > Constants.ALIGN_RADIUS) continue;

                sumX += ((Boid) object).getVelocity().getX();
                sumY += ((Boid) object).getVelocity().getY();

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        return new Vector2D(sumX/boids, sumY/boids).scale(Constants.ALIGNMENT_WEIGHT);
    }

    protected Vector2D calculateCohesion(HashMap<WorldObject, Double> distances) {
        /*Vector2D cohesion = new Vector2D();
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid && !(object instanceof Predator)) {
                double distance = distances.get(object);
                if (distance > Constants.COH_RADIUS) continue;

                cohesion.add(
                        object.getX(),
                        object.getY()
                );

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        cohesion = cohesion.scale(1 / boids);
        return cohesion.sub(x, y);*/
        double sumX=0, sumY=0;
        int boids = 0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Boid && !(object instanceof Predator)) {
                double distance = distances.get(object);
                if (distance > Constants.COH_RADIUS) continue;

                sumX += object.getX();
                sumY += object.getY();

                boids++;
            }
        }

        if (boids == 0) return new Vector2D();
        return new Vector2D(sumX/boids - x, sumY/boids - y).scale(Constants.COHESION_WEIGHT);
    }

    protected Vector2D calculateAvoidance(HashMap<WorldObject, Double> distances) {
        /*Vector2D avoidance = new Vector2D();

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Obstacle) {
                double distance = distances.get(object);
                if (distance > Constants.OBSTACLE_RADIUS) continue;

                avoidance.setX(avoidance.getX() + (x - object.getX()) / (Math.max(distance, 14) - 13));
                avoidance.setY(avoidance.getY() + (y - object.getY()) / (Math.max(distance, 14) - 13));
            }
        }

        return avoidance;*/
        double sumX=0, sumY=0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Obstacle) {
                double distance = distances.get(object);
                if (distance > Constants.OBSTACLE_RADIUS) continue;

                sumX += (x - object.getX()) / (Math.max(distance, 14) - 13);
                sumY += (y - object.getY()) / (Math.max(distance, 14) - 13);
            }
        }

        return new Vector2D(sumX, sumY).scale(Constants.AVOIDANCE_WEIGHT);
    }

    protected Vector2D calculateFlee(HashMap<WorldObject, Double> distances) {
        /*Vector2D flee = new Vector2D();

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Predator) {
                double distance = distances.get(object);
                if (distance > Constants.PREDATOR_RADIUS) continue;

                flee.setX(flee.getX() + (x-object.getX()) / distance);
                flee.setY(flee.getY() + (y-object.getY()) / distance);
            }
        }

        return flee;*/
        double sumX=0, sumY=0;

        for (WorldObject object : distances.keySet()) {
            if (object instanceof Predator) {
                double distance = distances.get(object);
                if (distance > Constants.PREDATOR_RADIUS) continue;

                sumX += (x-object.getX()) / distance;
                sumY += (y-object.getY()) / distance;
            }
        }

        return new Vector2D(sumX, sumY).scale(Constants.FLEE_WEIGHT);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
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
