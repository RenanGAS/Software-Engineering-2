package src;
import src.tools.LinkedList;
import src.tools.Operations;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        LinkedList list = new LinkedList();
        Scanner scanner = null;

        if (args.length < 1){
            scanner = new Scanner(System.in);
        }
        else{
            File file = new File("./src/tests/" + args[0]);

            try
            {
                scanner = new Scanner(file);
            }
            catch (FileNotFoundException ex)
            {
                throw ex;
            }
        }

        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.isEmpty()){
                break;
            }

            input = input.replace(",", ".");

            double element = Double.parseDouble(input);
            list = list.insert(list, element);
        }

        Operations operations = new Operations();
        
        System.out.format("Média: %f\n", operations.average(list));
        
        System.out.format("Desvio Padrão: %f\n", operations.std(list));
    }
}

