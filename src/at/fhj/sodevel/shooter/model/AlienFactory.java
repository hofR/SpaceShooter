package at.fhj.sodevel.shooter.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AlienFactory {
    private Random r = new Random();
    private Timer timer = new Timer();

    public void generateAliens(ArrayList<Alien> aliens) {
        //generates a number between 1 and 10
        int random = 1 + r.nextInt(10);

        //After a period of 1 to 10 seconds method calculateAlienPosition() is executed (period)
        //Starts 2 seconds after the start of the program execution (delay)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                calculateAlienPosition(aliens);
            }
        }, 2 * 1000, random * 1000);
    }

    // method generates a random number for the alien Y-position
    private void calculateAlienPosition(ArrayList<Alien> aliens) {
        // random.nextInt(max - min + 1) + min
        int random = r.nextInt(10 - 1 + 1) + 1;

        aliens.add(new Alien(850, random * 50));
    }
}
