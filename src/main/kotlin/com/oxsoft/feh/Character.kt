package com.oxsoft.feh

data class Character(
        val side: Side,
        val maxHp: Int,
        val attack: Int,
        val agility: Int,
        val defense: Int,
        val magicDefense: Int,
        val motionType: MotionType,
        val weapon: Weapon?,
        val assist: Assist?,
        val skill: Skill?,
        val A: A?,
        val B: B?,
        val C: C?,
        val position: Vector2,
        val hp: Int = maxHp,
        val acted: Boolean = false
) {
    fun startTurn(): Character {
        return copy(acted = false)
    }

    fun attack(target: Character): Pair<Character, Character>? {
        if (acted) return null
        weapon ?: return null
        var myHp = hp
        var targetHp = target.hp
        targetHp -= damage(this, target)
        if (targetHp <= 0) {
            return Pair(copy(acted = true), target.copy(hp = 0))
        }
        if (target.weapon?.type?.getRange() == weapon.type.getRange()) {
            myHp -= damage(target, this)
            if (myHp <= 0) {
                return Pair(copy(hp = 0, acted = true), target.copy(hp = targetHp))
            }
        }
        if (agility - target.agility >= 5) {
            targetHp -= damage(this, target)
            if (targetHp <= 0) {
                return Pair(copy(hp = myHp, acted = true), target.copy(hp = 0))
            }
        }
        if (target.agility - agility >= 5) {
            myHp -= damage(target, this)
            if (myHp <= 0) {
                return Pair(copy(hp = 0, acted = true), target.copy(hp = targetHp))
            }
        }
        return Pair(copy(hp = myHp, acted = true), target.copy(hp = targetHp))
    }

    fun assist(target: Character): Pair<Character, Character>? {
        // TODO
        return Pair(copy(acted = true), target)
    }

    private fun damage(offense: Character, defense: Character): Int {
        offense.weapon ?: return 0
        val def = if (offense.weapon.type.isMagic()) defense.magicDefense else defense.defense
        val damage = offense.attack - def
        return if (damage > 0) damage else 0
    }
}
