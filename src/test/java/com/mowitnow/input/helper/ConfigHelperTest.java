package com.mowitnow.input.helper;

import com.google.common.collect.Lists;
import com.mowitnow.exception.BadFormattedInputException;
import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.Point;
import java.util.List;

import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static com.mowitnow.enums.Orientation.NORTH;
import static com.mowitnow.enums.Orientation.SOUTH;
import static com.mowitnow.enums.Orientation.EAST;
import static com.mowitnow.enums.Orientation.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigHelperTest  {

    @Mock
    protected ILand land;

    @Test
    public void testSetLandDimensionsIfOK() throws Exception {
        ConfigHelper.setLandDimensions(land, fileLines());

        verify(land).setDimension(5, 5);

    }

    @Test
    public void testBuildDirectionsIfOk() {
        assertThat(ConfigHelper.buildDirections("DAGA")).containsExactly(RIGHT, FORWARD, LEFT, FORWARD);
        assertThat(ConfigHelper.buildDirections("DAXGA")).containsExactly(RIGHT, FORWARD, LEFT, FORWARD);
    }

    @Test
    public void testBuildDirection() {
        assertThat(ConfigHelper.buildDirection('A')).isEqualTo(FORWARD);
        assertThat(ConfigHelper.buildDirection('G')).isEqualTo(LEFT);
        assertThat(ConfigHelper.buildDirection('D')).isEqualTo(RIGHT);
        assertThat(ConfigHelper.buildDirection('R')).isNull();
    }

    @Test
    public void testBuildMowIfLineOk() throws Exception{
        Mow mow = ConfigHelper.buildMow("3 4 N  ");
        assertThat(mow).isNotNull();
        assertThat(mow.getOrientation()).isEqualTo(NORTH);
        assertThat(mow.getPosition()).isEqualTo(new Point(3, 4));
    }

    @Test(expected = BadFormattedInputException.class)
    public void testBuildMowIfLineLessThanThreeTokensShouldThrowException() throws Exception {
        ConfigHelper.buildMow("1 2");
    }

    @Test(expected = BadFormattedInputException.class)
    public void testBuildMowIfLineMoreThanThreeTokensShouldThrowException() throws Exception {
        ConfigHelper.buildMow("1 2 N 5 7");
    }

    @Test(expected = BadFormattedInputException.class)
    public void testIfCoordsNotNumberShouldThrowsException() throws Exception {
        ConfigHelper.buildMow("X X N");
    }

    @Test(expected = NullPointerException.class)
    public void testIfLineNullShouldThrowsException() throws Exception {
        ConfigHelper.buildMow(null);
    }

    @Test
    public void testBuildOrientation() throws Exception {
        assertThat(ConfigHelper.buildOrientation("N")).isEqualTo(NORTH);
        assertThat(ConfigHelper.buildOrientation("S")).isEqualTo(SOUTH);
        assertThat(ConfigHelper.buildOrientation("W")).isEqualTo(WEST);
        assertThat(ConfigHelper.buildOrientation("E")).isEqualTo(EAST);
    }

    @Test(expected = BadFormattedInputException.class)
    public void testBuildOrientationIfBadCharShouldThrowException() throws Exception {
        ConfigHelper.buildOrientation("X");
    }

    @Test
    public void testLoadMowsAndCommandsIfOk() throws Exception {
        List<Mow> mows = ConfigHelper.loadMowsAndCommands(fileLines());
        assertThat(mows).hasSize(2);

        assertThat(mows.get(0).getPosition().x).isEqualTo(1);
        assertThat(mows.get(0).getPosition().y).isEqualTo(2);
        assertThat(mows.get(0).getOrientation()).isEqualTo(NORTH);
        assertThat(mows.get(0).getCommands()).containsExactly(
                LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD
        );

        assertThat(mows.get(1).getPosition().x).isEqualTo(3);
        assertThat(mows.get(1).getPosition().y).isEqualTo(3);
        assertThat(mows.get(1).getOrientation()).isEqualTo(EAST);
        assertThat(mows.get(1).getCommands()).containsExactly(
                FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD
        );
    }

    @Test
    public void testLoadMowsIfLessThanThreeLinesShouldBeEmpty() throws Exception {
        assertThat(ConfigHelper.loadMowsAndCommands(Lists.newArrayList(
                "5 5",
                "1 2 N"
        ))).isEmpty();
    }

    protected static List<String> fileLines() {
        return Lists.newArrayList(
                "5 5",
                "1 2 N",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );
    }

}