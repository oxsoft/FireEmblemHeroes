enum class WeaponType {
    SWORD,
    SPEAR,
    AX,
    BOW,
    NINJA,
    RED_MAGIC,
    BLUE_MAGIC,
    GREEN_MAGIC,
    WAND,
    RED_DRAGON,
    BLUE_DRAGON,
    GREEN_DRAGON;

    fun isMagic(): Boolean {
        when (this) {
            SWORD, SPEAR, AX, BOW, NINJA, RED_DRAGON, BLUE_DRAGON, GREEN_DRAGON -> return false
            RED_MAGIC, BLUE_MAGIC, GREEN_MAGIC, WAND -> return true
        }
    }

    fun getAttackType(): AttackType {
        when (this) {
            WeaponType.SWORD, WeaponType.RED_MAGIC, WeaponType.RED_DRAGON -> return AttackType.RED
            WeaponType.SPEAR, WeaponType.BLUE_MAGIC, WeaponType.BLUE_DRAGON -> return AttackType.BLUE
            WeaponType.AX, WeaponType.GREEN_MAGIC, WeaponType.GREEN_DRAGON -> return AttackType.GREEN
            WeaponType.BOW, WeaponType.NINJA, WeaponType.WAND -> return AttackType.NONE
        }
    }

    fun getRange(): Int {
        when (this) {
            SWORD, SPEAR, AX, RED_DRAGON, BLUE_DRAGON, GREEN_DRAGON -> return 1
            BOW, NINJA, RED_MAGIC, BLUE_MAGIC, GREEN_MAGIC, WAND -> return 2
        }
    }
}