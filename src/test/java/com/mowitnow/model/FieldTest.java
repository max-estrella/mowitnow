package com.mowitnow.model;

import org.junit.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Max Velasco <ivan.velascomartin@gmail.com>
 */
public class FieldTest {

    @Test
    public void testCanGoInRange() throws Exception {
        Field field = new Field(new Point(5,5));
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
        Field field = new Field(new Point(-5,-4));
        assertThat(field.dimension).isEqualTo(new Point(0,0));
    }
}