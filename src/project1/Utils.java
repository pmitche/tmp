package project1;

/**
 * Created by paulpm on 03/02/16.
 */

public class Utils {

    public static double generateRandomX() {
        return Constants.WIDTH * Math.random();
    }

    public static double generateRandomY() {
        return Constants.HEIGHT * Math.random();
    }

    public static Vector2D generateInitialVelocity() {
        return new Vector2D(5 * Math.random() - 2.5, 5 * Math.random() - 2.5);
    }

    public static double distance(WorldObject o1, WorldObject o2) {
        double dX = o1.getX() - o2.getX();
        double dY = o1.getY() - o2.getY();
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static int modulo(int n, int m) {
        return (n < 0) ? (m - (Math.abs(n) % m)) % m : (n % m);
    }

    public static int wrap(int coordinate, int max) {
        coordinate %= max + 1;
        return (coordinate < 0) ? coordinate + max : coordinate;
    }
}
