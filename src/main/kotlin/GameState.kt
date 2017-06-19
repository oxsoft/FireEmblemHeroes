class GameState(val turn: Side, val field: Field, val status: List<Status>) {


    fun move(index: Int, motion: Array<Direction>): GameState? {
        val target = status[index]
        if (target.character.side != turn) return null
        if (target.character.motionType.getRange() < motion.size) return null
        var position = target.position
        motion.forEach {
            if (!field.get(position + it.v).isPassable(target.character.motionType)) return null
            position += it.v
        }
        val updated = status.mapIndexed { i, status -> if (i == index) Status(target.character, target.hp, position, true) else status }
        return GameState(turn, field, updated)
    }

    fun attack(offenseIndex: Int, defenseIndex: Int): GameState? {
        val offense = status[offenseIndex]
        val defense = status[defenseIndex]
        if (offense.character.side != turn) return null
        if (defense.character.side == turn) return null
        if (offense.position.distance(defense.position) != offense.character.weaponType.getRange()) return null
        val p: Pair<Status, Status> = offense.attack(defense) ?: return null
        val updated = status.mapIndexed { i, status -> if (i == offenseIndex) p.first else if (i == defenseIndex) p.second else status }
        return GameState(turn, field, updated)
    }

    fun assist(offenseIndex: Int, defenseIndex: Int): GameState? {
        val offense = status[offenseIndex]
        val defense = status[defenseIndex]
        if (offense.character.side != turn) return null
        if (defense.character.side == turn) return null
        if (!status.contains(offense)) return null
        if (!status.contains(defense)) return null
        // TODO: distance
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