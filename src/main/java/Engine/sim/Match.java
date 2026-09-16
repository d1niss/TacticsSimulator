package engine.sim;

import engine.model.Ball;
import engine.model.Player;
import engine.model.Vector2D;
import engine.tactics.Formation;
import java.util.*;

public class Match {
    private final List<Player> allPlayers = new ArrayList<>();
    private final Ball ball;
    private int currentTick = 0;
    private final int totalTicks = 10800; // 90 min at 0.5s ticks

    public Match() {
        allPlayers.addAll(Formation.createFlat442(1, true));
        allPlayers.addAll(Formation.createFlat442(2, false));
        this.ball = new Ball(new Vector2D(52.5, 34.0));
        
        // Give ball to Team 1 Center Mid to start
        Player starter = allPlayers.stream().filter(p -> p.getTeamId() == 1 && p.getName().contains("Center Mid L")).findFirst().get();
        ball.setPossessor(starter);
    }

    public void run() {
        while (currentTick < totalTicks) {
            updateTick();
            currentTick++;
        }
    }

    private void updateTick() {
        Player carrier = ball.getPossessor();
        if (carrier != null) {
            // Decision: Carrier passes or steps forward
            resolveCarrierAction(carrier);
        }

        // Shift defenders toward the ball
        for (Player p : allPlayers) {
            if (carrier != null && p.getTeamId() != carrier.getTeamId()) {
                // Opponents press if within 15 meters
                if (p.getPosition().distanceTo(ball.getPosition()) < 15.0) {
                    p.shiftToward(ball.getPosition(), 2.0); // max 2 meters per tick
                }
            }
        }
    }

    private void resolveCarrierAction(Player carrier) {
        // Find nearest teammate ahead of the ball
        Optional<Player> target = allPlayers.stream()
                .filter(p -> p.getTeamId() == carrier.getTeamId() && !p.equals(carrier))
                .filter(p -> (carrier.getTeamId() == 1 ? p.getPosition().x() > carrier.getPosition().x() : p.getPosition().x() < carrier.getPosition().x()))
                .min(Comparator.comparingDouble(p -> p.getPosition().distanceTo(carrier.getPosition())));

        if (target.isPresent() && target.get().getPosition().distanceTo(carrier.getPosition()) < 25.0) {
            System.out.printf("[Tick %d] %s passed to %s%n", currentTick, carrier.getName(), target.get().getName());
            ball.setPossessor(target.get());
        } else {
            // Carry forward
            double dir = carrier.getTeamId() == 1 ? 1.0 : -1.0;
            Vector2D carryTarget = new Vector2D(carrier.getPosition().x() + dir * 2.0, carrier.getPosition().y());
            carrier.shiftToward(carryTarget, 1.5);
            ball.setPosition(carrier.getPosition());
        }
    }
}