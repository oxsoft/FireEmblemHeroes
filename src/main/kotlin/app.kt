import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel


fun main(args: Array<String>) {
    val me1 = Unit(Side.ME, 19, 1, 1, 1, 1, MotionType.WALK, WeaponType.AX, 1, 1, 1, 1, 1, 1)
    val me2 = Unit(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, WeaponType.NINJA, 1, 1, 1, 1, 1, 1)
    val me3 = Unit(Side.ME, 16, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, 1, 1, 1, 1, 1, 1)
    val me4 = Unit(Side.ME, 15, 1, 1, 1, 1, MotionType.WALK, WeaponType.GREEN_MAGIC, 1, 1, 1, 1, 1, 1)
    val enemy1 = Unit(Side.ENEMY, 17, 1, 1, 1, 1, MotionType.WALK, WeaponType.SPEAR, 1, 1, 1, 1, 1, 1)
    val enemy2 = Unit(Side.ENEMY, 15, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, 1, 1, 1, 1, 1, 1)
    val enemy3 = Unit(Side.ENEMY, 13, 1, 1, 1, 1, MotionType.WALK, WeaponType.SWORD, 1, 1, 1, 1, 1, 1)
    me1.x = 0
    me1.y = 3
    me2.x = 1
    me2.y = 3
    me3.x = 0
    me3.y = 4
    me4.x = 1
    me4.y = 4
    enemy1.x = 4
    enemy1.y = 3
    enemy2.x = 5
    enemy2.y = 4
    enemy3.x = 5
    enemy3.y = 5
    createAndShowGUI(GameState(Fields.STAGE_1_1, arrayOf(me1, me2, me3, me4, enemy1, enemy2, enemy3)))
}

private fun createAndShowGUI(gameState: GameState) {
    val frame = JFrame()
    frame.setSize(630, 850)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true
    val panel = object : JPanel() {
        override fun paint(g: Graphics?) {
            super.paint(g)
            gameState.field.cells.withIndex().forEach {
                val y = it.index
                it.value.withIndex().forEach {
                    val x = it.index
                    when (it.value) {
                        CellType.NORMAL -> g?.color = Color.WHITE
                        CellType.FOREST -> g?.color = Color.GREEN
                        CellType.RIVER -> g?.color = Color.CYAN
                        CellType.WALL -> g?.color = Color.BLACK
                    }
                    g?.fillRect(x * 100, y * 100, 100, 100)
                }
            }
            gameState.units.forEach {
                when (it.side) {
                    Side.ME -> g?.color = Color.BLUE
                    Side.ENEMY -> g?.color = Color.RED
                }
                g?.fillOval(it.x * 100, it.y * 100, 100, 100)
                when (it.weaponType) {
                    WeaponType.SWORD -> g?.color = Color.RED
                    WeaponType.SPEAR -> g?.color = Color.BLUE
                    WeaponType.AX -> g?.color = Color.GREEN
                    WeaponType.BOW -> g?.color = Color.GRAY
                    WeaponType.NINJA -> g?.color = Color.GRAY
                    WeaponType.RED_MAGIC -> g?.color = Color.RED
                    WeaponType.BLUE_MAGIC -> g?.color = Color.BLUE
                    WeaponType.GREEN_MAGIC -> g?.color = Color.GREEN
                    WeaponType.WAND -> g?.color = Color.GRAY
                    WeaponType.RED_DRAGON -> g?.color = Color.RED
                    WeaponType.BLUE_DRAGON -> g?.color = Color.BLUE
                    WeaponType.GREEN_DRAGON -> g?.color = Color.GREEN
                }
                g?.fillOval(it.x * 100 + 3, it.y * 100 + 3, 94, 94)
                if (g is Graphics2D) {
                    g.color = Color.WHITE
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                    g.setFont(Font("", Font.PLAIN, 36))
                    g.drawString(it.hp.toString(), it.x * 100 + 30, it.y * 100 + 60)
                }
            }
        }
    }
    frame.add(panel)
}