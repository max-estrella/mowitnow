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

    public void setDimension(int x, int y) {
        if (x >= 0 && y >=0) {
            this.dimension = new Point(x,y);
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
