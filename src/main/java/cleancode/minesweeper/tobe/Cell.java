package cleancode.minesweeper.tobe;

public class Cell {
    public static final String FLAG_SIGN = "⚑";
    public static final String LAND_MINE_SIGN = "*";
    public static final String UNCHECKED_SIGN = "□";
    public static final String EMPTY_SIGN = "■";

    private int nearbyLandMineCounts;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(int nearbyLandMineCounts, boolean landMines, boolean isFlaged, boolean isOpened) {
        this.nearbyLandMineCounts = nearbyLandMineCounts;
        this.isLandMine = landMines;
        this.isFlagged = isFlaged;
        this.isOpened = isOpened;
    }

    public static Cell of(int nearbyLandMineCounts, boolean landMines, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandMineCounts, landMines, isFlagged, isOpened);
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    public void turnOnLandMind() {
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandMineCounts = count;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandMineCounts != 0;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }

            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandMineCounts);
            }

            return EMPTY_SIGN;
        }

        if (isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
