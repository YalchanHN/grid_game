package util;

public class UserInputValidator {

    public UserInputValidator() {
    }

    public boolean areGridMeasurementsValid(String height, String weight) {
        boolean isValid = false;
        int gridHeight = 0;
        int gridWeight = 0;

        if (this.isNumber(height) && this.isNumber(weight)) {
            gridHeight = Integer.parseInt(height);
            gridWeight = Integer.parseInt(weight);
            isValid = 0 < gridWeight && gridWeight <= gridHeight && gridHeight < 1000;
        }
        return isValid;
    }

    public boolean areCellCoordinatesValid(String cellX, String cellY, int gridX, int gridY) {
        boolean isValid = false;
        int cellXCoordinate = 0;
        int cellYCoordinate = 0;
        if (this.isNumber(cellX) && this.isNumber(cellY)) {
            cellXCoordinate = Integer.parseInt(cellX);
            cellYCoordinate = Integer.parseInt(cellY);
            isValid = ((cellXCoordinate >= 0 && cellXCoordinate < gridX) && (cellYCoordinate >= 0 && cellYCoordinate < gridY));
        }

        return isValid;
    }

    public boolean isCountGenerationsValid(String countGenerations){
        return this.isNumber(countGenerations);
    }

    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (
                NumberFormatException ex) {
            System.out.println("Not a number");
            return false;
        }
    }


}
