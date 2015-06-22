package com.mowitnow.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
        DefaultLand land = new DefaultLand();
        land.setDimension(5,5);
        when(command.getLand()).thenReturn(land);
        mower = new Mower(command, observable);
    }

    @Test
    public void testMow() {
        Mow mow1 = new Mow(new Point(1,2), Orientation.NORTH);
        mow1.setCommands(
            ImmutableList.of(//GAGAGAGAA
                LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD
            )
        );
        Mow mow2 = new Mow(new Point(3,3), Orientation.EAST);
        mow2.setCommands(
                ImmutableList.of(//AADAADADDA
                        FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD
                )
        );

        when(command.getMows()).thenReturn(Lists.newArrayList(mow1, mow2));

        mower.mow();

        assertThat(mow1.getPosition()).isEqualTo(new Point(1, 3));
        assertThat(mow1.getOrientation()).isEqualTo(Orientation.NORTH);
        verify(observable).notifyObservers(eq(mow1));

        assertThat(mow2.getPosition()).isEqualTo(new Point(5, 1));
        assertThat(mow2.getOrientation()).isEqualTo(Orientation.EAST);
        verify(observable).notifyObservers(eq(mow2));
    }
}