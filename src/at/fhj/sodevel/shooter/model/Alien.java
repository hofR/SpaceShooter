package at.fhj.sodevel.shooter.model;

public class Alien extends SpaceObjects implements Collidable {

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveX() {
        this.x = this.x - 5;

        /*If alien disappears because it reached the left border of the screen,
        it comes back in at the right border of the screen*/
        if (this.x < 0) {
            this.x = 800;
        }
    }

    private boolean isCollided;

    @Override
    public boolean isCollided() {
        return isCollided;
    }

    @Override
    public boolean collide(Collidable e) {
        isCollided = this.getBoundingBox().overlaps(e.getBoundingBox());
        return isCollided;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(this.x, this.y, 35);
    }
}
