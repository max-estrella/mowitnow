package com.mowitnow.controller;

import com.mowitnow.input.ICommand;
import com.mowitnow.model.Mow;

import java.util.Observable;
import java.util.function.Consumer;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class Mower {

    /**
     * Input data
     */
    protected ICommand command;

    /**
     * Controleur des tondeuses
     */
    protected IMowController controller;

    /**
     * Observable pour notifier les observes qu'on a processé une tondeuse
     */
    protected Observable observable;

    public Mower(ICommand command, IMowController controller, Observable observable) {
        this.command = command;
        this.controller = controller;
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
