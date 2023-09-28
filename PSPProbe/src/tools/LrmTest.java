package src.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class LrmTest {
	DataFrame dfTrainingSet;

	String historyPath = "./datasets/History.txt";

	double roundDecimalNumber(double number) {
		if (number > -1 && number < 1) {
			BigDecimal bigDecimal = new BigDecimal(number);
			bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

			return bigDecimal.doubleValue();
		}

		return Math.floor(number);
	}

	@BeforeAll
	void initVariables() throws NumberFormatException, FileNotFoundException, IOException {
		dfTrainingSet = new DataFrame(10);
		dfTrainingSet.create(historyPath);
	}

	@Test
	void testCaseOne() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(1, 3);

		Lrm lrModel = new Lrm(llTrainingSet);
		lrModel.train();

		assertEquals(roundDecimalNumber(-22.55), roundDecimalNumber(lrModel.getBetaZero()));
		assertEquals(roundDecimalNumber(1.7279), roundDecimalNumber(lrModel.getBetaOne()));
		assertEquals(roundDecimalNumber(0.9545), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.9111), roundDecimalNumber(lrModel.getRpw()));

		assertEquals(roundDecimalNumber(644.429), roundDecimalNumber(lrModel.predict((double) 386)));
	}

	@Test
	void testCaseTwo() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(1, 4);

		Lrm lrModel = new Lrm(llTrainingSet);
		lrModel.train();

		assertEquals(roundDecimalNumber(-4.039), roundDecimalNumber(lrModel.getBetaZero()));
		assertEquals(roundDecimalNumber(0.1681), roundDecimalNumber(lrModel.getBetaOne()));
		assertEquals(roundDecimalNumber(0.9333), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.8711), roundDecimalNumber(lrModel.getRpw()));

		assertEquals(roundDecimalNumber(60.858), roundDecimalNumber(lrModel.predict((double) 386)));
	}

	@Test
	void testCaseThree() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(2, 3);

		Lrm lrModel = new Lrm(llTrainingSet);
		lrModel.train();

		assertEquals(roundDecimalNumber(-23.92), roundDecimalNumber(lrModel.getBetaZero()));
		assertEquals(roundDecimalNumber(1.43097), roundDecimalNumber(lrModel.getBetaOne()));
		assertEquals(roundDecimalNumber(0.9631), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.9276), roundDecimalNumber(lrModel.getRpw()));

		assertEquals(roundDecimalNumber(528.4294), roundDecimalNumber(lrModel.predict((double) 386)));
	}

	@Test
	void testCaseFour() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(2, 4);

		Lrm lrModel = new Lrm(llTrainingSet);
		lrModel.train();

		assertEquals(roundDecimalNumber(-4.604), roundDecimalNumber(lrModel.getBetaZero()));
		assertEquals(roundDecimalNumber(0.140164), roundDecimalNumber(lrModel.getBetaOne()));
		assertEquals(roundDecimalNumber(0.9480), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.8988), roundDecimalNumber(lrModel.getRpw()));

		assertEquals(roundDecimalNumber(49.4994), roundDecimalNumber(lrModel.predict((double) 386)));
	}

	@Test
	void testCaseError() throws IOException {
		DataFrame dfTrainingSet1 = new DataFrame(10);

		Throwable exception1 = assertThrows(IOException.class, () -> dfTrainingSet1.create("./datasets/History2.txt"));
	    	assertEquals("Invalid number of attributes in the object: 4", exception1.getMessage());

	    	dfTrainingSet1.create(historyPath);

	    	LinkedList llTrainingSet = dfTrainingSet1.getSubset(2, 4);

	    	Lrm lrModel = new Lrm(llTrainingSet);

	    	Throwable exception2 = assertThrows(IOException.class, () -> lrModel.getBetaZero());
	    	assertEquals("The model needs to be trained first", exception2.getMessage());
	}
}
