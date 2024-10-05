package cleancode.minesweeper.tobe.in;

import cleancode.minesweeper.tobe.position.CellPosition;

public interface InputHandler {
    String getUserInput();

    CellPosition getCellPositionFromUser();
}
