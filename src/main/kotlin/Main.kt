import javax.swing.JFrame

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val frame = JFrame()
        frame.setSize(620, 840)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val panel = GameView(initGameState())
        frame.add(panel)
        frame.isVisible = true

        (1..10000).forEach {
            (0..panel.gameState.character.size - 1).forEach {
                val me = it
                var newState: GameState? = null
                (0..panel.gameState.character.size - 1).forEach {
                    newState = newState ?: panel.gameState.attack(me, it, listOf())
                }
                newState = newState ?: panel.gameState.move(it, listOf(Direction.values()[(Math.random() * 4).toInt()]))
                newState?.let {
                    panel.gameState = it
                    panel.repaint()
                    Thread.sleep(100)
                }
            }
            if (panel.gameState.character.filter { it.side == Side.ME }.all { it.hp == 0 }) return@forEach
            if (panel.gameState.character.filter { it.side == Side.ENEMY }.all { it.hp == 0 }) return@forEach
            panel.gameState = panel.gameState.changeTurn()
        }
    }

    private fun initGameState(): GameState {
        val me1 = Character(Side.ME, 19, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.AX), null, null, null, null, null, Vector2(0, 3))
        val me2 = Character(Side.ME, 16, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.NINJA), null, null, null, null, null, Vector2(1, 3))
        val me3 = Character(Side.ME, 16, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null, Vector2(0, 4))
        val me4 = Character(Side.ME, 15, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.GREEN_MAGIC), null, null, null, null, null, Vector2(1, 4))
        val enemy1 = Character(Side.ENEMY, 17, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SPEAR), null, null, null, null, null, Vector2(4, 3))
        val enemy2 = Character(Side.ENEMY, 15, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null, Vector2(5, 4))
        val enemy3 = Character(Side.ENEMY, 13, 5, 1, 1, 1, MotionType.WALK, Weapon(WeaponType.SWORD), null, null, null, null, null, Vector2(5, 5))
        return GameState(Side.ME, Fields.STAGE_1_1, listOf(me1, me2, me3, me4, enemy1, enemy2, enemy3))
    }
}
