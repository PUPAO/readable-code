package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.in.ConsoleInputHandler;
import cleancode.minesweeper.tobe.in.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.in.InputHandler;
import cleancode.minesweeper.tobe.in.OutputHandler;
import cleancode.minesweeper.tobe.level.Beginner;
import cleancode.minesweeper.tobe.level.GameLevel;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        Minesweeper minesweeper = new Minesweeper(gameLevel, outputHandler, inputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }
}
