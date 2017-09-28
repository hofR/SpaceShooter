package at.fhj.sodevel.shooter.model;

public interface Collidable {

    public boolean isCollided();

    public boolean collide(Collidable e);

    public BoundingBox getBoundingBox();
}
