enum class MotionType {
    WALK,
    HEAVY,
    HORSE,
    FLYING;

    fun getRange(): Int {
        when (this) {
            MotionType.WALK -> return 2
            MotionType.HEAVY -> return 1
            MotionType.HORSE -> return 3
            MotionType.FLYING -> return 2
        }
    }
}