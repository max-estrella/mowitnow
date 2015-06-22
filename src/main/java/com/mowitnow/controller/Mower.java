package com.mowitnow.controller;

import com.mowitnow.input.ICommand;

import java.util.Observable;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class Mower {

    protected ICommand command;

    protected MowController controller;

    protected Observable observable;

    public Mower(ICommand command, Observable observable) {
        this.command = command;
        this.controller = new MowController();
        this.controller.setLand(command.getLand());
        this.observable = observable;
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
                    observable.notifyObservers(mow);
                });
    }
}
