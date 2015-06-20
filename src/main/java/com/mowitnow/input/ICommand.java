package com.mowitnow.input;


import com.mowitnow.enums.Direction;
import com.mowitnow.field.IField;
import com.mowitnow.model.Mow;

import java.util.List;

/**
 * Interface qui represente les différents instructions à donner à une tondeuse
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public interface ICommand {

    /**
     * Recupère les dimensions de la grille de pelouse
     *
     * @return IField
     */
    IField getField();

    /**
     * Retourne true si on a plus de tondeuses
     *
     * @return boolean
     */
    boolean hasNextMow();

    /**
     * Retourne la prochaine tondeuse de la grille
     *
     * @return Mow
     */
    Mow nextMow();

    /**
     * Retourne la prochaine instruction
     *
     * @return Direction
     */
    List<Direction> getCommands();

}
