class Field(val cells: Array<Array<CellType>>) {
    fun get(position: Vector2): CellType {
        return cells[position.y][position.x]
    }
}