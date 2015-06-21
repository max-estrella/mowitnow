package com.mowitnow.land;

import org.junit.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class DefaultLandTest {

    @Test
    public void testCanGoInRange() throws Exception {
        DefaultLand field = new DefaultLand(new Point(5,5));
        Point point = new Point(3,2);
        assertThat(field.isValid(point)).isTrue();

        point.setLocation(0, 0);
        assertThat(field.isValid(point)).isTrue();

        point.setLocation(5,5);
        assertThat(field.isValid(point)).isTrue();

        point.setLocation(0,-1);
        assertThat(field.isValid(point)).isFalse();

        point.setLocation(5,6);
        assertThat(field.isValid(point)).isFalse();

        point.setLocation(6,5);
        assertThat(field.isValid(point)).isFalse();
    }

    @Test
    public void testWhenDimesionNotValidShouldBeZero() {
        DefaultLand field = new DefaultLand(new Point(-5,-4));
        assertThat(field.dimension).isEqualTo(new Point(0,0));
    }
}