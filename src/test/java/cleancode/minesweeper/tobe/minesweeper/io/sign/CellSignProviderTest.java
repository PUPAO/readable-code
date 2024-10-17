package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellSignProviderTest {

    @Test
    void testEmptySign() {
        System.out.println("CellSignProviderTest.testEmptySign");
        CellSnapshot snapshot = CellSnapshot.of(CellSnapshotStatus.EMPTY, 0);
        assertEquals("■", CellSignProvider.findCellSignFrom(snapshot));
    }

    @Test
    void landMine() {
        System.out.println("CellSignProviderTest.landMine");
        CellSnapshot snapshot = CellSnapshot.of(CellSnapshotStatus.LAND_MINE, 0);
        assertEquals("☼", CellSignProvider.findCellSignFrom(snapshot));
    }
}
