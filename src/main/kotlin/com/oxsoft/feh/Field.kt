package com.oxsoft.feh

class Field(val cells: Array<Array<CellType>>) {
    fun get(position: Vector2): CellType {
        if (position.y < 0 || position.y >= cells.size) return CellType.WALL
        if (position.x < 0 || position.x >= cells[0].size) return CellType.WALL
        return cells[position.y][position.x]
    }
}
