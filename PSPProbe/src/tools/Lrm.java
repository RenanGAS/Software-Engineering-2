package src.tools;

import java.io.IOException;

/*
 * Lrm: Modelo de Regressão Linear.
 */

public class Lrm {
	ProbeCalculator probeCalculator;

	LinkedList dadosTreinamento;

	boolean modelTrained;

	Double beta0;

	Double beta1;

	Double rXy;

	Double rpw;

	private String errorMessage = "The model needs to be trained first";

	public Lrm(LinkedList dadosTreinamento) {
		this.dadosTreinamento = dadosTreinamento;
		this.probeCalculator = new ProbeCalculator(dadosTreinamento);
		this.modelTrained = false;
	}

	/**
	 * Faz o cálculo de beta0, beta1, rXy e rpw usando os valores calculados em probeCalculator. Redireciona exceção de precalculateSubOperations()
	 */
	public void train() throws IndexOutOfBoundsException {
		this.probeCalculator.precalculateSubOperations();

		Double summationXy = this.probeCalculator.getSummationXy();
		Double summationXp = this.probeCalculator.getSummationXp();
		Double summationYp = this.probeCalculator.getSummationYp();
		Double summationX = this.probeCalculator.getSummationX();
		Double summationY = this.probeCalculator.getSummationY();
		Double xAverage = this.probeCalculator.getxAverage();
		Double yAverage = this.probeCalculator.getyAverage();

		int numElements = this.dadosTreinamento.getNumElements();

		this.beta1 = (summationXy - (numElements * xAverage * yAverage)) / (summationXp - (numElements * xAverage * xAverage));
		this.beta0 = yAverage - (this.beta1 * xAverage);
		this.rXy = ((numElements * summationXy) - (summationX * summationY))
				/ (Math.sqrt(((numElements * summationXp) - (summationX * summationX)) * ((numElements * summationYp) - (summationY * summationY))));
		this.rpw = this.rXy * this.rXy;

		this.modelTrained = true;
	}

	/**
	 * Faz a predição de um valor Y dado um valor X
	 */
	public Double predict(Double xVal) throws IOException {
		if (!this.modelTrained) {
			throw new IOException(errorMessage);
		}

		return this.beta0 + this.beta1 * xVal;
	}

	public Double getBetaZero() throws IOException {
		if (!this.modelTrained) {
			throw new IOException(errorMessage);
		}

		return this.beta0;
	}

	public Double getBetaOne() throws IOException {
		if (!this.modelTrained) {
			throw new IOException(errorMessage);
		}

		return this.beta1;
	}

	public Double getRxy() throws IOException {
		if (!this.modelTrained) {
			throw new IOException(errorMessage);
		}

		return this.rXy;
	}

	public Double getRpw() throws IOException {
		if (!this.modelTrained) {
			throw new IOException(errorMessage);
		}

		return this.rpw;
	}
}
