import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun `create board`() {
        val board: Board = setOf(0 to 0, 0 to 1, 0 to 2)
    }
}

typealias Board = Set<Pair<Int, Int>>