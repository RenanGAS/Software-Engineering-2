package project;

public class MMatrix {
    int numRows;

    int numColumns;

    double[][] matrix;

    public MMatrix(double[][] matrix, int numRows, int numColumns) {
        this.matrix = matrix;
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    public double getElement(int row, int col) {
        return this.matrix[row][col];
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumColumns() {
        return this.numColumns;
    }
}
