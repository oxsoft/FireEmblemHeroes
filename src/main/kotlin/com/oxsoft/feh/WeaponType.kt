package com.oxsoft.feh

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
            SWORD, RED_MAGIC, RED_DRAGON -> return AttackType.RED
            SPEAR, BLUE_MAGIC, BLUE_DRAGON -> return AttackType.BLUE
            AX, GREEN_MAGIC, GREEN_DRAGON -> return AttackType.GREEN
            BOW, NINJA, WAND -> return AttackType.NONE
        }
    }

    fun getRange(): Int {
        when (this) {
            SWORD, SPEAR, AX, RED_DRAGON, BLUE_DRAGON, GREEN_DRAGON -> return 1
            BOW, NINJA, RED_MAGIC, BLUE_MAGIC, GREEN_MAGIC, WAND -> return 2
        }
    }
}
