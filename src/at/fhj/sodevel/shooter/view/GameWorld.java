package at.fhj.sodevel.shooter.view;

import at.fhj.sodevel.shooter.controller.Sound;
import at.fhj.sodevel.shooter.model.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class GameWorld extends JPanel {
    private SpaceShip ship;
    private Player player;
    private ArrayList<Missile> missiles;
    private ArrayList<Alien> aliens;
    private ScorePanel scorePanel;

    private Timer timer = new Timer();
    private Random r = new Random();

    private Image backgroundImage;
    private ImageIcon heartIcon;
    private BufferedImage shipImage;
    private BufferedImage alienImage;

    private Clip clip;
    private Sound sound;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public GameWorld(ScorePanel scorePanel) {
        this.setFocusable(true);
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(800, 600));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setFont(new Font("Verdana", Font.BOLD, 15));
        this.setVisible(true);

        this.scorePanel = scorePanel;
        this.player = createPlayer();

        this.heartIcon = new ImageIcon("resources/img/heart.gif");
        backgroundImage = Toolkit.getDefaultToolkit().createImage("resources/img/background.png");

        this.scorePanel.getScoreLabel().setText("Score: " + player.getScore());
        this.scorePanel.getLiveLabel().setIcon(heartIcon);
        this.scorePanel.getLiveLabel().setText(" X " + player.getLives());
        this.scorePanel.getPlayerLabel().setText("Player: " + player.getName());

        this.ship = new SpaceShip();
        this.missiles = new ArrayList<>();
        this.aliens = new ArrayList<>();

        this.sound = new Sound("resources/sound/game_music.wav");
        sound.play();
        sound.loop();

        try {
            shipImage = ImageIO.read(new File("resources/img/SpaceShip.png"));
        } catch (IOException ex) {
            System.out.println("Fehler! SpaceShip-Image nicht gefunden");
        }

        try {
            alienImage = ImageIO.read(new File("resources/img/alien1.png"));
        } catch (IOException ex) {
            System.out.println("Fehler! Alien-Image nicht gefunden");
        }
    }

    public SpaceShip getShip() {
        return ship;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(backgroundImage, 0, 0, null);

        //g.drawString("=[]>-", ship.getX(), ship.getY());

        g.drawImage(shipImage, ship.getX(), ship.getY() - 15, this);

        if (missiles != null) {
            for (Missile m : missiles) {
                // g.drawString("-", m.getX(), m.getY());
                g.drawLine(m.getX(), m.getY(), m.getX() + 20, m.getY());
                m.moveX();
            }

            //ListIterator to avoid Current Modification Exception
            //Removes Missiles if they are out of the Screen
            ListIterator<Missile> iter = missiles.listIterator();
            while (iter.hasNext()) {
                if (iter.next().getX() > 800) {
                    iter.remove();
                }
            }
        }

        if (aliens != null) {
            for (Alien a : aliens) {
                //g.drawString("<€=", a.getX(), a.getY());
                g.drawImage(alienImage, a.getX(), a.getY() - 35, this);
                a.moveX();

                /* Removes Aliens which get out of the Screen (disabled because they should come in again)

                ListIterator<Alien> iter = aliens.listIterator();
                while (iter.hasNext()) {
                    if (iter.next().getX() < 0) {
                        iter.remove();
                    }
                }*/
            }
        }

        checkCollision();
    }

    public void fireMissile() {
        playSound("resources/sound/shot.wav");

        //Instances Missile on ship position
        missiles.add(new Missile(ship.getX() + 50, ship.getY()));
    }

    public void generateAliens() {
        //generates a number between 1 and 10
        int random = 1 + r.nextInt(10);

        //After a period of 1 to 10 seconds method calculateAlienPosition() is executed (period)
        //Starts 2 seconds after the start of the program execution (delay)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                calculateAlienPosition();
            }
        }, 2 * 1000, random * 1000);
    }

    // method generates a random number for the alien Y-position
    private void calculateAlienPosition() {
        // random.nextInt(max - min + 1) + min
        int random = r.nextInt(10 - 1 + 1) + 1;

        aliens.add(new Alien(850, random * 50));
    }

    private void checkCollision() {
        //ArrayList to which collided Objects are added
        ArrayList<Collidable> collidablesToRemove = new ArrayList<>();

        for (Collidable collAlien : aliens) {
            for (Collidable collMissile : missiles) {
                if (collMissile.collide(collAlien)) {
                    playSound("resources/sound/explosion.wav");

                    collidablesToRemove.add(collMissile);
                    collidablesToRemove.add(collAlien);

                    player.updateScore();
                    scorePanel.getScoreLabel().setText("Score: " + player.getScore());
                }
            }
        }

        for (Collidable collAlien : aliens) {
            if (ship.collide(collAlien)) {
                playSound("resources/sound/explosion.wav");

                collidablesToRemove.add(collAlien);

                player.updateLives(-1);
                this.scorePanel.getLiveLabel().setText(" X " + player.getLives());
            }
        }

        missiles.removeAll(collidablesToRemove);
        aliens.removeAll(collidablesToRemove);
    }

    private void playSound(String path) {
        try {
            // Open an audio input stream.
            File soundFile = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            this.clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            this.clip.open(audioIn);
            this.clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    private Player createPlayer() {
        String playerName = (String) JOptionPane.showInputDialog(
                this, "Enter your name", "Player", JOptionPane.PLAIN_MESSAGE,
                null, null, "player");

        Player player = new Player(playerName);
        return player;
    }

    public void gameOver() {
        aliens.removeAll(aliens);
        missiles.removeAll(missiles);

        playSound("resources/sound/explosion.wav");
        this.sound.stop();
        playSound("resources/sound/GameOver.wav");

        //makes the SpaceShip disappear
        shipImage = new BufferedImage(10, 10, 1);

        repaint();

        //opens a dialog with the Game Over message
        JOptionPane.showMessageDialog
                (this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);

        System.exit(ABORT);
    }
}