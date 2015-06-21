package com.mowitnow.land;

import javax.annotation.Nonnull;
import java.awt.Point;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public interface ILand {

    /**
     * True si la possition dans la pelouse est valide
     * @param point
     * @return
     */
    boolean isValid(@Nonnull Point point);
}
