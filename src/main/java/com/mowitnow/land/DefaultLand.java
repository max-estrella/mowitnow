package com.mowitnow.land;

import com.google.common.annotations.VisibleForTesting;

import java.awt.Point;

/**
 * Represente la grille de la pelouse
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class DefaultLand implements ILand {

    @VisibleForTesting
    Point dimension;

    public DefaultLand(Point dimension) {
        if (dimension.x >= 0 && dimension.y >=0) {
            this.dimension = dimension;
        } else {
            //todo what to do if not valid ??
            this.dimension = new Point(0,0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(Point point) {
        return point.x >= 0 && point.y >= 0 &&
                point.x <= this.dimension.x && point.y <= this.dimension.y;
    }
}
