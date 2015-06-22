package com.mowitnow.controller;

import com.google.common.collect.Lists;
import com.mowitnow.enums.Orientation;
import com.mowitnow.input.ICommand;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.util.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MowerTest {

    @Mock
    protected ICommand command;

    @Mock
    protected IMowController controller;

    @Mock
    protected Observable observable;

    @InjectMocks
    protected Mower mower;

    @Test
    public void testMow() {
        Mow mow1 = new Mow(new Point(1,2), Orientation.NORTH);
        Mow mow2 = new Mow(new Point(3,3), Orientation.EAST);

        when(command.getMows()).thenReturn(Lists.newArrayList(mow1, mow2));

        mower.mow();

        verify(controller).accept(mow1);
        verify(observable).notifyObservers(mow1);

        verify(controller).accept(mow2);
        verify(observable).notifyObservers(mow2);
    }
}