package src.tools;

import java.io.IOException;
import java.lang.Math;

/*
 * LRM: Modelo de Regressão Linear.
 */

public class LRM {
	ProbeCalculator probeCalculator;
	LinkedList dadosTreinamento;
	boolean modelTrained;
	Double beta0;
	Double beta1;
	Double rXY;
	Double r2;
	
	public LRM(LinkedList dadosTreinamento) {
		this.dadosTreinamento = dadosTreinamento;
		this.probeCalculator = new ProbeCalculator(dadosTreinamento);
		this.modelTrained = false;
	}
	
	/**
	 * Faz o cálculo de beta0, beta1, rXY e r2 usando os valores calculados em probeCalculator. Redireciona exceção de precalculateSubOperations()
	 */
	public void train() throws IndexOutOfBoundsException {
		this.probeCalculator.precalculateSubOperations();
		
		Double summationXY = this.probeCalculator.getSummationXY();
		Double summationX2 = this.probeCalculator.getSummationX2();
		Double summationY2 = this.probeCalculator.getSummationY2();
		Double summationX = this.probeCalculator.getSummationX();
		Double summationY = this.probeCalculator.getSummationY();
		Double xAverage = this.probeCalculator.getxAverage();
		Double yAverage = this.probeCalculator.getyAverage();
		
		int numElements = this.dadosTreinamento.getNumElements();
		
		this.beta1 = (summationXY - (numElements * xAverage * yAverage)) / (summationX2 - (numElements * xAverage * xAverage));
		this.beta0 = yAverage - (this.beta1 * xAverage);
		this.rXY = ((numElements * summationXY) - (summationX * summationY)) / 
				(Math.sqrt(((numElements * summationX2) - (summationX * summationX)) * ((numElements * summationY2) - (summationY * summationY))));
		this.r2 = this.rXY * this.rXY;
		
		this.modelTrained = true;
	}
	
	/**
	 * Faz a predição de um valor Y dado um valor X
	 */
	public Double predict(Double x) throws IOException {
		if (!this.modelTrained) {
			throw new IOException("The model needs to be trained first");
		}
		
		return this.beta0 + this.beta1*x;
	}

	public Double getBeta0() throws IOException {
		if (!this.modelTrained) {
			throw new IOException("The model needs to be trained first");
		}
		
		return this.beta0;
	}

	public Double getBeta1() throws IOException {
		if (!this.modelTrained) {
			throw new IOException("The model needs to be trained first");
		}
		
		return this.beta1;
	}

	public Double getRxy() throws IOException {
		if (!this.modelTrained) {
			throw new IOException("The model needs to be trained first");
		}
		
		return this.rXY;
	}

	public Double getR2() throws IOException {
		if (!this.modelTrained) {
			throw new IOException("The model needs to be trained first");
		}
		
		return this.r2;
	}
	
}