package com.mowitnow.controller;

import com.mowitnow.input.IConfigReader;
import com.mowitnow.model.Mow;

import java.util.Observable;
import java.util.function.Consumer;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class Mower extends Observable implements IMower {

    /**
     * Input data
     */
    protected IConfigReader reader;

    /**
     * Controleur des tondeuses
     */
    protected Consumer<Mow> controller;

    public Mower(IConfigReader reader, Consumer<Mow> controller) {
        this.reader = reader;
        this.controller = controller;
    }

    /**
     * Coupe la pelouse
     */
    public void mow() {
        reader.getMows()
                .stream()
                .filter(mow -> null != mow)
                .forEach(mow -> {
                    controller.accept(mow);
                    setChanged();
                    notifyObservers(mow);
                });
    }
}
