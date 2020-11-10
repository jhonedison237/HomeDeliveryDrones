package com.s4n.homedelivery.model.state;

import com.s4n.homedelivery.exception.LimitExceededException;
import com.s4n.homedelivery.model.Position;

public class EastState extends DirectionState {

    @Override
    public void turnRight(Position position) {
        position.setDirection(Position.Direction.SOUTH);
        position.setDirectionState(position.getSouthState());
    }

    @Override
    public void turnLeft(Position position) {
        position.setDirection(Position.Direction.NORTH);
        position.setDirectionState(position.getNorthState());
    }

    @Override
    public void advancePosition(Position position) {
        validateMovement(position.getX());
        position.setX(position.getX() + 1);
    }

    @Override
    public void validateMovement(int positionPoint) {
        if (positionPoint + 1 > EAST_LIMIT) {
            throw new LimitExceededException("No puedo ir más allá de 10 cuadras al oriente");
        }
    }

}
