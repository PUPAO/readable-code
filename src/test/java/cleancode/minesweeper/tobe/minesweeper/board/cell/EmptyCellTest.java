package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyCellTest {
    @Test
    void testGetSnapshotOpened() {
        System.out.println("EmptyCellTest.testGetSnapshotOpened");

        EmptyCell emptyCell = new EmptyCell();
        emptyCell.open();
        CellSnapshot snapshot = emptyCell.getSnapshot();
        assertThat(CellSnapshot.ofEmpty()).isEqualTo(snapshot);
    }
}
