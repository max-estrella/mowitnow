package com.mowitnow.model;

import com.mowitnow.enums.Orientation;
import org.junit.Test;

import java.awt.Point;

import static com.mowitnow.enums.Orientation.*;
import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class MowTest {

    @Test
    public void testChangeInEastShouldChangeOrientation()  {
        assertThat(buildMow(1, 1, EAST).change(LEFT)).isEqualTo(buildMow(1, 1, NORTH));

        assertThat(buildMow(1, 1, EAST).change(RIGHT)).isEqualTo(buildMow(1, 1, SOUTH));

        assertThat(buildMow(1,1,EAST).change(FORWARD)).isEqualTo(buildMow(2, 1, EAST));
    }

    @Test
    public void testChangeInWestShouldChangeOrientation()  {
        assertThat(buildMow(1, 1, WEST).change(LEFT)).isEqualTo(buildMow(1, 1, SOUTH));

        assertThat(buildMow(1,1,WEST).change(RIGHT)).isEqualTo(buildMow(1, 1, NORTH));

        assertThat(buildMow(1,1,WEST).change(FORWARD)).isEqualTo(buildMow(0, 1, WEST));
    }

    @Test
    public void testChangeInNorthShouldChangeOrientation()  {
        assertThat(buildMow(1,1, NORTH).change(RIGHT)).isEqualTo(buildMow(1, 1, EAST));

        assertThat(buildMow(1,1,NORTH).change(LEFT)).isEqualTo(buildMow(1, 1, WEST));

        assertThat(buildMow(1,1,NORTH).change(FORWARD)).isEqualTo(buildMow(1, 2, NORTH));
    }

    @Test
    public void testChangeInSouthShouldChangeOrientation()  {
        assertThat(buildMow(1,1, SOUTH).change(RIGHT)).isEqualTo(buildMow(1, 1, WEST));

        assertThat(buildMow(1,1,SOUTH).change(LEFT)).isEqualTo(buildMow(1, 1, EAST));

        assertThat(buildMow(1,1,SOUTH).change(FORWARD)).isEqualTo(buildMow(1, 0, SOUTH));
    }


    @Test
    public void testNextWhenOrientationNotForwardShouldnotChange() {
        Mow mow = new Mow(new Point(1,1), EAST);

        assertThat(mow.next(LEFT)).isEqualTo(new Point(1,1));
        assertThat(mow.next(RIGHT)).isEqualTo(new Point(1,1));
    }

    @Test
    public void testNextWhenDirectionShouldReturnNextPoint() {
        assertThat(buildMow(1,1,EAST).next(FORWARD)).isEqualTo(new Point(2, 1));

        assertThat(buildMow(2,5,NORTH).next(FORWARD)).isEqualTo(new Point(2, 6));

        assertThat(buildMow(3,2,WEST).next(FORWARD)).isEqualTo(new Point(2, 2));

        assertThat(buildMow(2,5,SOUTH).next(FORWARD)).isEqualTo(new Point(2, 4));
    }

    protected static Mow buildMow(int x, int y, Orientation orientation) {
        return new Mow(new Point(x, y), orientation);
    }
}