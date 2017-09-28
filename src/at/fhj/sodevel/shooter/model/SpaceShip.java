package at.fhj.sodevel.shooter.model;

public class SpaceShip extends SpaceObjects implements Collidable {

    public SpaceShip() {
        this.x = 50;
        this.y = 300;
    }

    public void moveX(int x) {
        this.x = this.x + x;
    }

    public void moveY(int y) {
        this.y = this.y + y;
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
        return new BoundingBox(this.x, this.y, 15);
    }
}
