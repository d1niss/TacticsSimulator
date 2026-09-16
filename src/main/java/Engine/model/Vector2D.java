package engine.model;

public record Vector2D(double x, double y) {
    public double distanceTo(Vector2D other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    public Vector2D moveTowards(Vector2D target, double maxStep) {
        double dist = distanceTo(target);
        if (dist <= maxStep || dist == 0) return target;
        double ratio = maxStep / dist;
        return new Vector2D(x + (target.x - x) * ratio, y + (target.y - y) * ratio);
    }
}