package com.mowitnow.controller;

import com.google.common.collect.ImmutableTable;
import com.mowitnow.enums.Direction;
import com.mowitnow.enums.Orientation;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.awt.Point;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.mowitnow.enums.Direction.*;
import static com.mowitnow.enums.Orientation.*;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class MowController implements Consumer<Mow> {

    /**
     * Marque le mouvement de la tondeuse par rapport à la direction actuel et la future
     */
    private static final ImmutableTable<Orientation, Direction, Orientation> ORIENTATION_DIRECTION_TABLE = new ImmutableTable.Builder<Orientation, Direction, Orientation>()
            .put(NORTH, RIGHT, EAST)
            .put(NORTH, LEFT, WEST)
            .put(EAST, RIGHT, SOUTH)
            .put(EAST, LEFT, NORTH)
            .put(SOUTH, RIGHT, WEST)
            .put(SOUTH, LEFT, EAST)
            .put(WEST, RIGHT, NORTH)
            .put(WEST, LEFT, SOUTH)
            .build();

    /**
     * Renvoie la position suivante par rapport à la position actuelle de la tondeuse
     */
    private static final Function<Mow, Point> NEXT_POSITION = (Mow m) -> {
        switch (m.getOrientation()){
            case NORTH: return new Point(m.getPosition().x, m.getPosition().y +1);
            case SOUTH: return new Point(m.getPosition().x, m.getPosition().y -1);
            case EAST: return new Point(m.getPosition().x +1, m.getPosition().y);
            case WEST: return new Point(m.getPosition().x -1 , m.getPosition().y);
            default: return null;
        }
    };

    @Setter
    private ILand land;

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Mow currentMow) {
        Consumer<Direction> movement = (Direction command) -> {
            Orientation nextOrientation = ORIENTATION_DIRECTION_TABLE.get(currentMow.getOrientation(), command);
            if (null == nextOrientation) {
                //Forward
                Point nextPosition = next(currentMow, command);
                if (land.isValid(nextPosition)) {
                    currentMow.setPosition(nextPosition);
                }
            } else {
                //change orientation
                currentMow.setOrientation(nextOrientation);
            }
        };

        currentMow.getCommands()
                .stream()
                .filter(c -> null != c)
                .forEach(movement);
    }

    /**
     * Retourne le prochain Point ou null si la tondeuse change d'orientation mais pas de coordonnées
     * @return Point
     */
    protected Point next(@Nonnull Mow currentMow, @Nonnull Direction direction) {
        if (FORWARD.equals(direction)) {
            return NEXT_POSITION.apply(currentMow);
        }
        return currentMow.getPosition();
    }

}
