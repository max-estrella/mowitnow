package com.mowitnow.model;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableTable;
import com.mowitnow.enums.Direction;
import com.mowitnow.enums.Orientation;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.function.Function;

import static com.mowitnow.enums.Direction.*;
import static com.mowitnow.enums.Orientation.*;

/**
 * Represente la position d'une tondeuse avec des méthodes pour l'avancer, changer de direction
 * ou obtenir le prochain point
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class Mow {

    /**
     * Marque le mouvement de la tondeuse par rapport à la direction actuel et la future
     */
    private static final ImmutableTable<Orientation, Direction, Orientation> DIRECTION_TABLE = new ImmutableTable.Builder<Orientation, Direction, Orientation>()
            .put(NORTH, RIGHT, EAST)
            .put(NORTH, LEFT, WEST)
            .put(EAST, RIGHT, SOUTH)
            .put(EAST, LEFT, NORTH)
            .put(SOUTH, RIGHT, WEST)
            .put(SOUTH, LEFT, EAST)
            .put(WEST, RIGHT, NORTH)
            .put(WEST, LEFT, SOUTH)
            .build();

    private static final Function<Mow, Point> NEXT_POSITION = (Mow m) -> {
        switch (m.orientation ){
            case NORTH: return new Point(m.position.x, m.position.y +1);
            case SOUTH: return new Point(m.position.x, m.position.y -1);
            case EAST: return new Point(m.position.x +1, m.position.y);
            case WEST: return new Point(m.position.x -1 , m.position.y);
            default: return null;
        }
    };

    /**
     * Position actuel
     */
    @VisibleForTesting
    Point position;

    /**
     * Orientation actuel de la tondeuse
     */
    @VisibleForTesting
    Orientation orientation;


    public Mow(@Nonnull Point position, @Nonnull Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    /**
     * Change la direction par rapport à la direction actuel
     * @param direction
     * @return Mow
     */
    public Mow change(@Nonnull Direction direction) {

        if (null == DIRECTION_TABLE.get(orientation, direction)) {
            //Forward
            position = next(direction);
        } else {
            //change orientation
            orientation = DIRECTION_TABLE.get(orientation, direction);
        }
        return this;
    }

    /**
     * Retourne le prochain Point ou null si la tondeuse change d'orientation mais pas de coordonnées
     * @return Point
     */
    public Point next(@Nonnull Direction direction) {
        if (FORWARD.equals(direction)) {
            return NEXT_POSITION.apply(this);
        }
        return this.position;
    }

    /**
     * Pour les tests
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (null != object && object instanceof Mow) {
            Mow other = (Mow) object;
            return this.orientation.equals(other.orientation) &&
                    this.position.equals(other.position);
        }
        return false;
    }


}
