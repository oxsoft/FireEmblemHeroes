class Vector2(val x: Int, val y: Int) {
    operator fun plus(o: Vector2): Vector2 = Vector2(x + o.x, y + o.y)
    operator fun times(o: Int): Vector2 = Vector2(x * o, y * o)
}