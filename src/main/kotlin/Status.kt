data class Status(val character: Character, val hp: Int, val position: Vector2, val acted: Boolean) {
    fun startTurn(): Status {
        return Status(character, hp, position, false)
    }

    fun attack(target: Status): Pair<Status, Status>? {
        if (acted) return null
        character.weapon ?: return null
        var myHp = hp
        var targetHp = target.hp
        targetHp -= damage(this, target)
        if (targetHp <= 0) {
            return Pair(Status(character, hp, position, true), Status(target.character, 0, target.position, target.acted))
        }
        if (target.character.weapon?.type?.getRange() == character.weapon.type.getRange()) {
            myHp -= damage(target, this)
            if (myHp <= 0) {
                return Pair(Status(character, 0, position, true), Status(target.character, targetHp, target.position, target.acted))
            }
        }
        if (character.agility - target.character.agility >= 5) {
            targetHp -= damage(this, target)
            if (targetHp <= 0) {
                return Pair(Status(character, myHp, position, true), Status(target.character, 0, target.position, target.acted))
            }
        }
        if (target.character.agility - character.agility >= 5) {
            myHp -= damage(target, this)
            if (myHp <= 0) {
                return Pair(Status(character, 0, position, true), Status(target.character, targetHp, target.position, target.acted))
            }
        }
        return Pair(Status(character, myHp, position, true), Status(target.character, targetHp, target.position, target.acted))
    }

    fun assist(target: Status): Pair<Status, Status>? {
        // TODO
        return Pair(Status(character, hp, position, true), target)
    }

    private fun damage(offense: Status, defense: Status): Int {
        offense.character.weapon ?: return 0
        val def = if (offense.character.weapon.type.isMagic()) defense.character.magicDefense else defense.character.defense
        val damage = offense.character.attack - def
        return if (damage > 0) damage else 0
    }

    companion object {
        fun init(character: Character, position: Vector2): Status {
            return Status(character, character.maxHp, position, false)
        }
    }
}