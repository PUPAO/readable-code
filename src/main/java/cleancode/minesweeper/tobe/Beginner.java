package cleancode.minesweeper.tobe;

public class Beginner implements GameLevel {
    @Override
    public int getRowSize() {
        return 8;
    }

    @Override
    public int getColSize() {
        return 10;
    }

    @Override
    public int getLandMindCount() {
        return 6;
    }
}
