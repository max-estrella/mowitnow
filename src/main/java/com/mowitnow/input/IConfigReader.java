package com.mowitnow.input;

import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;

import java.util.List;

/**
 * Les classes implémentant cet interface devront lire la configuration des tondeuses et la pelouse
 * pour construire les objets à traiter
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public interface IConfigReader {

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
