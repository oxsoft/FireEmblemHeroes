package com.oxsoft.feh

enum class CellType {
    NORMAL,
    FOREST,
    RIVER,
    WALL;

    fun isPassable(motionType: MotionType): Boolean {
        when (this) {
            NORMAL -> return true
            FOREST -> return motionType != MotionType.HORSE
            RIVER -> return motionType == MotionType.FLYING
            WALL -> return false
        }
    }
}
