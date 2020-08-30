package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * @author q
 * @create 2020-08-30 17:16
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates Clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("Clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a Clorus with energy equal to 1.
     */
    public Clorus() {
        this(1.5);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        if (energy - 0.03 < 0) {
            energy = 0;
            return;
        }
        energy -= 0.03;
    }

    public void stay() {
        if (energy - 0.01 < 0) {
            energy = 0;
            return;
        }
        energy -= 0.01;
    }

    public Creature replicate() {
        Creature c = new Clorus(energy/2);
        energy = energy / 2;
        return c;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        boolean isEmpty = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")){
                isEmpty = true;
                emptyNeighbors.add(d);
            }
            if (neighbors.get(d).name().equals("plip")){
                anyPlip = true;
                plipNeighbors.add(d);
            }
        }

        if (!isEmpty){
            stay();
            return new Action(Action.ActionType.STAY);
        } else if (anyPlip){
            Direction dir = randomEntry(plipNeighbors);
            attack((Creature) neighbors.get(dir));
            return new Action(Action.ActionType.ATTACK, dir);
        } else if (energy > 1){
            Direction dir = randomEntry(emptyNeighbors);
            replicate();
            return new Action(Action.ActionType.REPLICATE, dir);
        }

        Direction dir = randomEntry(emptyNeighbors);
        move();
        return new Action(Action.ActionType.MOVE, dir);

    }

    private Direction randomEntry(Deque<Direction> directions){
        Object[] dirs = directions.toArray();
        int i = (int) Math.floor(Math.random()* dirs.length);
        return (Direction) dirs[i];
    }
}

