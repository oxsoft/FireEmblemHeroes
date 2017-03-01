class Unit(val side: Side, val maxHp: Int, val attack: Int, val agility: Int, val defense: Int, val magicDefense: Int, val motionType: MotionType, val weaponType: WeaponType, val weapon: Int?, val assist: Int?, val skill: Int?, val A: Int?, val B: Int?, val C: Int?) {
    var hp = maxHp
    var position = Vector2(0, 0)
}