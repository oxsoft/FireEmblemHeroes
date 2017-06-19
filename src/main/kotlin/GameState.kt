class GameState(val turn: Side, val field: Field, val status: List<Status>) {

    private fun moveInternal(index: Int, motion: List<Direction>): Status? {
        val target = status[index]
        if (target.acted) return null
        if (target.character.side != turn) return null
        if (target.character.motionType.range < motion.size) return null
        var position = target.position
        motion.forEach {
            if (!field.get(position + it.v).isPassable(target.character.motionType)) return null
            position += it.v
            if (target.character.motionType == MotionType.WALK && field.get(position) == CellType.FOREST && motion.size != 1) {
                return null
            }
        }
        return Status(target.character, target.hp, position, true)
    }

    fun move(index: Int, motion: List<Direction>): GameState? {
        val moved = moveInternal(index, motion) ?: return null
        val updated = status.mapIndexed { i, status -> if (i == index) moved else status }
        return GameState(turn, field, updated)
    }

    fun attack(offenseIndex: Int, defenseIndex: Int, motion: List<Direction>): GameState? {
        val offense = moveInternal(offenseIndex, motion) ?: return null
        val defense = status[defenseIndex]
        if (defense.acted) return null
        if (offense.character.side != turn) return null
        if (defense.character.side == turn) return null
        offense.character.weapon ?: return null
        if (offense.position.distance(defense.position) != offense.character.weapon.type.getRange()) return null
        val p: Pair<Status, Status> = offense.attack(defense) ?: return null
        val updated = status.mapIndexed { i, status -> if (i == offenseIndex) p.first else if (i == defenseIndex) p.second else status }
        return GameState(turn, field, updated)
    }

    fun assist(offenseIndex: Int, defenseIndex: Int, motion: List<Direction>): GameState? {
        val offense = moveInternal(offenseIndex, motion) ?: return null
        val defense = status[defenseIndex]
        if (defense.acted) return null
        if (offense.character.side != turn) return null
        if (defense.character.side == turn) return null
        offense.character.assist ?: return null
        if (offense.position.distance(defense.position) != offense.character.assist.distance) return null
        val p: Pair<Status, Status> = offense.assist(defense) ?: return null
        val updated = status.mapIndexed { i, status -> if (i == offenseIndex) p.first else if (i == defenseIndex) p.second else status }
        return GameState(turn, field, updated)
    }

    fun changeTurn(): GameState {
        val next = when (turn) {Side.ME -> Side.ENEMY
            Side.ENEMY -> Side.ME
        }
        val updated = status.map { if (it.character.side == next) it.startTurn() else it }
        return GameState(next, field, updated)
    }
}