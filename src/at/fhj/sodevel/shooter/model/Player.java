package at.fhj.sodevel.shooter.model;

public class Player {
    private String name;
    private int score;
    private int lives;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = 3;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void updateScore() {
        this.score = this.score + 5;
    }

    public int getLives() {
        return this.lives;
    }

    public void updateLives(int liveChange) {
        this.lives = this.lives + liveChange;
    }
}