package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import src.tools.DataFrame;
import src.tools.LinkedList;
import src.tools.LRM;

/*
 * Programa para predição de tamanho de software ou tempo de desenvolvimento com base nos métodos PSP Probe
 */

public final class Main {
    private Main() {
            throw new IllegalStateException("Utility class");
    }

    public static void main(String[] args) throws FileNotFoundException, IndexOutOfBoundsException, NumberFormatException, IOException {
    	try (Scanner scanner = new Scanner(System.in)) {
    		System.out.println("Selecione uma base de dados em /datasets/ :");
        	String dataset = scanner.nextLine();
        	
        	System.out.println("Digite o número de instâncias:");
        	int numSamples = scanner.nextInt();
        	
        	DataFrame dfTrainingSet = new DataFrame(numSamples);
        	
        	dfTrainingSet.create("./datasets/" + dataset);
        	
        	String indepVar = "";
        	
        	while (true) {
        		System.out.println("Escolha uma variável independente:\n-> TEP: Tamanho estimado do proxy"
        				+ "\n-> TPAM: Tamanho planejado de adições e modificações");
        		scanner.nextLine();
            	indepVar = scanner.nextLine();
            	
            	if (!"TEP".equals(indepVar) && !"TPAM".equals(indepVar)) {
            		System.out.format("Essa variável não existe: %s\n", indepVar);
            		continue;
            	}
            	
            	break;
        	}
        	
        	String depVar = "";
        	
        	while (true) {
        		System.out.println("Escolha uma variável dependente:\n-> TEAM: Tamanho efetivo de adições e modificações"
        				+ "\n-> TED: Tempo efetivo de desenvolvimento");
            	depVar = scanner.nextLine();
            	
            	if (!"TEAM".equals(depVar) && !"TED".equals(depVar)) {
            		System.out.format("Essa variável não existe: %s\n", depVar);
            		continue;
            	}
            	
            	break;
        	}
        	
        	System.out.println("Selecionando conjunto de treinamento...");
        	
        	LinkedList llTrainingSet = null;
        	
        	if ("TEP".equals(indepVar)) {
        		if ("TEAM".equals(depVar)) {
        			llTrainingSet = dfTrainingSet.getSubset(1, 3);
        		} else {
        			llTrainingSet = dfTrainingSet.getSubset(1, 4);
        		}
        	} else if ("TPAM".equals(indepVar)) {
        		if ("TEAM".equals(depVar)) {
        			llTrainingSet = dfTrainingSet.getSubset(2, 3);
        		} else {
        			llTrainingSet = dfTrainingSet.getSubset(2, 4);
        		}
        	}
        	
        	System.out.println("Treinando modelo...");
        	
        	LRM lrModel = new LRM(llTrainingSet);
        	
        	lrModel.train();
        	
        	System.out.format("beta 0: %f\n", lrModel.getBeta0());
        	System.out.format("beta 1: %f\n", lrModel.getBeta1());
        	System.out.format("beta rXY: %f\n", lrModel.getRxy());
        	System.out.format("beta r2: %f\n", lrModel.getR2());
        	
        	Double x = 0.0;
        	
        	while (true) {
        		System.out.println("Digite um valor para X:");
            	try {
					String xValue = scanner.nextLine();
					xValue = xValue.replace(",", ".");
					
					x = Double.parseDouble(xValue);
				} catch (NumberFormatException e) {
					System.out.println("Valor inesperado: " + e.getMessage());
					continue;
				}
            	
            	break;
        	}
        	
        	Double result = lrModel.predict(x);
        	
        	System.out.format("Y predito: %f\n", result);
    	}
    }
}
