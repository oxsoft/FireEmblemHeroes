class GameState(val field: Field, val units: Array<Unit>) {
    var turn = Side.ME

    fun move(unit: Unit, motion: Array<Direction>) {
        if (!units.contains(unit)) return
        if (unit.motionType.getRange() < motion.size) return
        motion.forEach {
            if (!field.get(unit.position + it.v).isPassable(unit.motionType)) return
            unit.position += it.v // TODO
        }
    }

    fun attack(offense: Unit, defense: Unit) {
        if (!units.contains(offense)) return
        if (!units.contains(defense)) return
        if (offense.position.distance(defense.position) != offense.weaponType.getRange()) return
        offense.attack(defense)
    }

    fun assist(offense: Unit, defense: Unit) {
        if (!units.contains(offense)) return
        if (!units.contains(defense)) return
        // TODO: distance
        offense.assist(defense)
    }
}