Para compilação:

- javac src/Main.java src/tools/*.java

Para execução:

Para checagem de estilo:

- java -jar checkstyle-10.12.3-all.jar -c bcc_engsw2_checks.xml ./src

// Comentar as coisas que tem pra fazer
        	
// Se tiver ideia de função, escrever anotação e comentar parâmetros e saída esperada
// tanto para casos de sucesso como para casos de falha
        	
// Idealizar tudo e depois fazer os casos de teste
        	
// Depois dos casos de teste, fazer o programa real


//Classe LRM que lidará com LinkedList e ProbeCalculator. Se quiser testar outro método do PSP Probe, tem que instanciar outro DataFrame e LRM

// Guardar os pontos da LinkedList? Para mandar pro GraphicGenerator?
	
// Fluxo: DataFrame gera LinkedList; ProbleCalculator tem LinkedList; LRM usa ProbeCalculator; GraphicGenerator usa LRM

// Os cálculos do ProbeCalculator faz parte do treinamento do modelo

// Fora faz-se o Datagrama e inicializa-se o modelo mandando as listas de x e y para ele. No modelo, na fase de treinamento inicializa-se o
// probeCalculator com as listas para o cálculo das operações e em seguida dos subresultados principais.


// Carregar o histórico em uma estrutura de dados
        	
        	// Decidir o método que será utilizado:
        		// - Dependendo do método um determinado subconjunto dos dados será retirado da estrutura
        	
        	// Calcular B0 e B1 do subconjunto selecionado
        	
        	// Determinar a função linear e calcular o resultado para um dado valor
        	
        	// Calcular medidas de correlação do subconjunto selecionado
        	
        	// Fazer métodos get para pegar todos os resultados para serem utilizados nos casos de teste
        	
        	// Plotar o gráfico da reta com os pontos do subconjunto e as medidas de correlação
