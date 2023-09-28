package src.tools;

import java.util.ArrayList;
import java.util.List;

/*
 * LinkedList: Lista com instâncias de um DataFrame. Mantêm duas listas separadas para os atributos X (independente) e Y (dependente).
 */

public class LinkedList {
	List<Double> xList;

	List<Double> yList;

	int numElements;

	public LinkedList() {
		this.xList = new ArrayList<>();
		this.yList = new ArrayList<>();
		this.numElements = 0;
	}

	    /**
	     * Insere uma instância do DataFrame na lista
	     *
	     * @param xValue Valor do atributo independente X
	     * @param yValue Valor do atributo dependente Y
	     */
    	public void insert(double xValue, double yValue) {
    		this.xList.add(xValue);
    		this.yList.add(yValue);

    		this.numElements++;
    	}

	    /**
	     * @return Valores X das instâncias inseridas na lista
	     */
    	public List<Double> getXvalues() {
    		return this.xList;
    	}

	    /**
	     * @return Valores Y das instâncias inseridas na lista
	     */
    	public List<Double> getYvalues() {
    		return this.yList;
    	}

    	public int getNumElements() {
    		return this.numElements;
    	}
}
