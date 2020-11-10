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
        assertEquals(
            listOf(
                0 to 0, 0 to 1, 0 to 2,
                1 to 0, /*   */ 1 to 2,
                2 to 0, 2 to 1, 2 to 2,
            ),
            cell.neighbours()
        )
    }
}

private fun Cell.neighbours(): List<Cell> {
    val rw = this.first
    val cl = this.second
    return listOf(
        rw - 1 to cl - 1, rw - 1 to cl, rw - 1 to cl + 1,
        rw to cl - 1, /*             */ rw to cl + 1,
        rw + 1 to cl - 1, rw + 1 to cl, rw + 1 to cl + 1,
    )
}

fun Board.step(): Board = setOf(0 to 1)


typealias Cell = Pair<Int, Int>
typealias Board = Set<Cell>
fun boardOf(vararg liveCells: Cell) = setOf(*liveCells)
