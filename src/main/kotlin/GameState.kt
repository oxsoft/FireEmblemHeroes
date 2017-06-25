class GameState(val turn: Side, val field: Field, val character: List<Character>) {

    private fun moveInternal(index: Int, motion: List<Direction>): Character? {
        val target = character[index]
        if (target.acted) return null
        if (target.side != turn) return null
        if (target.motionType.range < motion.size) return null
        var position = target.position
        motion.forEach {
            if (!field.get(position + it.v).isPassable(target.motionType)) return null
            position += it.v
            if (target.motionType == MotionType.WALK && field.get(position) == CellType.FOREST && motion.size != 1) {
                return null
            }
        }
        if (character.filterIndexed { i, character -> i != index && character.position == position }.isNotEmpty()) return null
        return target.copy(position = position, acted = true)
    }

    fun move(index: Int, motion: List<Direction>): GameState? {
        val moved = moveInternal(index, motion) ?: return null
        val updated = character.mapIndexed { i, character -> if (i == index) moved else character }
        return GameState(turn, field, updated)
    }

    fun attack(offenseIndex: Int, defenseIndex: Int, motion: List<Direction>): GameState? {
        val offense = moveInternal(offenseIndex, motion) ?: return null
        val defense = character[defenseIndex]
        if (defense.acted) return null
        if (offense.side != turn) return null
        if (defense.side == turn) return null
        offense.weapon ?: return null
        if (offense.position.distance(defense.position) != offense.weapon.type.getRange()) return null
        val p: Pair<Character, Character> = offense.attack(defense) ?: return null
        val updated = character.mapIndexed { i, character -> if (i == offenseIndex) p.first else if (i == defenseIndex) p.second else character }
        return GameState(turn, field, updated)
    }

    fun assist(offenseIndex: Int, defenseIndex: Int, motion: List<Direction>): GameState? {
        val offense = moveInternal(offenseIndex, motion) ?: return null
        val defense = character[defenseIndex]
        if (defense.acted) return null
        if (offense.side != turn) return null
        if (defense.side == turn) return null
        offense.assist ?: return null
        if (offense.position.distance(defense.position) != offense.assist.distance) return null
        val p: Pair<Character, Character> = offense.assist(defense) ?: return null
        val updated = character.mapIndexed { i, character -> if (i == offenseIndex) p.first else if (i == defenseIndex) p.second else character }
        return GameState(turn, field, updated)
    }

    fun changeTurn(): GameState {
        val next = when (turn) {Side.ME -> Side.ENEMY
            Side.ENEMY -> Side.ME
        }
        val updated = character.map { if (it.side == next) it.startTurn() else it }
        return GameState(next, field, updated)
    }
}