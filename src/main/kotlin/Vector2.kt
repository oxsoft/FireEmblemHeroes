data class Vector2(val x: Int, val y: Int) {
    operator fun plus(o: Vector2): Vector2 = Vector2(x + o.x, y + o.y)
    operator fun times(o: Int): Vector2 = Vector2(x * o, y * o)
    fun distance(o: Vector2): Int = Math.abs(x - o.x) + Math.abs(y - o.y)
}