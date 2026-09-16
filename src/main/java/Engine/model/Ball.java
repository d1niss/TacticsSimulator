package Engine.model;

public class Ball {
    private Vector2D position;
    private Player possessor;

    public Ball(Vector2D initialPosition) {
        this.position = initialPosition;
        this.possessor = null;
    }

    public Vector2D getPosition() { return position; }
    public void setPosition(Vector2D position) { this.position = position; }
    public Player getPossessor() { return possessor; }
    public void setPossessor(Player possessor) { 
        this.possessor = possessor;
        if (possessor != null) {
            this.position = possessor.getPosition();
        }
    }
}