package engine.model;

public class Player {
    private final String id;
    private final String name;
    private final int teamId; // One team or the other
    private Vector2D position;
    private final Vector2D basePosition; // Tactical origin in formation

    public Player(String id, String name, int teamId, Vector2D basePosition) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.basePosition = basePosition;
        this.position = basePosition;
    }

    public void shiftToward(Vector2D target, double speedPerTick) {
        this.position = this.position.moveTowards(target, speedPerTick);
    }

    public Vector2D getPosition() { return position; }
    public Vector2D getBasePosition() { return basePosition; }
    public int getTeamId() { return teamId; }
    public String getName() { return name; }
}