package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.GameBoard;

public class VeryBeginner implements GameLevel {
    @Override
    public int getRowSize() {
        return 4;
    }

    @Override
    public int getColSize() {
        return 5;
    }

    @Override
    public int getLandMindCount() {
        return 2;
    }
}
