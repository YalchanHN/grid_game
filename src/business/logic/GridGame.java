package business.logic;

import data.model.Grid;
import data.model.enums.CellType;
import business.logic.util.UserInputReader;

public class GridGame implements Game {
    private Grid grid;
    private GridGenerationCreator generationCreator;
    private UserInputReader userInputReader;

    public GridGame(GridGenerationCreator generationCreator, UserInputReader userInputReader) {
        this.generationCreator = generationCreator;
        this.userInputReader = userInputReader;
    }

    @Override
    public void startGame() {
        userInputReader.readGridMeasurements();
        this.grid = this.generationCreator.createZeroGeneration(
                this.userInputReader.getGridX(),
                this.userInputReader.getGridY()
        );
        System.out.println("Generation 0");
        printGrid();
        userInputReader.readCellCoordinates();
        userInputReader.readCountGenerations();
        System.out.println("The result is: " + this.getCountOfGreenGenerationsByCell(
                userInputReader.getCellXCoordinate(),
                userInputReader.getCellYCoordinate(),
                userInputReader.getCountGenerations())
        );
    }


    private int getCountOfGreenGenerationsByCell(int cellX, int cellY, int countGenerations) {

        int greenCellGenerationCounter = isCellGreen(cellX, cellY) ? 1 : 0;

        for (int i = 0; i < countGenerations; i++) {
            this.grid = generationCreator.createNextGeneration(this.grid);
            if (isCellGreen(cellX, cellY)) {
                greenCellGenerationCounter++;
            }
            System.out.println("Generation " + (i + 1));
            printGrid();

        }
        return greenCellGenerationCounter;
    }

    private boolean isCellGreen(int column, int row) {
        if (this.grid.getCellGrid()[row][column].getCellType().equals(CellType.GREEN))
            return true;
        return false;
    }

    private void printGrid() {
        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                System.out.print(
                        grid.getCellGrid()[i][j]
                                .getCellType()
                                .ordinal()
                                + " "
                );
            }
            System.out.println();
        }
        System.out.println();
    }
}
