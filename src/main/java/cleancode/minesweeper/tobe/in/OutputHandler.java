package cleancode.minesweeper.tobe.in;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;

public interface OutputHandler {
    void showGameStartComments();

    void showBoard(GameBoard board);

    String generatedColAlphabets(GameBoard board);

    void showGameWinningComment();

    void showGameLosingComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(GameException e);

    void printWarningMessage();
}
