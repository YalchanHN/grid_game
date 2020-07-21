package business.logic;

import data.model.Cell;
import data.model.Grid;
import data.model.enums.CellType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GridGenerationCreator {

    public Grid createZeroGeneration(int gridWidth, int gridHeight) {
        Grid grid = new Grid(gridWidth, gridHeight);
        int cellValue = 0;

        for (int i = 0; i < grid.getRowSize(); i++) {
            for (int j = 0; j < grid.getColSize(); j++) {
                cellValue = ThreadLocalRandom.current().nextInt(0, 1 + 1);
                grid.getCellGrid()[i][j] = new Cell(i, j, CellType.values()[cellValue]);
            }
        }
        return grid;
    }

    public Grid createNextGeneration(Grid grid) {
        int rowSize = grid.getRowSize();
        int colSize = grid.getColSize();
        Grid nextGenerationGrid = new Grid(colSize, rowSize);

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                nextGenerationGrid.getCellGrid()[i][j] = new Cell(i, j,
                        applyNewGenerationRulesOnCell(grid.getCellGrid()[i][j], grid)
                );
            }
        }
        return nextGenerationGrid;
    }


    private CellType applyNewGenerationRulesOnCell(Cell cell, Grid grid) {
        long greenNeighboursCount = getCellNeighbours(cell, grid)
                .stream()
                .filter(c -> c.getCellType().equals(CellType.GREEN))
                .count();
        if (cell.getCellType().equals(CellType.RED)) {
            return CellType.values()[applyRedCellRules(greenNeighboursCount)];
        }
        return CellType.values()[applyGreenCellRules(greenNeighboursCount)];
    }

    private int applyGreenCellRules(long greenNeighboursCount) {
        if ((greenNeighboursCount == 2) || (greenNeighboursCount == 3) || (greenNeighboursCount == 6))
            return 1;
        return 0;
    }

    private int applyRedCellRules(long greenNeighboursCount) {
        if (greenNeighboursCount == 3 || greenNeighboursCount == 6)
            return 1;
        return 0;
    }

    private List<Cell> getCellNeighbours(Cell cell, Grid grid) {
        int currentCellX = cell.getX();
        int currentCellY = cell.getY();
        int rowSize = grid.getRowSize();
        int colSize = grid.getColSize();

        List<Cell> neighbourList = new ArrayList<>();
        if (currentCellX != 0)
            neighbourList.add(grid.getCellGrid()[currentCellX - 1][currentCellY]);
        if (currentCellX != 0 && currentCellY != colSize - 1)
            neighbourList.add(grid.getCellGrid()[currentCellX - 1][currentCellY + 1]);
        if (currentCellY != colSize - 1)
            neighbourList.add(grid.getCellGrid()[currentCellX][currentCellY + 1]);
        if (currentCellX != rowSize - 1 && currentCellY != colSize - 1)
            neighbourList.add(grid.getCellGrid()[currentCellX + 1][currentCellY + 1]);
        if (currentCellX != rowSize - 1)
            neighbourList.add(grid.getCellGrid()[currentCellX + 1][currentCellY]);
        if (currentCellX != rowSize - 1 && currentCellY != 0)
            neighbourList.add(grid.getCellGrid()[currentCellX + 1][currentCellY - 1]);
        if (currentCellY != 0)
            neighbourList.add(grid.getCellGrid()[currentCellX][currentCellY - 1]);
        if (currentCellX != 0 && currentCellY != 0)
            neighbourList.add(grid.getCellGrid()[currentCellX - 1][currentCellY - 1]);
        return neighbourList;
    }
}
