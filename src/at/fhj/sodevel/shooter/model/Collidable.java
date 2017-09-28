package at.fhj.sodevel.shooter.model;

public interface Collidable {

    boolean isCollided();

    boolean collide(Collidable e);

    BoundingBox getBoundingBox();
}
