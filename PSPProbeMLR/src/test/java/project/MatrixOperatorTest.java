package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class MatrixOperatorTest {

    @Test
    void defaultTestCase() throws IOException {
        MLRModel mlr = new MLRModel();

        mlr.setFeaturesData("./datasets/features.txt", 6, 4);
        mlr.setTargetData("./datasets/target.txt", 6, 1);
        mlr.run();

        assertEquals(6.7013365364, mlr.getCoefficient(0), .01);
        assertEquals(0.0783660367, mlr.getCoefficient(1), .01);
        assertEquals(0.0150413312, mlr.getCoefficient(2), .01);
        assertEquals(0.2460563326, mlr.getCoefficient(3), .01);
    }

    @Test
	void errorMatrixMultiplication() throws IOException {
        final MLRModel mlr = new MLRModel();

        mlr.setFeaturesData("./datasets/features.txt", 6, 4);
        mlr.setTargetData("./datasets/targetFailure.txt", 8, 1);

		Throwable exceptionMultiplication = assertThrows(IOException.class, () -> mlr.run());
        assertEquals("Não é possível multiplicar estas matrizes", exceptionMultiplication.getMessage());
	}
}
