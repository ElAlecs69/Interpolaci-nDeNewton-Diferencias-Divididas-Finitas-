package brian2;

public class e2 {

    public static void main(String[] args) {
        double[] valX = {0.05, 0.11, 0.15, 0.31, 0.46, 0.52, 0.70, 0.74, 0.82, 0.98, 1.17};
        double[] valY = {0.956, 0.890, 0.832, 0.717, 0.571, 0.539, 0.378, 0.370, 0.306, 0.242, 0.104};

        double[] x = {0.13, 0.48, 0.85, 1.10};
        int grado = 1; // Modificar el grado a gusto (maximo grado 9)

        ajusteDeCurvas(valX, valY, x, grado);
    }

    public static void ajusteDeCurvas(double[] valX, double[] valY, double[] x, int grado) {
        int M = valX.length;

        double[] sumas = new double[2 * grado + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j <= 2 * grado; j++) {
                sumas[j] += Math.pow(valX[i], j);
            }
        }

        double[][] matrizCoeficientes = new double[grado + 1][grado + 1];
        double[] vectorIndependiente = new double[grado + 1];
        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                matrizCoeficientes[i][j] = sumas[i + j];
            }
            vectorIndependiente[i] = 0;
            for (int j = 0; j < M; j++) {
                vectorIndependiente[i] += Math.pow(valX[j], i) * valY[j];
            }
        }

        double[] coeficientes = resolverSistemaGauss(matrizCoeficientes, vectorIndependiente);

        System.out.print("\nCoeficientes del polinomio de grado " + grado + ": \n");
        for (int i = 0; i <= grado; i++) {
            if (i == 0) {
                System.out.print("(" + coeficientes[i] + ")");
            } else {
                System.out.print(" + (" + coeficientes[i] + ")x^" + i);
            }
        }
        System.out.println();

        System.out.println("\nValores ajustados:");
        for (int i = 0; i < x.length; i++) {
            double y = 0;
            for (int j = 0; j <= grado; j++) {
                y += coeficientes[j] * Math.pow(x[i], j);
            }
            System.out.println("x = " + x[i] + " para y = " + y);
        }
    }

    public static double[] resolverSistemaGauss(double[][] A, double[] b) {
        int n = b.length;
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(A[j][i]) > Math.abs(A[maxRow][i])) {
                    maxRow = j;
                }
            }
            double[] tempRow = A[i];
            A[i] = A[maxRow];
            A[maxRow] = tempRow;
            double temp = b[i];
            b[i] = b[maxRow];
            b[maxRow] = temp;
            for (int j = i + 1; j < n; j++) {
                double factor = A[j][i] / A[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= factor * A[i][k];
                }
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
}
