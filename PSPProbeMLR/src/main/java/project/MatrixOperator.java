package project;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;

/**
  * Implementa operações sobre matrizes.
  */
public class MatrixOperator {
    
    /**
     * Cria uma matriz a partir de um arquivo.
     *
     * @param filePath Caminho do arquivo.
     * @param numRows Número de linhas da matriz.
     * @param numColumns Número de colunas da matriz.
     * @return Objeto MMatrix resultante.
     */   
    public MMatrix create(String filePath, int numRows, int numColumns) throws IOException {
        File file = new File(filePath);

        double [][] matrix = new double[numRows][numColumns];

        try (Scanner scanner = new Scanner(file)) {
            int rowIndex = 0;

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.isEmpty()) {
                    break;
                }

                Pattern pattern = Pattern.compile(" ", Pattern.CASE_INSENSITIVE);

                String[] objectValues;

                objectValues = pattern.split(input);

                if (objectValues.length != numColumns) {
                    throw new IOException("Número de colunas inválido.");
                }

                for (int i=0; i<numColumns; i++) {
                    String stringValue = objectValues[i].replace(",", ".");
                    matrix[rowIndex][i] = Double.parseDouble(stringValue);
                }

                rowIndex++;

                if (rowIndex > numRows) {
                    throw new IOException("Número de linhas inválido.");
                }
            }
        }

        MMatrix matrixObj = new MMatrix(matrix, numRows, numColumns);

        return matrixObj;
    }

    /**
     * Calcula a multiplicação de duas matrizes.
     *
     * @param matrixA Primeira matriz.
     * @param matrixB Segunda matriz.
     * @return Objeto MMatrix resultante.
     */   
    public MMatrix multiply(MMatrix matrixA, MMatrix matrixB) throws IOException {
        if (matrixA.getNumColumns() != matrixB.getNumRows()) {
            throw new IOException("Não é possível multiplicar estas matrizes");
        }

        int nRowsA = matrixA.getNumRows();
        int nColsA = matrixA.getNumColumns();
        int nColsB = matrixB.getNumColumns();

        double[][] matrixResult = new double[nRowsA][nColsB];

        for (int i=0; i<nRowsA; i++) {
            for (int j=0; j<nColsB; j++) {
                double sum = 0;

                for(int k=0; k<nColsA; k++) {
                    sum += matrixA.getElement(i, k) * matrixB.getElement(k, j);
                }

                matrixResult[i][j] = sum;
            }
        }

        MMatrix matrixObj = new MMatrix(matrixResult, nRowsA, nColsB);

        return matrixObj;
    }

    /**
     * Calcula a inversa de uma matriz.
     *
     * @param matrix Matriz alvo.
     * @return Objeto MMatrix resultante.
     */   
    public MMatrix inverse(MMatrix matrix) throws IOException {
        if (matrix.getNumRows() != matrix.getNumColumns()) {
            throw new IOException("Não é possível calcular a inversa desta matriz");
        }

        int nRows = matrix.getNumRows();
        int nCols = matrix.getNumColumns();

        double[][] matrixOfMinors = new double[nRows][nCols];

        for(int i=0; i<nRows; i++) {
            for (int j=0; j<nCols; j++) {
                MMatrix cutMatrix = cut(i, j, matrix);
                matrixOfMinors[j][i] = determinant(cutMatrix);
            }
        }

        MMatrix minors = new MMatrix(matrixOfMinors, nRows, nCols);

        MMatrix cofactors = getCofactorMatrix(minors);

        MMatrix adjugate = transposed(cofactors);

        MMatrix cofactorsAdj = getCofactorMatrix(adjugate);

        double matrixDeterminant = determinant(matrix);

        MMatrix inverseMatrix = multply(1/matrixDeterminant, cofactorsAdj);
        return inverseMatrix;
    }

    /**
     * Determina a matriz resultante do corte da linha e coluna de um elemento.
     *
     * @param rowElem Linha do elemento.
     * @param columnElem Coluna do elemento.
     * @param matrix Matriz em questão.
     * @return Objeto MMatrix resultante.
     */
    public MMatrix cut(int rowElem, int columnElem, MMatrix matrix) {
        // Pegar todos elementos da matriz que não pertencem aquela linha e coluna
        // O número de elementos restantes é igual a matrix.getNumRows()^2 - (numRows * 2 - 1)
        // Vou ter que percorrer a matriz para pegar os elementos não excluidos e colocar na matriz nova

        int nRows = matrix.getNumRows();
        int nCols = matrix.getNumColumns();

        int newMatrixDimension = nRows - 1;

        double[][] newMatrix = new double[newMatrixDimension][newMatrixDimension];
        int newI = 0;
        int newJ = 0;

        for (int i = 0; i < nRows; i++) {
            if (i == rowElem) {
                continue; 
            }

            for (int j = 0; j < nCols; j++) {
                if (j == columnElem) {
                    continue; 
                }

                newMatrix[newI][newJ] = matrix.getElement(i, j); 
                newJ++;
            } 
 
            newJ = 0;
            newI++;
        }

        MMatrix matrixObj = new MMatrix(newMatrix, newMatrixDimension, newMatrixDimension);

        return matrixObj;
    }

    /**
     * Calcula a determinante de uma matriz por meio da Expansão de Laplace.
     *
     * @param rowElem Linha do elemento.
     * @param columnElem Coluna do elemento.
     * @param matrix Matriz em questão.
     * @return Objeto MMatrix resultante.
     */
    public double determinant(MMatrix matrix) {
        if (matrix.getNumRows() == 2) {
            return (matrix.getElement(0, 0) * matrix.getElement(1, 1)) - (matrix.getElement(0, 1) * matrix.getElement(1, 0));            
        }

        if (matrix.getNumRows() == 3) {
           double firstTerm = matrix.getElement(0, 0) * (matrix.getElement(1,1) * matrix.getElement(2,2) - matrix.getElement(1,2) * matrix.getElement(2,1));
           double secondTerm = matrix.getElement(0,1) * (matrix.getElement(1,0) * matrix.getElement(2,2) - matrix.getElement(1,2) * matrix.getElement(2,0));
           double thirdTerm = matrix.getElement(0, 2) * (matrix.getElement(1,0) * matrix.getElement(2,1) - matrix.getElement(1,1) * matrix.getElement(2,0));

           return firstTerm - secondTerm + thirdTerm;
        }

        double matrixDeterminant = 0.0;
        boolean negativeSignal = false;

        for (int i = 0; i < matrix.getNumColumns(); i++) {
            MMatrix cutMatrix = cut(0, i, matrix);
            double cutMatrixDeterminant = determinant(cutMatrix);

           if (!negativeSignal) {
               matrixDeterminant += matrix.getElement(0, i) * cutMatrixDeterminant;
               negativeSignal = true;
           } else { 
               matrixDeterminant -= matrix.getElement(0, i) * cutMatrixDeterminant;
               negativeSignal = false;
           }
        }

        return matrixDeterminant;
    }

    /**
     * Calcula a Matriz de Cofatores de uma matriz.
     *
     * @param matrix Matriz alvo.
     * @return Objeto MMatrix resultante.
     */
    public MMatrix getCofactorMatrix(MMatrix matrix) {
        int nRows = matrix.getNumRows();
        int nCols = matrix.getNumColumns();

        double[][] newMatrix = new double[nRows][nCols];

        boolean negativeSignal = false;

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (!negativeSignal) {
                    newMatrix[i][j] = matrix.getElement(i, j);
                    negativeSignal = true;
                } else { 
                    newMatrix[i][j] = matrix.getElement(i, j) * -1;
                    negativeSignal = false;
                }
            } 
        }

        MMatrix matrixObj = new MMatrix(newMatrix, nRows, nCols);

        return matrixObj;
    }

    /**
     * Calcula a multiplicação de um escalar por uma matriz.
     *
     * @param num Valor escalar.
     * @param matrix Matriz em questão.
     * @return Objeto MMatrix resultante.
     */
    public MMatrix multply(double num, MMatrix matrix) {
        int nRows = matrix.getNumRows();
        int nCols = matrix.getNumColumns();

        double[][] newMatrix = new double[nRows][nCols];

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                newMatrix[i][j] = matrix.getElement(i, j) * num;
            } 
        }

        MMatrix matrixObj = new MMatrix(newMatrix, nRows, nCols);

        return matrixObj;
    }

    /**
     * Calcula a transposta de uma matriz.
     *
     * @param matrix Matriz alvo.
     * @return Objeto MMatrix resultante.
     */   
    public MMatrix transposed(MMatrix matrix) {
        int nRows = matrix.getNumRows();
        int nCols = matrix.getNumColumns();

        double[][] matrixResult = new double[nCols][nRows];

        for(int i=0; i<nRows; i++) {
            for (int j=0; j<nCols; j++) {
                matrixResult[j][i] = matrix.getElement(i, j);
            }
        }

        MMatrix matrixObj = new MMatrix(matrixResult, nCols, nRows);

        return matrixObj;
    }

     /**
      * Mostra uma matriz.
      *
      * @return Print da matriz. 
      */
    public void printMatrix(MMatrix matrix) {
        for (int i = 0; i < matrix.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumColumns(); j++) {
                System.out.print(String.valueOf(matrix.getElement(i, j)) + " "); 
            }

            System.out.println("");
        }
    }
}
