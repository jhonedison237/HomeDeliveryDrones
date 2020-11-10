package com.s4n.homedelivery.model.state;

import com.s4n.homedelivery.model.Position;

import java.util.NoSuchElementException;

public abstract class DirectionState {

    public static final int NORTH_LIMIT = 10;
    public static final int EAST_LIMIT = 10;
    public static final int SOUTH_LIMIT = -10;
    public static final int WEST_LIMIT = -10;

    public void updatePosition(Character charInstruction, Position position) {

        switch (charInstruction) {
            case 'A':
                advancePosition(position);
                break;
            case 'I':
                turnLeft(position);
                break;
            case 'D':
                turnRight(position);
                break;
            default:
                throw new NoSuchElementException(String.format("La instrucción no es válida [%s]", charInstruction));
        }
    }

    public abstract void turnRight(Position position);

    public abstract void turnLeft(Position position);

    public abstract void advancePosition(Position position);

    public abstract void validateMovement(int positionPoint);
}
