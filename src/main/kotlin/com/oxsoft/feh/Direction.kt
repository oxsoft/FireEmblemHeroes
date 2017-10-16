package com.oxsoft.feh

enum class Direction(val v: Vector2) {
    LEFT(Vector2(-1, 0)),
    TOP(Vector2(0, -1)),
    RIGHT(Vector2(1, 0)),
    BOTTOM(Vector2(0, 1)),
}
