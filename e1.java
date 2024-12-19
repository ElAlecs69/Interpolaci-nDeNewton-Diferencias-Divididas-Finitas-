//Brian Ivald Gomez Esquivel
// Metodo  Interpolacion ajuste de cuervas o metodo de regresion (minimos cuadráticos)   
package brian2;

import java.util.Scanner;

public class e1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
			int nPuntos;
			do {
			    System.out.print("Ingrese el número de puntos: ");
			    nPuntos = sc.nextInt();
			    if (nPuntos <= 0) {
			        System.out.println("Debe ingresar un número mayor a 0");
			    }
			} while (nPuntos <= 0);

			double[] valX = new double[nPuntos];
			double[] valY = new double[nPuntos];

			System.out.println("Ingrese los valores de x:");
			for (int i = 0; i < nPuntos; i++) {
			    System.out.print("x" + (i + 1) + ": ");
			    valX[i] = sc.nextDouble();
			}

			System.out.println("Ingrese los valores de y:");
			for (int i = 0; i < nPuntos; i++) {
			    System.out.print("y" + (i + 1) + ": ");
			    valY[i] = sc.nextDouble();
			}

			int cantidadX;
			do {
			    System.out.print("Ingrese la cantidad de valores de x: ");
			    cantidadX = sc.nextInt();
			    if (cantidadX <= 0) {
			        System.out.println("Debe ingresar un número mayor a 0");
			    }
			} while (cantidadX <= 0);

			double[] x = new double[cantidadX];

			System.out.println("Ingrese los valores de x para los que desea calcular y:");
			for (int i = 0; i < cantidadX; i++) {
			    System.out.print("x" + (i + 1) + ": ");
			    x[i] = sc.nextDouble();
			}
			System.out.println("Ingrese el numero de grado deseado a resolver (se recomienda el grado máximo 9 para más exactitud):");
			int grado = sc.nextInt();

			ajusteDeCurvas(valX, valY, x, grado);
		}
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
