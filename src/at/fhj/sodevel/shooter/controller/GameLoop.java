package at.fhj.sodevel.shooter.controller;

import at.fhj.sodevel.shooter.model.SpaceShip;
import at.fhj.sodevel.shooter.view.GameWorld;
import at.fhj.sodevel.shooter.view.ScorePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameLoop implements KeyListener, Runnable {
    private GameWorld world;
    private ScorePanel scorePanel;
    private boolean isRunning;
    private Thread runner;

    public GameWorld getWorld() {
        return world;
    }

    public void setWorld(GameWorld world) {
        this.world = world;
        world.addKeyListener(this);

        this.scorePanel = scorePanel;

        isRunning = true;
        runner = new Thread(this);
        runner.start();
    }

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 37) {
            SpaceShip s = world.getShip();
            if (s.getX() > 50) {
                s.moveX(-25);
            }
        }

        if (key == 40) {
            SpaceShip s = world.getShip();
            if (s.getY() < 500) {
                s.moveY(25);
            }
        }

        if (key == 39) {
            SpaceShip s = world.getShip();
            if (s.getX() < 700) {
                s.moveX(25);
            }
        }

        if (key == 38) {
            SpaceShip s = world.getShip();
            if (s.getY() > 50) {
                s.moveY(-25);
            }
        }

        if (key == 32) {
            world.fireMissile();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //Solange Programm lauft wird welt gezeichnet
        while (isRunning) {
            try {
                Thread.sleep(50);

                //if player has no lives left
                if (world.getPlayer().getLives() <= 0) {
                    isRunning = false;
                    world.gameOver();
                    Thread.sleep(50);
                    runner.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.repaint();
        }
    }
}