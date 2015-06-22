package com.mowitnow.observer;

import com.mowitnow.model.Mow;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Max Velasco <ivan-maximino.velasco-martin@ign.fr>
 */
public class MowObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Mow) {
            Mow mow = (Mow) arg;
            System.out.println("Final position for mow " +
                    "(" + mow.getPosition().x + "," + mow.getPosition().y + ") " +
                    "with Orientation " + mow.getOrientation());
        }

    }
}
