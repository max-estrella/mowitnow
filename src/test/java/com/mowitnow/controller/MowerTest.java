package com.mowitnow.controller;

import com.google.common.collect.Lists;
import com.mowitnow.enums.Orientation;
import com.mowitnow.input.IConfigReader;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.util.function.Consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MowerTest {

    @Mock
    protected IConfigReader reader;

    @Mock
    protected Consumer<Mow> controller;

    @InjectMocks
    protected Mower mower;

    @Test
    public void testMow() {
        Mow mow1 = new Mow(new Point(1,2), Orientation.NORTH);
        Mow mow2 = new Mow(new Point(3,3), Orientation.EAST);

        when(reader.getMows()).thenReturn(Lists.newArrayList(mow1, mow2));

        mower.mow();

        verify(controller).accept(mow1);
        verify(controller).accept(mow2);
    }
}