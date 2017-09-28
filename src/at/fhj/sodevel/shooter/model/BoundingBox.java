package at.fhj.sodevel.shooter.model;

public class BoundingBox {
    private int x, y, delta;

    public BoundingBox(int x, int y, int delta) {
        this.x = x;
        this.y = y;
        this.delta = delta;
    }

    public int getXMin() {
        int xMin = this.x - delta;
        return xMin;
    }

    public int getXMax() {
        int xMax = this.x + delta;
        return xMax;
    }

    public int getYMin() {
        int yMin = this.y - delta;
        return yMin;
    }

    public int getYMax() {
        int yMax = this.y + delta;
        return yMax;
    }

    public boolean overlaps(BoundingBox boundingBox) {
        if (this.getXMax() >= boundingBox.getXMin() &&
                this.getYMax() <= boundingBox.getYMax() &&
                this.getYMin() >= boundingBox.getYMin() &&
                this.getXMin() <= boundingBox.getXMax()) {
            return true;
        } else {
            return false;
        }
    }
}