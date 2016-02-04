package project1;

import javax.swing.*;
import java.awt.*;

/**
 * Created by paulpm on 04/02/16.
 */
public class Main {
    public static final int APP_WIDTH = 980, APP_HEIGHT = 680;

    public Main() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{Constants.WIDTH, APP_WIDTH-Constants.WIDTH};
        gridBagLayout.rowHeights = new int[]{APP_HEIGHT};


        World world = World.getWorld();
        GridBagConstraints gbc_world = new GridBagConstraints();
        gbc_world.fill = GridBagConstraints.BOTH;
        gbc_world.gridx = 0;
        gbc_world.gridy = 0;
        gbc_world.weightx = 1;
        gbc_world.weighty = 1;


        Sidebar sidebar = new Sidebar();
        GridBagConstraints gbc_sidebar = new GridBagConstraints();
        gbc_sidebar.fill = GridBagConstraints.BOTH;
        gbc_sidebar.gridx = 1;
        gbc_sidebar.gridy = 0;
        gbc_sidebar.weightx = 1;
        gbc_sidebar.weighty = 1;


        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Boids");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().add(world);
        //mainFrame.getContentPane().setLayout(gridBagLayout);
        //mainFrame.getContentPane().add(sidebar, gbc_sidebar);
        //mainFrame.getContentPane().add(world, gbc_world);
        //mainFrame.add(simulation);
        mainFrame.pack();
        mainFrame.setSize(Constants.WIDTH, Constants.HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setFocusable(true);
    }

    public static void main(String[] args) {
        World.createWorld();
        new Main();
        World.getWorld().run();
    }
}
