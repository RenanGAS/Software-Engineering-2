package project;

import java.io.IOException;

/**
 * Programa para cálculo do tempo de desenvolvimento de um software com base em múltiplas métricas.
 *
 */
public final class Main {
        private Main() {
                throw new IllegalStateException("Utility class");
        }

        public static void main(String[] args) {
                MlrModel mlr = new MlrModel();
                try {
                        mlr.setFeaturesData("./datasets/features.txt", 6, 4);
                        mlr.setTargetData("./datasets/target.txt", 6, 1);
                        mlr.run();

                        for (int i = 0; i < mlr.getNumCoefficients(); i++) {
                                System.out.println(mlr.getCoefficient(i));
                        }
                } catch (IOException e) {
                        System.out.println("ERROR: " + e.getMessage());
                }
        }
}
