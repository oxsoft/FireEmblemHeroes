enum class CellType {
    NORMAL,
    FOREST,
    RIVER,
    WALL;

    fun isPassable(motionType: MotionType): Boolean {
        when (this) {
            CellType.NORMAL -> return true
            CellType.FOREST -> return motionType != MotionType.HORSE
            CellType.RIVER -> return motionType == MotionType.FLYING
            CellType.WALL -> return false
        }
    }
}