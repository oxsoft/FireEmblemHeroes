data class Status(val character: Character, val hp: Int, val position: Vector2, val acted: Boolean) {
    fun startTurn(): Status {
        return Status(character, hp, position, false)
    }

    fun attack(target: Status): Pair<Status, Status>? {
        if (acted) return null
        if (damage(this, target) == 0) {
            return null
        }
        if (character.weaponType.getRange() == character.weaponType.getRange()) {
            if (damage(target, this) == 0) {
                return null
            }
        }
        if (character.agility - target.character.agility >= 5) {
            if (damage(this, target) == 0) {
                return null
            }
        }
        if (target.character.agility - character.agility >= 5) {
            if (damage(target, this) == 0) {
                return null
            }
        }
        return Pair<Status, Status>(Status(character, hp, position, true), target)
    }

    fun assist(target: Status): Pair<Status, Status>? {
        // TODO
        return Pair<Status, Status>(this, target)
    }

    private fun damage(offense: Status, defense: Status): Int {
        val def = if (offense.character.weaponType.isMagic()) defense.character.magicDefense else defense.character.defense
        // if (offense.character.attack > def) defense.hp -= offense.character.attack - def
        // if (defense.hp < 0) defense.hp = 0
        return defense.hp
    }

    companion object {
        fun init(character: Character, position: Vector2): Status {
            return Status(character, character.maxHp, position, false)
        }
    }
}