package com.s4n.homedelivery.model.state;

import com.s4n.homedelivery.exception.LimitExceededException;
import com.s4n.homedelivery.model.Position;

public class SouthState extends DirectionState {

    @Override
    public void turnRight(Position position) {
        position.setDirection(Position.Direction.WEST);
        position.setDirectionState(position.getWestState());
    }

    @Override
    public void turnLeft(Position position) {
        position.setDirection(Position.Direction.EAST);
        position.setDirectionState(position.getEastState());
    }

    @Override
    public void advancePosition(Position position) {
        validateMovement(position.getY());
        position.setY(position.getY() - 1);
    }

    @Override
    public void validateMovement(int positionPoint) {
        if (positionPoint - 1 < SOUTH_LIMIT) {
            throw new LimitExceededException("No puedo ir más allá de 10 cuadras al sur");
        }
    }
}
