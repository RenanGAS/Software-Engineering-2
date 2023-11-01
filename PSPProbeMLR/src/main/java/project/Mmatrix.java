package project;

public class Mmatrix {
        int numRows;

        int numColumns;

        double[][] matrix;

        public Mmatrix(double[][] matrix, int numRows, int numColumns) {
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
