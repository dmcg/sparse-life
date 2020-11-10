import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
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

    @Test
    fun `cell with 1 neighbour dies`() {
        val board: Board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val next: Board = board.step()
        assertEquals(next, setOf(0 to 1))
    }

    @Test
    fun `neighbours of a cell`() {
        val cell = 1 to 1
        cell.neighbours()
    }
}

private fun Cell.neighbours(): List<Cell> {
    return emptyList()
}

fun Board.step(): Board = setOf(0 to 1)


typealias Cell = Pair<Int, Int>
typealias Board = Set<Cell>
fun boardOf(vararg liveCells: Cell) = setOf(*liveCells)
