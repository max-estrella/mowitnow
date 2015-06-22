package com.mowitnow.controller;

import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;

import java.util.function.Consumer;

/**
 * @author Max Velasco <ivan-maximino.velasco-martin@ign.fr>
 */
public interface IMowController extends Consumer<Mow> {

    /**
     * Pelouse
     * @param land
     */
    public void setLand(ILand land);
}
