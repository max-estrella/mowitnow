package com.mowitnow.controller;

import com.mowitnow.enums.Orientation;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Point;

import static com.mowitnow.enums.Orientation.*;
import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MowControllerTest {

    @Mock
    protected ILand field;

    @InjectMocks
    protected MowController controller;

    @Test
    public void testProcessInEastShouldChangeOrientation()  {
        //Change orientation
        controller.setCurrentMow(buildMow(1, 1, EAST));
        controller.accept(LEFT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, NORTH));

        controller.setCurrentMow(buildMow(1, 1, EAST));
        controller.accept(RIGHT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, SOUTH));
    }

    @Test
    public void testProcessInEastWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        controller.setCurrentMow(buildMow(1, 1, EAST));
        controller.accept(FORWARD);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(2, 1, EAST));
    }

    @Test
    public void testProcessInWestShouldChangeOrientation()  {
        controller.setCurrentMow(buildMow(1, 1, WEST));
        controller.accept(LEFT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, SOUTH));

        controller.setCurrentMow(buildMow(1, 1, WEST));
        controller.accept(RIGHT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, NORTH));
    }

    @Test
    public void testProcessInWestWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        controller.setCurrentMow(buildMow(1, 1, WEST));
        controller.accept(FORWARD);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(0, 1, WEST));
    }

    @Test
    public void testProcessInNorthShouldChangeOrientation()  {
        controller.setCurrentMow(buildMow(1, 1, NORTH));
        controller.accept(RIGHT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, EAST));

        controller.setCurrentMow(buildMow(1, 1, NORTH));
        controller.accept(LEFT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, WEST));
    }

    @Test
    public void testProcessInNorthWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        controller.setCurrentMow(buildMow(1, 1, NORTH));
        controller.accept(FORWARD);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 2, NORTH));
    }

    @Test
    public void testProcessInSouthShouldChangeOrientation()  {
        controller.setCurrentMow(buildMow(1, 1, SOUTH));
        controller.accept(RIGHT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, WEST));

        controller.setCurrentMow(buildMow(1, 1, SOUTH));
        controller.accept(LEFT);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, EAST));
    }

    @Test
    public void testProcessInSouthWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        controller.setCurrentMow(buildMow(1, 1, SOUTH));
        controller.accept(FORWARD);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 0, SOUTH));
    }

    @Test
    public void testProcessAnyWhereWhenInValidShouldStay() {
        when(field.isValid(any())).thenReturn(false);
        controller.setCurrentMow(buildMow(1, 1, EAST));
        controller.accept(FORWARD);
        assertThat(controller.getCurrentMow()).isEqualTo(buildMow(1, 1, EAST));
    }

    protected static Mow buildMow(int x, int y, Orientation orientation) {
        return new Mow(new Point(x, y), orientation);
    }
}