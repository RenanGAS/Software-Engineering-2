package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import src.tools.CodeAccounter;


public final class Main {
        private Main() {
                throw new IllegalStateException("Utility class");
        }

        public static void main(String[] args) throws FileNotFoundException, IOException {
                CodeAccounter codeAccounter = new CodeAccounter();
                File inputFilePaths = new File(args[1]);

                //System.out.println("Running seFilePaths\n");
                codeAccounter.setFilePaths(inputFilePaths);
                //System.out.println("Running beginAccounting\n");
                codeAccounter.beginAccounting();
                //System.out.println("Running getProjectReport\n");
                codeAccounter.getProgramReport(args[0]);
        }
}
