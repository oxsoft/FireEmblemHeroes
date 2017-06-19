import java.awt.*
import javax.swing.JPanel

class GameView(var gameState: GameState) : JPanel() {
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

        gameState.status.forEach {
            when (it.character.side) {
                Side.ME -> g.color = Color.BLUE
                Side.ENEMY -> g.color = Color.RED
            }
            g.fillCircle(it.position * 100, 100)

            when (it.character.weapon?.type?.getAttackType()) {
                AttackType.RED -> g.color = Color.RED
                AttackType.BLUE -> g.color = Color.BLUE
                AttackType.GREEN -> g.color = Color.GREEN
                AttackType.NONE -> g.color = Color.GRAY
                null -> g.color = Color.WHITE
            }
            g.fillCircle(it.position * 100 + Vector2(3, 3), 94)

            g.color = Color.WHITE
            g.drawString(it.hp.toString(), it.position.x * 100 + 30, it.position.y * 100 + 60)
        }
    }
}