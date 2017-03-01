enum class AttackType {
    RED,
    BLUE,
    GREEN,
    NONE;

    companion object {
        fun getType(weaponType: WeaponType): AttackType {
            when (weaponType) {
                WeaponType.SWORD, WeaponType.RED_MAGIC, WeaponType.RED_DRAGON -> return RED
                WeaponType.SPEAR, WeaponType.BLUE_MAGIC, WeaponType.BLUE_DRAGON -> return BLUE
                WeaponType.AX, WeaponType.GREEN_MAGIC, WeaponType.GREEN_DRAGON -> return GREEN
                WeaponType.BOW, WeaponType.NINJA, WeaponType.WAND -> return NONE
            }
        }
    }
}