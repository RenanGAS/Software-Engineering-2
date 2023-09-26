package src.tools;

import java.io.IOException;
import java.util.List;

/*
 * ProbeCalculator: Calculadora relacionada a uma LinkedList. Faz o cálculo de operações repetidas durante o PSP Probe.
 */

public class ProbeCalculator {
	private LinkedList linkedList;
	private Double xAverage;
	private Double yAverage;
	private Double summationX;
	private Double summationY;
	private Double summationX2;
	private Double summationY2;
	private Double summationXY;
	
	public ProbeCalculator(LinkedList linkedList) {
		this.linkedList = linkedList;
		this.xAverage= -1.0;
		this.yAverage= -1.0;
		this.summationX= -1.0;
		this.summationY= -1.0;
		this.summationX2= -1.0;
		this.summationY2= -1.0;
		this.summationXY= -1.0;
	}
	
	/**
	 * Itera sobre as listas de valores de X e Y da respectiva LinkedList, calculando valores que serão usados posteriormente em LRM
	 */
	public void precalculateSubOperations() throws IndexOutOfBoundsException {
		List<Double> xList = this.linkedList.getXValues();
		List<Double> yList = this.linkedList.getYValues();
		
		System.out.println(xList.toString());
		System.out.println(yList.toString());
		
		int numElements = this.linkedList.getNumElements();
		
		for (int i = 0; i < numElements; i++) {
			Double xValue = xList.get(i);
			Double yValue = yList.get(i);
			
			this.xAverage += xValue;
			this.yAverage += yValue;
			
			this.summationX += xValue;
			this.summationY += yValue;
			
			this.summationX2 += xValue * xValue;
			this.summationY2 += yValue * yValue;
			
			this.summationXY += xValue * yValue;
		}
		
		this.xAverage /= numElements;
		this.yAverage /= numElements;
	}

	public Double getxAverage() {
		return this.xAverage;
	}

	public Double getyAverage() {
		return this.yAverage;
	}

	public Double getSummationX() {
		return this.summationX;
	}

	public Double getSummationY() {
		return this.summationY;
	}

	public Double getSummationX2() {
		return this.summationX2;
	}

	public Double getSummationY2() {
		return this.summationY2;
	}

	public Double getSummationXY() {
		return this.summationXY;
	}
}