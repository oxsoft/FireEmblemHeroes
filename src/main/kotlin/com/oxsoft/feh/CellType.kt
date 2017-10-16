package com.oxsoft.feh

enum class CellType {
    NORMAL,
    FOREST,
    RIVER,
    WALL;

    fun isPassable(motionType: MotionType): Boolean = when (this) {
        NORMAL -> true
        FOREST -> motionType != MotionType.HORSE
        RIVER -> motionType == MotionType.FLYING
        WALL -> false
    }
}
