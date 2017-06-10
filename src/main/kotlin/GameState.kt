class GameState(val field: Field, val status: Array<Status>) {
    var turn = Side.ME

    fun move(status: Status, motion: Array<Direction>): GameState? {
        if (!this.status.contains(status)) return null
        if (status.character.motionType.getRange() < motion.size) return null
        motion.forEach {
            if (!field.get(status.position + it.v).isPassable(status.character.motionType)) return null
            // status.position += it.v // TODO
        }
        return null
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