package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.GameLoop;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GameLoop loop;
    private GameWorld world;
    private ScorePanel scorePanel;

    public GameWindow() {
        this.setTitle("Shooter");
        this.setSize(800, 650);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        scorePanel = new ScorePanel();
        this.add(scorePanel, BorderLayout.PAGE_START);

        world = new GameWorld(scorePanel);
        this.add(world, BorderLayout.CENTER);

        loop = new GameLoop();
        loop.setWorld(world);
        loop.setScorePanel(scorePanel);

        world.addKeyListener(loop);
    }
}