package com.mowitnow.model;

import com.mowitnow.enums.Direction;
import com.mowitnow.enums.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Nonnull;
import java.awt.Point;
import java.util.List;

/**
 * Represente la position d'une tondeuse
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@Data
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
     * Liste des ordres pour déplacer la tondeuse
     */
    protected List<Direction> commands;

    /**
     * On construit la tondeuse avec la position et orientation initiale
     *
     * @param position
     * @param orientation
     */
    public Mow(@Nonnull Point position, @Nonnull Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
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
