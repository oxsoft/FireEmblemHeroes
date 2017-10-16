package com.oxsoft.feh

import org.junit.Assert
import org.junit.Test

class Vector2Test {
    @Test
    fun plusTest() {
        val actual = Vector2(1, 2) + Vector2(3, 4)
        Assert.assertEquals(Vector2(4, 6), actual)
    }

    @Test
    fun timesTest() {
        val actual = Vector2(1, 2) * 2
        Assert.assertEquals(Vector2(2, 4), actual)
    }

    @Test
    fun distanceTest() {
        val actual = Vector2(1, 2).distance(Vector2(3, 4))
        Assert.assertEquals(4, actual)
    }
}
