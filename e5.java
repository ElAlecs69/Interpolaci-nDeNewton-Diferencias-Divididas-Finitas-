package brian;

public class e5 {

    public static void main(String[] args) {
        double[] valX = {2.0, 2.2, 2.4, 2.6, 2.8, 3.0}; //valores para X
        double[] valY = {0.5103757, 0.5207843, 0.5104147, 0.4813306, 0.4359160, 0.4067381}; //Valores para Y
        double xVal = 2.5; //Valores para x buscado a interpolar

        double resultado = interpolacionNewton(valX, valY, xVal);

        System.out.println("El resultado es: " + resultado);

    }

    public static double interpolacionNewton(double[] x, double[] y, double xValue) {
        int n = x.length;
        double res = 0;

        double[][] diferenciasDiv = new double[n][n];
        for (int i = 0; i < n; i++) {
            diferenciasDiv[i][0] = y[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                diferenciasDiv[i][j] = (diferenciasDiv[i + 1][j - 1] - diferenciasDiv[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            double term = diferenciasDiv[0][i];
            for (int j = 0; j < i; j++) {
                term *= (xValue - x[j]);
            }
            res += term;
        }

        return res;
    }
}
