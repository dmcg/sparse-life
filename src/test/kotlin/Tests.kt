import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Tests {

    @Test
    fun `count neighbours of a cell`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        val result = board.map { board.countNeighbours(it) }
        assertEquals(listOf(1, 2, 1), result)
    }

    @Test
    fun `cell with 1 neighbour dies`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        assertTrue(0 to 0 !in board.step())
        assertTrue(0 to 2 !in board.step())
    }

    @Test
    fun `cell with 2 neighbours lives`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        assertTrue(0 to 1 in board.step())
    }

    @Test
    fun `cell with 3 neighbours lives`() {
        val board = boardOf(0 to 0, 0 to 1, 1 to 1, 0 to 2)
        assertTrue(0 to 1 in board.step())
    }

    @Test
    fun `cell with 4 neighbours dies`() {
        val board = boardOf(0 to 0, 0 to 1, -1 to 1, 1 to 1, 0 to 2)
        assertTrue(0 to 1 !in board.step())
    }

    @Test
    fun `dead cell with 3 neighbours comes to live`() {
        val board = boardOf(0 to 0, 0 to 1, 0 to 2)
        assertTrue(1 to 1 in board.step())
    }
}

typealias Cell = Pair<Int, Int>
typealias Board = Set<Cell>
fun boardOf(vararg liveCells: Cell) = setOf(*liveCells)

fun Board.step(): Board =
    filter { cell ->
        shouldBeAlive(isAliveNow = true, liveNeighbours(cell).count())
    }.toSet() + flatMap { cell ->
        deadNeighbours(cell).filter { deadCell ->
            shouldBeAlive(isAliveNow = false, liveNeighbours(deadCell).count())
        }
    }

private fun shouldBeAlive(isAliveNow: Boolean, neighbourCount: Int) =
    (isAliveNow && neighbourCount in 2..3) ||
    (!isAliveNow && neighbourCount == 3)

private fun Board.countNeighbours(cell: Cell): Int =
    liveNeighbours(cell).count()

private fun Board.liveNeighbours(cell: Cell): List<Cell> =
    cell.neighbours().filter { it in this }

private fun Board.deadNeighbours(cell: Cell): List<Cell> =
    cell.neighbours().filter { it !in this }

private fun Cell.neighbours(): List<Cell> {
    val rw = this.first
    val cl = this.second
    return listOf(
        rw - 1 to cl - 1, rw - 1 to cl, rw - 1 to cl + 1,
        rw to cl - 1, /*             */ rw to cl + 1,
        rw + 1 to cl - 1, rw + 1 to cl, rw + 1 to cl + 1,
    )
}
