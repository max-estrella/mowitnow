package com.mowitnow.input;

import com.mowitnow.land.ILand;
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
     * @return ILand
     */
    ILand getLand();

    /**
     * Recupère la liste des tondeuses
     *
     * @return
     */
    List<Mow> getMows();
}
