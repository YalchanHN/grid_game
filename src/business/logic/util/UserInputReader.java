package business.logic.util;

import java.util.Scanner;

public class UserInputReader {

    private int gridX;
    private int gridY;
    private int cellXCoordinate;
    private int cellYCoordinate;
    private int countGenerations;
    private Scanner userInput;
    private UserInputValidator inputValidator;

    public UserInputReader(Scanner userInput, UserInputValidator inputValidator) {
        this.userInput = userInput;
        this.inputValidator = inputValidator;
        this.gridX = 0;
        this.gridY = 0;
        this.cellXCoordinate = 0;
        this.cellYCoordinate = 0;
        this.countGenerations = 0;
    }

    public void readGridMeasurements() {
        String height = null;
        String weight = null;
        boolean isValid = false;
        System.out.println("Please enter grid measurements");
        do {
            System.out.println("X <= Y <1000 ");

            System.out.print("Grid X = ");
            weight = this.userInput.nextLine();

            System.out.print("Grid Y = ");
            height = this.userInput.nextLine();

            isValid = this.inputValidator.areGridMeasurementsValid(height, weight);
            if (!isValid)
                System.out.println("Invalid size of grid. Please enter new measurements.");
        } while (!isValid);
        gridX = Integer.parseInt(weight);
        gridY = Integer.parseInt(height);
    }

    public void readCellCoordinates() {
        String cellX = null;
        String cellY = null;
        boolean isValid = false;
        System.out.println("Please enter searching cell  coordinates");
        do {
            System.out.println("X >= 0 And X<" + this.gridX + " Y >= 0 And Y<" + this.getGridY());

            System.out.print("Cell X = ");
            cellX = this.userInput.nextLine();

            System.out.print("Cell Y = ");
            cellY = this.userInput.nextLine();

            isValid = this.inputValidator.areCellCoordinatesValid(cellX, cellY, this.gridX, this.gridY);
            if (!isValid)
                System.out.println("Invalid cell coordinates. Please enter new ones");
        } while (!isValid);
        this.cellXCoordinate = Integer.parseInt(cellX);
        this.cellYCoordinate = Integer.parseInt(cellY);
    }

    public void readCountGenerations() {
        String countNewGenerations = null;
        boolean isValid = false;
        System.out.println("Please enter count of new generations ");
        do {
            System.out.print("N = ");
            countNewGenerations = this.userInput.nextLine();
            isValid = this.inputValidator.isCountGenerationsValid(countNewGenerations);
            if (!isValid)
                System.out.println("Invalid count of new generations");
        } while (!isValid);
        this.countGenerations = Integer.parseInt(countNewGenerations);

    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getCellXCoordinate() {
        return cellXCoordinate;
    }

    public int getCellYCoordinate() {
        return cellYCoordinate;
    }

    public int getCountGenerations() {
        return countGenerations;
    }
}
