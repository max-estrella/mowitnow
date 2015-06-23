package com.mowitnow.input.helper;

import com.google.common.collect.Lists;
import com.mowitnow.enums.Direction;
import com.mowitnow.enums.Orientation;
import com.mowitnow.exception.BadFormattedInputException;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;

import javax.annotation.Nonnull;
import java.awt.Point;
import java.util.List;

import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static com.mowitnow.enums.Orientation.*;

/**
 * Utilitaire qui permet de passer les lignes du fichier de configuration pour établir
 * les dimensions de la pelouse et construire les tondeuses avec la position initiale
 * et les ordres à éxecuter
 *
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class ConfigHelper {

    private static final String DELIMITER = " ";

    /**
     *
     * @param line
     * @return
     */
    public static List<Direction> buildDirections(@Nonnull String line) {
        List<Direction> directions = Lists.newArrayList();
        line.chars().forEach(
                c -> {
                    Direction direction = buildDirection((char) c);
                    if (null != direction) {
                        directions.add(direction);
                    }
                }
        );
        return directions;
    }

    /**
     *
     * @param c
     * @return
     */
    public static Direction buildDirection(char c) {
        switch (c) {
            case 'G' : return LEFT;
            case 'D' : return RIGHT;
            case 'A' : return FORWARD;
        }
        return null;
    }

    /**
     *
     * @param land
     * @param configLines
     */
    public static void setLandDimensions(ILand land, List<String> configLines) throws BadFormattedInputException {
        if (null != configLines && configLines.size()>=1) {
            String[] tokens = configLines.get(0).split(DELIMITER);
            if (tokens.length == 2) {
                try{
                    land.setDimension(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]));
                } catch (NumberFormatException e) {
                    //Error
                    throw new BadFormattedInputException("Bad formatted line " + configLines.get(0) + " => " + e.getMessage());
                }
            } else {
                //Error
                throw new BadFormattedInputException("Bad formatted line (" + configLines.get(0) + ") for set land dimensions");
            }
        }
    }

    /**
     * @param line
     * @return Mow
     * @throws NumberFormatException
     */
    public static Mow buildMow(@Nonnull String line) throws BadFormattedInputException {
        String[] tokens = line.trim().split(DELIMITER);
        if (3 == tokens.length) { // on va être strict
            try {
                return new Mow(new Point(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])), buildOrientation(tokens[2]));
            } catch (NumberFormatException e) {
                throw new BadFormattedInputException("Bad formatted line (" + line + ") for building mow => " + e.getMessage());
            }
        }
        throw new BadFormattedInputException("Bad formatted line (" + line + ") for building mow ");
    }

    /**
     *
     * @param sOrientation
     * @return Orientation
     */
    public static Orientation buildOrientation(@Nonnull String sOrientation) throws BadFormattedInputException{
        switch (sOrientation) {
            case "N" : return NORTH;
            case "S" : return SOUTH;
            case "E" : return EAST;
            case "W" : return WEST;
        }
        throw new BadFormattedInputException("Bad formatted orientiation: " + sOrientation);
    }

    /**
     *
     * @param commandLines
     * @throws BadFormattedInputException
     * @return List<Mow>
     */
    public static @Nonnull List<Mow> loadMowsAndCommands(@Nonnull List<String> commandLines) throws BadFormattedInputException {

        Mow mow = null;
        List<Mow> mowList = Lists.newArrayList();
        if (commandLines.size()>=3) {
            for (int i = 1; i<commandLines.size(); i++) {
                if (i % 2 != 0) {
                    //Mow
                    mow = ConfigHelper.buildMow(commandLines.get(i));
                    mowList.add(mow);
                } else {
                    //Orders
                    mow.setCommands(ConfigHelper.buildDirections(commandLines.get(i)));
                }
            }
        }

        return mowList;
    }
}
