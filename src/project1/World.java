package project1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by paulpm on 04/02/16.
 */
public class World extends JPanel {
    protected static World world;
    private final ArrayList<WorldObject> objects = new ArrayList<>();

    private World() {
        setBackground(Color.black);
        for (int i = 0; i < Constants.NUMBER_OF_BOIDS; i++) {
            Boid boid = new Boid();
            addEntity(boid);
        }
    }

    public static World createWorld() {
        world = new World();
        return world;
    }

    public static World getWorld() {
        return world;
    }

    public void run() {
        WorldObject objectsCopy[];
        while (true) {
            long time = System.currentTimeMillis();
            objectsCopy = objects.toArray(new WorldObject[objects.size()]);

            HashMap<WorldObject, HashMap<WorldObject, Double>> distanceMatrix = new HashMap<>();
            for(WorldObject object: objectsCopy) distanceMatrix.put(object, new HashMap<>());

            for(int i=0; i<objectsCopy.length; i++) {
                for(int j=i+1; j<objectsCopy.length; j++) {
                    double distance = Utils.distance(objectsCopy[i], objectsCopy[j]);
                    distanceMatrix.get(objectsCopy[i]).put(objectsCopy[j], distance);
                    distanceMatrix.get(objectsCopy[j]).put(objectsCopy[i], distance);
                }
            }

            Arrays.stream(objectsCopy).parallel().forEach(b -> b.update(distanceMatrix.get(b)));

            try {
                long sleepTime = 40 - (System.currentTimeMillis()-time);
                if(sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(WorldObject object : objects)
            object.draw(g);
    }

    private synchronized void addEntity(WorldObject object) {
        objects.add(object);
    }


    public void addObstacle() {
        addEntity(new Obstacle());
    }

    public synchronized void removeObstacles() {
        for(int i=objects.size()-1; i>=0; i--) {
            if(objects.get(i) instanceof Obstacle) objects.remove(i);
        }
    }

    public void addPredator() {
        addEntity(new Predator());
    }

    public synchronized void removePredators() {
        for(int i=objects.size()-1; i>=0; i--) {
            if(objects.get(i) instanceof Predator) objects.remove(i);
        }
    }
}
