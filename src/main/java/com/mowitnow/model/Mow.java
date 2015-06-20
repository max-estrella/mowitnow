package com.mowitnow.model;

import com.mowitnow.enums.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.Point;

/**
 * Represente la position d'une tondeuse
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@Data
@AllArgsConstructor
public class Mow {

    /**
     * Position actuel
     */
    protected Point position;

    /**
     * Orientation actuel de la tondeuse
     */
    protected Orientation orientation;

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
