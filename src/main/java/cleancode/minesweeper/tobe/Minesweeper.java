package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.in.InputHandler;
import cleancode.minesweeper.tobe.in.OutputHandler;
import cleancode.minesweeper.tobe.level.GameLevel;

public class Minesweeper implements GameRunnable, GameInitialize {
    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private static int gameStatus = 0;

    public Minesweeper(GameLevel gameLevel, OutputHandler outputHandler, InputHandler inputHandler) {
        gameBoard = new GameBoard(gameLevel);
        this.outputHandler = outputHandler;
        this.inputHandler = inputHandler;
    }


    @Override
    public void initialize() {
        gameBoard.initializedGame();
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments();

        while (true) {
            try {
                outputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    outputHandler.showGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    outputHandler.showGameLosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.printWarningMessage();
            }
        }
    }

    private void actOnCell(String cellInput, String userActionInput) {
        int selectedCol = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRow = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flag(selectedRow, selectedCol);
            checkIfGameIsOver();
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            if (gameBoard.isLandMineCell(selectedRow, selectedCol)) {
                gameBoard.open(selectedRow, selectedCol);
                changeGameStatusToLose();
                return;
            }

            gameBoard.openSurroundedCells(selectedRow, selectedCol);
            checkIfGameIsOver();
            return;
        }

        System.out.println("잘못된 번호를 선택하셨습니다.");
    }

    private void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private String getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        return inputHandler.getUserInput();
    }

    private boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private void checkIfGameIsOver() {
        if (gameBoard.isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = 1;
    }

}
