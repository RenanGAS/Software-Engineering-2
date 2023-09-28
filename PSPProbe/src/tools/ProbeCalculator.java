package src.tools;

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

	private Double summationXy;

	public ProbeCalculator(LinkedList linkedList) {
		this.linkedList = linkedList;
		this.xAverage = 0.0;
		this.yAverage = 0.0;
		this.summationX = 0.0;
		this.summationY = 0.0;
		this.summationX2 = 0.0;
		this.summationY2 = 0.0;
		this.summationXy = 0.0;
	}

	/**
	 * Itera sobre as listas de valores de X e Y da respectiva LinkedList, calculando valores que serão usados posteriormente em LRM
	 */
	public void precalculateSubOperations() throws IndexOutOfBoundsException {
		List<Double> xList = this.linkedList.getXvalues();
		List<Double> yList = this.linkedList.getYvalues();

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

			this.summationXy += xValue * yValue;
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

	public Double getSummationXp() {
		return this.summationX2;
	}

	public Double getSummationYp() {
		return this.summationY2;
	}

	public Double getSummationXy() {
		return this.summationXy;
	}
}
