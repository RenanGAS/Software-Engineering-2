package src.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * DataFrame: Faz abstração do arquivo de entrada do modelo como uma matriz ou algo parecido, para o acesso aos valores 
 * dos atributos das instâncias de forma conveniente.
 */

public class DataFrame {
	Vector<Vector<Double>> matrix;
	
	public DataFrame(int numSamples) {
		this.matrix = new Vector<Vector<Double>>(numSamples);
	}
	
	/**
	 * Preenche a respectiva matriz do objeto DataFrame com os dados do arquivo de entrada
	 * 
	 * @param filePath Caminho relativo do arquivo de entrada
	 * @return Verdadeiro se a matriz foi preenchida com sucesso. Se ocorrer um erro, gera uma exceção
	 */
	public boolean create(String filePath) throws FileNotFoundException, NumberFormatException, IOException {
		File file = new File(filePath);
		
		try (Scanner scanner = new Scanner(file)) {
			int objectIndex = 0;
			
			while (scanner.hasNextLine()){
			    String input = scanner.nextLine();

			    if (input.isEmpty()){
			        break;
			    }
			    
			    Pattern pattern = Pattern.compile(" ", Pattern.CASE_INSENSITIVE);
			    
				String[] objectValues;
				
				objectValues = pattern.split(input);
				
				if (objectValues.length != 5) {
					String errorMessage = "Invalid number of attributes in the object: " + String.valueOf(objectValues.length);
					throw new IOException(errorMessage);
				}
				
				Vector<Double> objectVector = new Vector<Double>(5);
				
				this.matrix.add(objectIndex, objectVector);
				
				int attributeIndex = 0;
				
				for (String value : objectValues) {
					value = value.replace(",", ".");

			        Double attributeValue = Double.parseDouble(value);
			        
			        objectVector.add(attributeIndex, attributeValue);
			        
			        attributeIndex++;
				}
				
				objectIndex++;
			}
		}
		
		return true;
	}
	
	/**
	 * Devolve um subconjunto do dataFrame com apenas as colunas columnX e columnY
	 * 
	 * @param columnX Coluna do atributo independente
	 * @param columnY Coluna do atributo dependente
	 * @return Uma LinkedList com todas as instâncias iniciais, mas apenas com os valores das colunas especificadas. Se ocorrer um erro, gera uma exceção
	 */
	public LinkedList getSubset(int columnX, int columnY) throws IOException {
		LinkedList trainingDataList = new LinkedList();
		
		this.matrix.forEach(doubleVector -> {
			Double xValue = doubleVector.get(columnX);
			Double yValue = doubleVector.get(columnY);
			
		    trainingDataList.insert(xValue, yValue) ;
		});
		
		if (trainingDataList.getNumElements() == 0) {
			throw new IOException("Error at retrieving this subset");
		}
		
		return trainingDataList;
	}
}