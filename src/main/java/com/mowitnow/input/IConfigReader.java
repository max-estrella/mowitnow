package com.mowitnow.input;

import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;

import java.io.InputStream;
import java.util.List;

/**
 * Les classes implémentant cet interface devront lire la configuration des tondeuses et la pelouse
 * pour construire les objets à traiter
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public interface IConfigReader {

    /**
     * Charge et parse les données pour construire la liste de tondeuses et les dimesions de la pelouse
     * @param data
     */
    void loadData(InputStream data);

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
