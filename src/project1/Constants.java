package project1;

/**
 * Created by paulpm on 03/02/16.
 */
public final class Constants {
    public static final int WIDTH = 810;
    public static final int HEIGHT = 680;

    public static final int AGENT_SIZE = 14;
    public static final int OBSTACLE_SIZE = 25;

    public static final int NUMBER_OF_BOIDS = 200;

    public static double BOID_SEPERATION_WEIGHT = 0.4;
    public static final double PREDATOR_SEPARATION_WEIGHT = -20;
    public static double ALIGNMENT_WEIGHT = 0.1;
    public static double COHESION_WEIGHT = 0.008;
    public static final double AVOIDANCE_WEIGHT = 0.5;
    public static final double FLEE_WEIGHT = 5;
    public static final double PREDATOR_FLEE_WEIGHT = 50;

    public static final int SEP_RADIUS = 20;
    public static final int PREDATOR_SEP_RADIUS = 80;
    public static final double ALIGN_RADIUS = 50;
    public static final double COH_RADIUS = 55;
    public static final double OBSTACLE_RADIUS = 30;
    public static final double PREDATOR_RADIUS = 60;

    public static final int BOID_SPEED = 5;
    public static final int PREDATOR_SPEED = 6;
}
