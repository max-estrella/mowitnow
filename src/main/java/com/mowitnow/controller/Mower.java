package com.mowitnow.controller;

import com.mowitnow.input.ICommand;
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
    protected ICommand command;

    /**
     * Controleur des tondeuses
     */
    protected Consumer<Mow> controller;

    public Mower(ICommand command, Consumer<Mow> controller) {
        this.command = command;
        this.controller = controller;
    }

    /**
     * Coupe la pelouse
     */
    public void mow() {
        command.getMows()
                .stream()
                .filter(mow -> null != mow)
                .forEach(mow -> {
                    controller.accept(mow);
                    setChanged();
                    notifyObservers(mow);
                });
    }
}
