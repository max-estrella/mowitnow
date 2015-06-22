package com.mowitnow.input;


import com.mowitnow.land.ILand;
import com.mowitnow.model.Mow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.mowitnow.enums.Direction.FORWARD;
import static com.mowitnow.enums.Direction.LEFT;
import static com.mowitnow.enums.Direction.RIGHT;
import static com.mowitnow.enums.Orientation.EAST;
import static com.mowitnow.enums.Orientation.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class FileCommandTest {

    @Test
    public void testReadFileAndGetMows() throws Exception {
        ILand land = Mockito.mock(ILand.class);
        FileCommand command = new FileCommand(getClass().getClassLoader().getResourceAsStream("input.txt"), land);

        List<Mow> mows = command.getMows();

        assertThat(mows).hasSize(2);
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

}