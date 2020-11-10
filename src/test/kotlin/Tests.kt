import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Tests {

    @Test
    fun `create board`() {
        val board: Board = boardOf(0 to 0, 0 to 1, 0 to 2)
    }

    @Test
    fun `cell with 2 neighbours lives`() {
        val board: Board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val next: Board = board.step()
        assertTrue(next.contains(0 to 1))
    }

}


typealias Board = Set<Pair<Int, Int>>
fun boardOf(vararg liveCells: Pair<Int, Int>) = setOf(*liveCells)
fun Board.step(): Board = this
