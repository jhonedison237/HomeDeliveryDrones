package com.s4n.homedelivery.model;

import com.s4n.homedelivery.model.state.DirectionState;
import com.s4n.homedelivery.model.state.EastState;
import com.s4n.homedelivery.model.state.NorthState;
import com.s4n.homedelivery.model.state.SouthState;
import com.s4n.homedelivery.model.state.WestState;

public class Position {
    private int x;
    private int y;
    private Direction direction;
    private DirectionState directionState;

    private DirectionState northState;
    private DirectionState westState;
    private DirectionState eastState;
    private DirectionState southState;


    public Position() {
        x = 0;
        y = 0;
        direction = Direction.NORTH;
        northState = new NorthState();
        westState = new WestState();
        southState = new SouthState();
        eastState = new EastState();
        directionState = northState;

    }

    public void updatePosition(Character charInstruction) {

        directionState.updatePosition(charInstruction, this);
    }

    public String getDirectionReport() {
        return "(" + x + ", " + y + ") dirección " + direction.getName();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionState getNorthState() {
        return northState;
    }

    public DirectionState getWestState() {
        return westState;
    }

    public DirectionState getEastState() {
        return eastState;
    }

    public DirectionState getSouthState() {
        return southState;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setDirectionState(DirectionState directionState) {
        this.directionState = directionState;
    }

    public enum Direction {
        NORTH("Norte"), SOUTH("Sur"), WEST("Occidente"), EAST("Oriente");

        String name;

        Direction(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
