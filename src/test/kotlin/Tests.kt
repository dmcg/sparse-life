import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Tests {

    @Test
    fun `create board`() {
        val board: Board = boardOf(0 to 0, 0 to 1, 0 to 2)
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

    @Test
    fun `count neighbours of a cell`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val result = board.map { board.countNeighbours(it) }
        assertEquals(listOf(1, 2, 1), result)
    }

    @Test
    fun `cell with 2 neighbours lives`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val next = board.step()
        assertTrue(next.contains(0 to 1))
    }

    @Test
    fun `cell with 1 neighbour dies`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val next = board.step()
        assertEquals(next, setOf(0 to 1))
    }

}


fun Board.step(): Board = this.filter { cell ->
    this.countNeighbours(cell) > 1
}.toSet()


typealias Cell = Pair<Int, Int>
typealias Board = Set<Cell>
fun boardOf(vararg liveCells: Cell) = setOf(*liveCells)

private fun Board.countNeighbours(cell: Cell): Int =
    cell.neighbours().count { it in this }

private fun Cell.neighbours(): List<Cell> {
    val rw = this.first
    val cl = this.second
    return listOf(
        rw - 1 to cl - 1, rw - 1 to cl, rw - 1 to cl + 1,
        rw to cl - 1, /*             */ rw to cl + 1,
        rw + 1 to cl - 1, rw + 1 to cl, rw + 1 to cl + 1,
    )
}