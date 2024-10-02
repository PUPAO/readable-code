package cleancode.minesweeper.tobe.cell;

public class NumberCell extends Cell {
    private final int nearbyLandMineCounts;

    public NumberCell(int nearbyLandMineCounts) {
        this.nearbyLandMineCounts = nearbyLandMineCounts;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public String getSign() {
        if(isOpened) return String.valueOf(nearbyLandMineCounts);
        if(isFlagged) return FLAG_SIGN;
        return UNCHECKED_SIGN;
    }
}
