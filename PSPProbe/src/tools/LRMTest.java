package src.tools;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.lang.Math;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class LRMTest {
	
	DataFrame dfTrainingSet;
	
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
		dfTrainingSet.create("./datasets/History.txt");
	}

	@Test
	void testCase1() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(1, 3);
		
		LRM lrModel = new LRM(llTrainingSet);
		lrModel.train();

		assertEquals(roundDecimalNumber(-22.55), roundDecimalNumber(lrModel.getBeta0()));
		assertEquals(roundDecimalNumber(1.7279), roundDecimalNumber(lrModel.getBeta1()));
		assertEquals(roundDecimalNumber(0.9545), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.9111), roundDecimalNumber(lrModel.getR2()));
		
		assertEquals(roundDecimalNumber(644.429), roundDecimalNumber(lrModel.predict((double) 386)));
	}
	
	@Test
	void testCase2() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(1, 4);
		
		LRM lrModel = new LRM(llTrainingSet);
		lrModel.train();
    	
		assertEquals(roundDecimalNumber(-4.039), roundDecimalNumber(lrModel.getBeta0()));
		assertEquals(roundDecimalNumber(0.1681), roundDecimalNumber(lrModel.getBeta1()));
		assertEquals(roundDecimalNumber(0.9333), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.8711), roundDecimalNumber(lrModel.getR2()));
		
		assertEquals(roundDecimalNumber(60.858), roundDecimalNumber(lrModel.predict((double) 386)));
	}
	
	@Test
	void testCase3() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(2, 3);
		
		LRM lrModel = new LRM(llTrainingSet);
		lrModel.train();
    	
		assertEquals(roundDecimalNumber(-23.92), roundDecimalNumber(lrModel.getBeta0()));
		assertEquals(roundDecimalNumber(1.43097), roundDecimalNumber(lrModel.getBeta1()));
		assertEquals(roundDecimalNumber(0.9631), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.9276), roundDecimalNumber(lrModel.getR2()));
		
		assertEquals(roundDecimalNumber(528.4294), roundDecimalNumber(lrModel.predict((double) 386)));
	}
	
	@Test
	void testCase4() throws IOException {
		LinkedList llTrainingSet = dfTrainingSet.getSubset(2, 4);
		
		LRM lrModel = new LRM(llTrainingSet);
		lrModel.train();
    	
		assertEquals(roundDecimalNumber(-4.604), roundDecimalNumber(lrModel.getBeta0()));
		assertEquals(roundDecimalNumber(0.140164), roundDecimalNumber(lrModel.getBeta1()));
		assertEquals(roundDecimalNumber(0.9480), roundDecimalNumber(lrModel.getRxy()));
		assertEquals(roundDecimalNumber(0.8988), roundDecimalNumber(lrModel.getR2()));
		
		assertEquals(roundDecimalNumber(49.4994), roundDecimalNumber(lrModel.predict((double) 386)));
	}
}
