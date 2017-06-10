class GameState(val field: Field, val characters: Array<Character>) {
    var turn = Side.ME

    fun move(character: Character, motion: Array<Direction>) {
        if (!characters.contains(character)) return
        if (character.motionType.getRange() < motion.size) return
        motion.forEach {
            if (!field.get(character.position + it.v).isPassable(character.motionType)) return
            character.position += it.v // TODO
        }
    }

    fun attack(offense: Character, defense: Character) {
        if (!characters.contains(offense)) return
        if (!characters.contains(defense)) return
        if (offense.position.distance(defense.position) != offense.weaponType.getRange()) return
        offense.attack(defense)
    }

    fun assist(offense: Character, defense: Character) {
        if (!characters.contains(offense)) return
        if (!characters.contains(defense)) return
        // TODO: distance
        offense.assist(defense)
    }
}