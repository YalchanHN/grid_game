import business.logic.Game;
import business.logic.GridGame;
import business.logic.GridGenerationCreator;
import business.logic.util.UserInputReader;
import business.logic.util.UserInputValidator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserInputReader reader = new UserInputReader(new Scanner(System.in),new UserInputValidator());
        Game gridGame = new GridGame(new GridGenerationCreator(),reader);
        gridGame.startGame();
    }
}
