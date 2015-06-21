package com.mowitnow.input.helper;

import com.google.common.collect.Lists;
import com.mowitnow.enums.Direction;
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
import static com.mowitnow.enums.Orientation.*;
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
    public void testBuildMowIfLineOk() {
        Mow mow = ConfigHelper.buildMow("3 4 N  ");
        assertThat(mow).isNotNull();
        assertThat(mow.getOrientation()).isEqualTo(NORTH);
        assertThat(mow.getPosition()).isEqualTo(new Point(3, 4));
    }

    @Test
    public void testBuildMowIfLineNotOk() {
        assertThat(ConfigHelper.buildMow("1 2")).isNull();
        assertThat(ConfigHelper.buildMow("1 2 N 5 7")).isNull();
    }

    @Test(expected = NumberFormatException.class)
    public void testIfCoordsNotNumberShouldThrowsException() {
        ConfigHelper.buildMow("X X N");
    }

    @Test(expected = NullPointerException.class)
    public void testIfLineNullShouldThrowsException() {
        ConfigHelper.buildMow(null);
    }

    @Test
    public void testBuildOrientation() {
        assertThat(ConfigHelper.buildOrientation("N")).isEqualTo(NORTH);
        assertThat(ConfigHelper.buildOrientation("S")).isEqualTo(SOUTH);
        assertThat(ConfigHelper.buildOrientation("W")).isEqualTo(WEST);
        assertThat(ConfigHelper.buildOrientation("E")).isEqualTo(EAST);
        assertThat(ConfigHelper.buildOrientation("X")).isNull();
    }

    @Test
    public void testMowsAndCommandsIfOk() {
        List<Mow> mows = Lists.newArrayList();
        List<List<Direction>> commands = Lists.newArrayList();
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