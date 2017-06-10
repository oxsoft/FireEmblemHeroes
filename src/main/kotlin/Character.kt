data class Character(val side: Side, val maxHp: Int, val attack: Int, val agility: Int, val defense: Int, val magicDefense: Int, val motionType: MotionType, val weaponType: WeaponType, val weapon: Int?, val assist: Int?, val skill: Int?, val A: Int?, val B: Int?, val C: Int?) {
    var hp = maxHp
    var position = Vector2(0, 0)
    var acted = false

    fun startTurn() {
        acted = false
    }

    fun attack(character: Character) {
        if (acted) return
        if (damage(this, character) == 0) {
            return
        }
        if (weaponType.getRange() == character.weaponType.getRange()) {
            if (damage(character, this) == 0) {
                return
            }
        }
        if (agility - character.agility >= 5) {
            if (damage(this, character) == 0) {
                return
            }
        }
        if (character.agility - agility >= 5) {
            if (damage(character, this) == 0) {
                return
            }
        }
        acted = true
    }

    fun assist(character: Character) {
        // TODO
        acted = true
    }

    private fun damage(offense: Character, defense: Character): Int {
        val def = if (offense.weaponType.isMagic()) defense.magicDefense else defense.defense
        if (offense.attack > def) defense.hp -= offense.attack - def
        if (defense.hp < 0) defense.hp = 0
        return defense.hp
    }
}