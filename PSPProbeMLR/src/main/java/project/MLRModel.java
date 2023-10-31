package project;

import java.io.IOException;

/**
  * Modelo de Regressão Linear Múltipla para predição de tempo de desenvolvimento de software.
  */
public class MLRModel {
    MMatrix features;
    MMatrix target;
    MMatrix coefficients;
    MatrixOperator matrixOp;

    public MLRModel() {
        this.matrixOp = new MatrixOperator();
    }

     /**
      * Transfere dados do arquivo de features para uma matriz de doubles.
      *
      * @param filePath Caminho do arquivo de features.
      * @return Estabelece a matriz de features do modelo.
      */
    public void setFeaturesData(String filePath, int numRows, int numCols) throws IOException {
        this.features = this.matrixOp.create(filePath, numRows, numCols);
    }

     /**
      * Transfere dados do arquivo de target para uma matriz de doubles.
      *
      * @param filePath Caminho do arquivo de target.
      * @return Estabelece a matriz de target do modelo.
      */
    public void setTargetData(String filePath, int numRows, int numCols) throws IOException {
        this.target = this.matrixOp.create(filePath, numRows, numCols);
    }

     /**
      * Calcula a matriz de coeficientes com base na matriz de features e target.
      *
      * @return Estabelece a matriz de coeficientes do modelo.
      */
    public void run() throws IOException {
        MMatrix featuresDataTransposed = this.matrixOp.transposed(this.features);

        MMatrix firstTerm = this.matrixOp.inverse(this.matrixOp.multiply(featuresDataTransposed, this.features));

        MMatrix secondTerm = this.matrixOp.multiply(featuresDataTransposed, this.target);

        this.coefficients = this.matrixOp.multiply(firstTerm, secondTerm);
    }

    /**
     * Retorna o número de coeficientes.
     *
     * @return Valor inteiro do número de coeficientes.
     */
    public int getNumCoefficients() {
        return this.coefficients.getNumRows();
    }

     /**
      * Retorna o coeficiente no dado index.
      *
      * @return Valor do coeficiente. 
      */
    public double getCoefficient(int index) {
        return this.coefficients.getElement(index, 0); 
    }
}

