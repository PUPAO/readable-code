package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.in.ConsoleInputHandler;
import cleancode.minesweeper.tobe.in.ConsoleOutputHandler;

public class Minesweeper implements GameRunnable, GameInitialize {
    private final GameBoard gameBoard;
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private static int gameStatus = 0;

    public Minesweeper(GameLevel gameLevel) {
        gameBoard = new GameBoard(gameLevel);
    }


    @Override
    public void initialize() {
        gameBoard.initializedGame();
    }

    @Override
    public void run() {
        consoleOutputHandler.showGameStartComments();

        while (true) {
            try {
                consoleOutputHandler.showBoard(gameBoard);

                if (doesUserWinTheGame()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseTheGame()) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }

                String cellInput = getCellInputFromUser();
                String userActionInput = getUserActionInputFromUser();
                actOnCell(cellInput, userActionInput);
            } catch (GameException e) {
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printWarningMessage();
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
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private String getCellInputFromUser() {
        consoleOutputHandler.printCommentForSelectingCell();
        return consoleInputHandler.getUserInput();
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
