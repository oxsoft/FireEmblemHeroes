data class Unit(val side: Side, val maxHp: Int, val attack: Int, val agility: Int, val defense: Int, val magicDefense: Int, val motionType: MotionType, val weaponType: WeaponType, val weapon: Int?, val assist: Int?, val skill: Int?, val A: Int?, val B: Int?, val C: Int?) {
    var hp = maxHp
    var position = Vector2(0, 0)
    var acted = false

    fun startTurn() {
        acted = false
    }

    fun attack(unit: Unit) {
        if (acted) return
        if (damage(this, unit) == 0) {
            return
        }
        if (weaponType.getRange() == unit.weaponType.getRange()) {
            if (damage(unit, this) == 0) {
                return
            }
        }
        if (agility - unit.agility >= 5) {
            if (damage(this, unit) == 0) {
                return
            }
        }
        if (unit.agility - agility >= 5) {
            if (damage(unit, this) == 0) {
                return
            }
        }
        acted = true
    }

    fun assist(unit: Unit) {
        // TODO
        acted = true
    }

    private fun damage(offense: Unit, defense: Unit): Int {
        val def = if (offense.weaponType.isMagic()) defense.magicDefense else defense.defense
        if (offense.attack > def) defense.hp -= offense.attack - def
        if (defense.hp < 0) defense.hp = 0
        return defense.hp
    }
}