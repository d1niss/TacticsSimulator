package Engine.tactics;

import engine.model.Player;
import engine.model.Vector2D;
import java.util.ArrayList;
import java.util.List;

public class Formation {
    public static List<Player> createFlat442(int teamId, boolean attackingRight) {
        List<Player> players = new ArrayList<>();
        // Invert X coordinates if team attacks from right to left
        double dir = attackingRight ? 1.0 : -1.0;
        double originX = attackingRight ? 0.0 : 105.0;

        // GK
        players.add(new Player("GK", "Goalkeeper", teamId, new Vector2D(originX + dir * 5.0, 34.0)));

        // Back 4 (LB, LCB, RCB, RB)
        players.add(new Player("LB", "Left Back", teamId, new Vector2D(originX + dir * 25.0, 10.0)));
        players.add(new Player("LCB", "Center Back L", teamId, new Vector2D(originX + dir * 20.0, 26.0)));
        players.add(new Player("RCB", "Center Back R", teamId, new Vector2D(originX + dir * 20.0, 42.0)));
        players.add(new Player("RB", "Right Back", teamId, new Vector2D(originX + dir * 25.0, 58.0)));

        // Midfield 4 (LM, LCM, RCM, RM)
        players.add(new Player("LM", "Left Mid", teamId, new Vector2D(originX + dir * 50.0, 10.0)));
        players.add(new Player("LCM", "Center Mid L", teamId, new Vector2D(originX + dir * 48.0, 26.0)));
        players.add(new Player("RCM", "Center Mid R", teamId, new Vector2D(originX + dir * 48.0, 42.0)));
        players.add(new Player("RM", "Right Mid", teamId, new Vector2D(originX + dir * 50.0, 58.0)));

        // Front 2 (ST, ST)
        players.add(new Player("ST1", "Striker L", teamId, new Vector2D(originX + dir * 70.0, 28.0)));
        players.add(new Player("ST2", "Striker R", teamId, new Vector2D(originX + dir * 70.0, 40.0)));

        return players;
    }
}