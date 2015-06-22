package com.mowitnow.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mowitnow.enums.Direction;
import com.mowitnow.enums.Orientation;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Point;
import java.util.List;

import static com.mowitnow.enums.Orientation.*;
import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
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
        Mow mow = buildMow(1, 1, EAST, Lists.newArrayList(LEFT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(NORTH);

        mow = buildMow(1, 1, EAST, Lists.newArrayList(RIGHT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(SOUTH);
    }

    @Test
    public void testProcessInEastWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);

        Mow mow = buildMow(1, 1, EAST, Lists.newArrayList(FORWARD));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(2);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void testProcessInWestShouldChangeOrientation()  {
        Mow mow = buildMow(1, 1, WEST, Lists.newArrayList(LEFT));

        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(SOUTH);

        mow = buildMow(1, 1, WEST, Lists.newArrayList(RIGHT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testProcessInWestWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);

        Mow mow = buildMow(1, 1, WEST, Lists.newArrayList(FORWARD));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(0);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testProcessInNorthShouldChangeOrientation()  {
        Mow mow = buildMow(1, 1, NORTH, Lists.newArrayList(RIGHT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(EAST);


        mow = buildMow(1, 1, NORTH, Lists.newArrayList(LEFT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(WEST);
    }

    @Test
    public void testProcessInNorthWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        Mow mow = buildMow(1, 1, NORTH, Lists.newArrayList(FORWARD));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(2);
        assertThat(mow.getOrientation()).isEqualTo(NORTH);
    }

    @Test
    public void testProcessInSouthShouldChangeOrientation()  {
        Mow mow = buildMow(1, 1, SOUTH, Lists.newArrayList(RIGHT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(WEST);

        mow = buildMow(1, 1, SOUTH, Lists.newArrayList(LEFT));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(EAST);
    }

    @Test
    public void testProcessInSouthWhenValidShouldForward() {
        when(field.isValid(any())).thenReturn(true);
        Mow mow = buildMow(1, 1, SOUTH, Lists.newArrayList(FORWARD));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(0);
        assertThat(mow.getOrientation()).isEqualTo(SOUTH);
    }

    @Test
    public void testProcessAnyWhereWhenInValidShouldStay() {
        when(field.isValid(any())).thenReturn(false);
        Mow mow = buildMow(1, 1, EAST, Lists.newArrayList(FORWARD));
        controller.accept(mow);
        assertThat(mow.getPosition().x).isEqualTo(1);
        assertThat(mow.getPosition().y).isEqualTo(1);
        assertThat(mow.getOrientation()).isEqualTo(EAST);
    }

    protected static Mow buildMow(int x, int y, Orientation orientation, List<Direction> commands) {
        Mow mow = new Mow(new Point(x, y), orientation);
        mow.setCommands(commands);
        return mow;
    }
}