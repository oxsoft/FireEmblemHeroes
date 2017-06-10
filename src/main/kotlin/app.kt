import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel

fun Graphics.fillRect(position: Vector2, size: Vector2) = this.fillRect(position.x, position.y, size.x, size.y)
fun Graphics.fillCircle(position: Vector2, radius: Int) = this.fillOval(position.x, position.y, radius, radius)

fun main(args: Array<String>) {
    val frame = JFrame()
    frame.setSize(630, 850)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    val gameState = initGameState()
    val panel = createGUI(gameState)
    frame.add(panel)
    frame.isVisible = true

    Thread.sleep(1000)
    gameState.move(gameState.characters[1], arrayOf(Direction.RIGHT))
    panel.repaint()
}

private fun initGameState(): GameState {
    val me1 = Character(Side.ME, 19, 1, 1, 1, 1, MotionType.WALK, WeaponType.AX, null, null, null, null, null, null)
    val me2 = Character(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, WeaponType.NINJA, null, null, null, null, null, null)
    val me3 = Character(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, null, null, null, null, null, null)
    val me4 = Character(Side.ME, 15, 1, 1, 1, 1, MotionType.WALK, WeaponType.GREEN_MAGIC, null, null, null, null, null, null)
    val enemy1 = Character(Side.ENEMY, 17, 1, 1, 1, 1, MotionType.WALK, WeaponType.SPEAR, null, null, null, null, null, null)
    val enemy2 = Character(Side.ENEMY, 15, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, null, null, null, null, null, null)
    val enemy3 = Character(Side.ENEMY, 13, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, null, null, null, null, null, null)
    me1.position = Vector2(0, 3)
    me2.position = Vector2(1, 3)
    me3.position = Vector2(0, 4)
    me4.position = Vector2(1, 4)
    enemy1.position = Vector2(4, 3)
    enemy2.position = Vector2(5, 4)
    enemy3.position = Vector2(5, 5)
    return GameState(Fields.STAGE_1_1, arrayOf(me1, me2, me3, me4, enemy1, enemy2, enemy3))
}

private fun createGUI(gameState: GameState): JPanel {
    return object : JPanel() {
        override fun paint(g: Graphics?) {
            super.paint(g)
            if (g !is Graphics2D) return
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g.setFont(Font("", Font.PLAIN, 36))

            gameState.field.cells.withIndex().forEach {
                val y = it.index
                it.value.withIndex().forEach {
                    val x = it.index
                    when (it.value) {
                        CellType.NORMAL -> g.color = Color.WHITE
                        CellType.FOREST -> g.color = Color.GREEN
                        CellType.RIVER -> g.color = Color.CYAN
                        CellType.WALL -> g.color = Color.BLACK
                    }
                    g.fillRect(Vector2(x, y) * 100, Vector2(100, 100))
                }
            }

            gameState.characters.forEach {
                when (it.side) {
                    Side.ME -> g.color = Color.BLUE
                    Side.ENEMY -> g.color = Color.RED
                }
                g.fillCircle(it.position * 100, 100)

                when (it.weaponType.getAttackType()) {
                    AttackType.RED -> g.color = Color.RED
                    AttackType.BLUE -> g.color = Color.BLUE
                    AttackType.GREEN -> g.color = Color.GREEN
                    AttackType.NONE -> g.color = Color.GRAY
                }
                g.fillCircle(it.position * 100 + Vector2(3, 3), 94)

                g.color = Color.WHITE
                g.drawString(it.hp.toString(), it.position.x * 100 + 30, it.position.y * 100 + 60)
            }
        }
    }
}