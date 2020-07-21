package data.model;

public class Grid {
    private Cell[][] cellGrid;
    private int rowSize;
    private int colSize;

    public Grid(int colSize, int rowSize ) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.cellGrid = new Cell[this.rowSize][this.colSize];
    }

    public Cell[][] getCellGrid() {
        return cellGrid;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

}
