//Brian Ivald Gomez Esquivel;
//Metodo de diferencias finitas de taylor(Diferencias divididas de newton)
package brian;

import java.util.Scanner;

public class brian {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Ingrese el número de puntos: ");
			int n = sc.nextInt();

			double[] valX = new double[n];
			double[] valY = new double[n];

			System.out.println("Ingrese los valores de x:");
			for (int i = 0; i < n; i++) {
			    System.out.print("x" + i + ": ");
			    valX[i] = sc.nextDouble();
			}

			System.out.println("Ingrese los valores de y:");
			for (int i = 0; i < n; i++) {
			    System.out.print("y" + i + ": ");
			    valY[i] = sc.nextDouble();
			}

			System.out.print("Ingrese el valor de x para interpolar: ");
			double xVal = sc.nextDouble();

			double resultado = interpolacionNewton(valX, valY, xVal);

			System.out.println("El resultado es: " + resultado);
		}
        
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