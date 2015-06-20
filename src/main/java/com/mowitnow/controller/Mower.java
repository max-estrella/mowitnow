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
        this.controller.setTheField(command.getField());
        this.observable = observable;
    }

    public void mow() {
        while (command.hasNextMow()) {
            controller.setCurrentMow(command.nextMow());
            command.getCommands()
                    .stream()
                    .filter(d -> null != d)
                    .forEach(controller);

            observable.notifyObservers(controller.getCurrentMow());
        }
    }
}
