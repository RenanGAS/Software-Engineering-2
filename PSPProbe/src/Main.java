package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import src.tools.DataFrame;
import src.tools.LinkedList;
import src.tools.Lrm;

/*
 * Programa para predição de tamanho de software ou tempo de desenvolvimento com base nos métodos PSP Probe
 */

public final class Main {
	private Main() {
		throw new IllegalStateException("Utility class");
	}

	public static void main(String[] args) throws FileNotFoundException, IndexOutOfBoundsException, NumberFormatException, IOException {
		String tep = "TEP";
		String tpam = "TPAM";
		String ted = "TED";
		String team = "TEAM";

		try (Scanner scanner = new Scanner(System.in)) {
	    		System.out.println("Selecione uma base de dados em /datasets/ :");
	        	String dataset = scanner.nextLine();

	        	System.out.println("Digite o número de instâncias:");
	        	int numSamples = scanner.nextInt();

	        	DataFrame dfTrainingSet = new DataFrame(numSamples);

	        	dfTrainingSet.create("./datasets/" + dataset);

	        	String invalidVarMessage = "Essa variável não existe";

	        	String indepVar = "";

	        	while (true) {
	        		System.out.format("Escolha uma variável independente:\n-> %s: Tamanho estimado do proxy"
	        			+ "\n-> %s: Tamanho planejado de adições e modificações\n", tep, tpam);
	        		scanner.nextLine();
	        		indepVar = scanner.nextLine();

	        		if (!tep.equals(indepVar) && !tpam.equals(indepVar)) {
	        			System.out.println(invalidVarMessage);
	        			continue;
	        		}

	        		break;
	        	}

	        	String depVar = "";

	        	while (true) {
	        		System.out.format("Escolha uma variável dependente:\n-> %s: Tamanho efetivo de adições e modificações"
	        				+ "\n-> %s: Tempo efetivo de desenvolvimento\n", team, ted);
		            	depVar = scanner.nextLine();

		            	if (!team.equals(depVar) && !ted.equals(depVar)) {
		            		System.out.println(invalidVarMessage);
		            		continue;
		            	}

	            		break;
	        	}

	        	System.out.println("Selecionando conjunto de treinamento...");

	        	LinkedList llTrainingSet = null;

	        	if (tep.equals(indepVar)) {
	        		if (team.equals(depVar)) {
	    				llTrainingSet = dfTrainingSet.getSubset(1, 3);
	        		} else {
	    				llTrainingSet = dfTrainingSet.getSubset(1, 4);
	        		}
	        	} else if (tpam.equals(indepVar)) {
	        		if (team.equals(depVar)) {
	    				llTrainingSet = dfTrainingSet.getSubset(2, 3);
	        		} else {
	    				llTrainingSet = dfTrainingSet.getSubset(2, 4);
	        		}
	        	}

	        	System.out.println("Treinando modelo...");

	        	Lrm lrModel = new Lrm(llTrainingSet);

	        	lrModel.train();

	        	System.out.format("beta 0: %f\n", lrModel.getBetaZero());
	        	System.out.format("beta 1: %f\n", lrModel.getBetaOne());
	        	System.out.format("beta rXY: %f\n", lrModel.getRxy());
	        	System.out.format("beta r2: %f\n", lrModel.getRpw());

	        	Double xParam = 0.0;

	        	while (true) {
		        	System.out.println("Digite um valor para X:");
		        	try {
					String xValue = scanner.nextLine();
					xValue = xValue.replace(",", ".");

					xParam = Double.parseDouble(xValue);
				} catch (NumberFormatException e) {
					System.out.println("Valor inesperado: " + e.getMessage());
					continue;
				}
		            	break;
	        	}

	        	Double result = lrModel.predict(xParam);

	        	System.out.format("Y predito: %f\n", result);
    		}
    	}
}
