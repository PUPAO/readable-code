package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {
    
    @Test
    void testOf() {
        CellPosition position = CellPosition.of(3, 4);
        assertEquals(3, position.getRowIndex());
        assertEquals(4, position.getColIndex());
    }

    @Test
    void testEquals() {
        CellPosition position1 = CellPosition.of(3, 4);
        CellPosition position2 = CellPosition.of(3, 4);
        CellPosition position3 = CellPosition.of(5, 4);

        assertTrue(position1.equals(position2));
        assertFalse(position1.equals(position3));
    }

    @Test
    void testHashCode() {
        CellPosition position1 = CellPosition.of(3, 4);
        CellPosition position2 = CellPosition.of(3, 4);
        CellPosition position3 = CellPosition.of(5, 4);

        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    @Test
    void testIsRowIndexMoreThanOrEqual() {
        CellPosition position = CellPosition.of(3, 4);
        assertTrue(position.isRowIndexMoreThanOrEqual(2));
        assertFalse(position.isRowIndexMoreThanOrEqual(4));
    }

    @Test
    void testIsColIndexMoreThanOrEqual() {
        CellPosition position = CellPosition.of(3, 4);
        assertTrue(position.isColIndexMoreThanOrEqual(3));
        assertFalse(position.isColIndexMoreThanOrEqual(5));
    }

    @Test
    void testCalculatePositionBy() {
        CellPosition position = CellPosition.of(3, 4);
        RelativePosition relativePosition = RelativePosition.of(1, 2);
        CellPosition calculatedPosition = position.calculatePositionBy(relativePosition);
        assertEquals(4, calculatedPosition.getRowIndex());
        assertEquals(6, calculatedPosition.getColIndex());
    }

    @Test
    void testCanCalculatePositionBy() {
        CellPosition position = CellPosition.of(3, 4);
        RelativePosition relativePosition = RelativePosition.of(1, 2);
        assertTrue(position.canCalculatePositionBy(relativePosition));

        RelativePosition relativePosition2 = RelativePosition.of(-2, -3);
        assertFalse(position.canCalculatePositionBy(relativePosition2));
    }

    @Test
    void testIsRowIndexLessThan() {
        CellPosition position = CellPosition.of(3, 4);
        assertTrue(position.isRowIndexLessThan(5));
        assertFalse(position.isRowIndexLessThan(3));
    }

    @Test
    void testIsColIndexLessThan() {
        CellPosition position = CellPosition.of(3, 4);
        assertTrue(position.isColIndexLessThan(5));
        assertFalse(position.isColIndexLessThan(4));
    }
}
