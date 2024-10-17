package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.minesweeper.level.Advanced;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameBoardTest {

    @Test
    void getRowSize() {
        System.out.println("GameBoardTest.getRowSize");
        // given
        Advanced advanced = new Advanced();

        // when
        GameConfig gameConfig = new GameConfig(advanced, new ConsoleInputHandler(), new ConsoleOutputHandler());
        GameBoard gameBoard = new GameBoard(gameConfig.getGameLevel());

        // then
        assertThat(gameBoard.getRowSize()).isEqualTo(advanced.getRowSize());
    }

    @Test
    void getColSize() {
        System.out.println("GameBoardTest.getColSize");
        // given
        Advanced advanced = new Advanced();

        // when
        GameConfig gameConfig = new GameConfig(advanced, new ConsoleInputHandler(), new ConsoleOutputHandler());
        GameBoard gameBoard = new GameBoard(gameConfig.getGameLevel());

        // then
        assertThat(gameBoard.getColSize()).isEqualTo(advanced.getColSize());
    }
}
