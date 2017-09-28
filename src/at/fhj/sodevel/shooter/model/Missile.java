package at.fhj.sodevel.shooter.model;

public class Missile extends SpaceObjects implements Collidable {

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveX() {
        this.x = this.x + 50;
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
        return new BoundingBox(this.x, this.y, 10);
    }
}
