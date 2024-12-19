package brian;

public class e3 {

    public static void main(String[] args) {
        double[] valX = {1, 2, 3, 4, 5, 6}; //valores para X
        double[] valY = {1, 8, 27, 64, 125, 216}; //Valores para Y
        double xVal = 2.45; //Valores para x buscado a interpolar

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
