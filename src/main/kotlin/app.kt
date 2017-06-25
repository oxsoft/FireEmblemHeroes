import javax.swing.JFrame

fun main(args: Array<String>) {
    val frame = JFrame()
    frame.setSize(620, 840)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    val panel = GameView(initGameState())
    frame.add(panel)
    frame.isVisible = true

    (1..100).forEach {
        (1..panel.gameState.status.size - 1).forEach {
            Thread.sleep(100)
            panel.gameState = panel.gameState.move(it, listOf(Direction.values()[(Math.random() * 4).toInt()])) ?: panel.gameState
            panel.repaint()
        }
        panel.gameState = panel.gameState.changeTurn()
    }
}

private fun initGameState(): GameState {
    val myCharacter1 = Character(Side.ME, 19, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.AX), null, null, null, null, null)
    val myCharacter2 = Character(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.NINJA), null, null, null, null, null)
    val myCharacter3 = Character(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null)
    val myCharacter4 = Character(Side.ME, 15, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.GREEN_MAGIC), null, null, null, null, null)
    val enemyCharacter1 = Character(Side.ENEMY, 17, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SPEAR), null, null, null, null, null)
    val enemyCharacter2 = Character(Side.ENEMY, 15, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null)
    val enemyCharacter3 = Character(Side.ENEMY, 13, 1, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null)
    val me1 = Status.init(myCharacter1, Vector2(0, 3))
    val me2 = Status.init(myCharacter2, Vector2(1, 3))
    val me3 = Status.init(myCharacter3, Vector2(0, 4))
    val me4 = Status.init(myCharacter4, Vector2(1, 4))
    val enemy1 = Status.init(enemyCharacter1, Vector2(4, 3))
    val enemy2 = Status.init(enemyCharacter2, Vector2(5, 4))
    val enemy3 = Status.init(enemyCharacter3, Vector2(5, 5))
    return GameState(Side.ME, Fields.STAGE_1_1, listOf(me1, me2, me3, me4, enemy1, enemy2, enemy3))
}
