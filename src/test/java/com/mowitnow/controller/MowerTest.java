package com.mowitnow.controller;

import com.google.common.collect.ImmutableList;
import com.mowitnow.enums.Orientation;
import com.mowitnow.land.DefaultLand;
import com.mowitnow.input.ICommand;
import com.mowitnow.model.Mow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Point;
import java.util.Observable;

import static com.mowitnow.enums.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
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
    protected Observable observable;

    protected Mower mower;

    @Before
    public void setUp() {
        when(command.getField()).thenReturn(new DefaultLand(new Point(5, 5)));
        mower = new Mower(command, observable);
    }

    @Test
    public void testMow() {
        when(command.hasNextMow()).thenReturn(true, true, false);
        Mow mow1 = new Mow(new Point(1,2), Orientation.NORTH);
        Mow mow2 = new Mow(new Point(3,3), Orientation.EAST);
        when(command.nextMow()).thenReturn(mow1, mow2);

        when(command.getCommands()).thenReturn(
            ImmutableList.of(//GAGAGAGAA
                LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD
            ),
            ImmutableList.of(//AADAADADDA
                FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD
            )
        );

        mower.mow();

        assertThat(mow1.getPosition()).isEqualTo(new Point(1, 3));
        assertThat(mow1.getOrientation()).isEqualTo(Orientation.NORTH);
        verify(observable).notifyObservers(eq(mow1));

        assertThat(mow2.getPosition()).isEqualTo(new Point(5, 1));
        assertThat(mow2.getOrientation()).isEqualTo(Orientation.EAST);
        verify(observable).notifyObservers(eq(mow2));
    }
}