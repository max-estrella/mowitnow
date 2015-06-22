package com.mowitnow.input;

import com.mowitnow.exception.BadFormattedInputException;
import com.mowitnow.input.helper.ConfigHelper;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Classe pour lire les resources d'un fichier de texte avec les tondeuses, les instructions pour
 * la diriger et les dimensions de la pelouse.
 *
 * On va pour l'exemple et par simplicité charger tous les données en mémoire. On pourra ajouter
 * des méthodes à la class {@link ConfigHelper}
 * 5 5
 * 1 2 N
 * GAGAGAGAA
 * 3 3 E
 * AADAADADDA
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class FileCommand implements ICommand {

    /**
     * Instructions et dimensions de la pelouse
     */
    private List<Mow> mows;

    /**
     * Pelouse
     */
    private ILand land;


    public FileCommand(InputStream inputStream, ILand land) throws IOException, BadFormattedInputException {
        this.land = land;

        List<String> lines = IOUtils.readLines(inputStream);
        ConfigHelper.setLandDimensions(land, lines);
        this.mows = ConfigHelper.loadMowsAndCommands(lines);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILand getLand() {
        return land;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mow> getMows(){
        return mows;
    }

}
