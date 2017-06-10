class GameState(val field: Field, val status: Array<Status>) {
    var turn = Side.ME

    fun move(index: Int, motion: Array<Direction>): GameState? {
        val target = status[index]
        if (target.character.motionType.getRange() < motion.size) return null
        var position = target.position
        motion.forEach {
            if (!field.get(target.position + it.v).isPassable(target.character.motionType)) return null
            position += it.v // TODO
        }
        status[index] = Status(target.character, target.hp, position, true)
        return GameState(field, status)
    }

    fun attack(offense: Status, defense: Status): GameState? {
        if (!status.contains(offense)) return null
        if (!status.contains(defense)) return null
        if (offense.position.distance(defense.position) != offense.character.weaponType.getRange()) return null
        val of: Pair<Status, Status>? = offense.attack(defense)
        of ?: return null
        return GameState(field, status)
    }

    fun assist(offense: Status, defense: Status): GameState? {
        if (!status.contains(offense)) return null
        if (!status.contains(defense)) return null
        // TODO: distance
        offense.assist(defense)
        return null
    }
}