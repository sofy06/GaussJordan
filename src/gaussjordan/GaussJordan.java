/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordan;

import java.util.Scanner;

/**
 *
 * @author Sofy
 */
public class GaussJordan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int n;
        
        System.out.println("Ingrese el número de ecuaciones y variables: ");
        n = lector.nextInt();
        double[][] mat1 = new double[n][n];
        double[] vecResultado = new double[n];

        System.out.println("\nIngrese los coeficientes de la matriz:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Coeficiente [" + (i + 1) + "][" + (j + 1) + "] = ");
                mat1[i][j] = lector.nextDouble();
            }
        }

        System.out.println("\nIngrese los resultados (valores del lado derecho del sistema):");
        for (int i = 0; i < n; i++) {
            System.out.print("Resultado [" + (i + 1) + "] = ");
            vecResultado[i] = lector.nextDouble();
        }
        //si mat1 es 0, se intenta intercambiar la fila actual con una fila inferior que tenga un pivote no nulo en la misma columna.
        //buscar una fila j que tenga un valor distinto de cero. Si se encuentra, se llama a la función cambFila para intercambiar las filas i y j.
        for (int i = 0; i < n; i++) {
            if (mat1[i][i] == 0) {
                boolean cambFil = false;
                for (int j = i + 1; j < n; j++) {
                    if (mat1[j][i] != 0) {
                        cambFila(mat1, vecResultado, i, j);
                        cambFil = true;
                        break;
                    }
                }
                if (!cambFil) {
                    System.out.println("El sistema no tiene solución única.");
                    return;
                }
            }
            //Se divide toda la fila i de mat1 entre el valor de m, para hacer que sea 1.
            double m = mat1[i][i];
            for (int j = 0; j < n; j++) {
                mat1[i][j] /= m;
            }
            vecResultado[i] /= m;

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = mat1[k][i];
                    for (int j = 0; j < n; j++) {
                        mat1[k][j] -= factor * mat1[i][j];
                    }
                    vecResultado[k] -= factor * vecResultado[i];
                }
            }
        }

        System.out.println("\nLos resultados de las incógnitas son:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Resultado x%d = %.2f%n", (i + 1), vecResultado[i]);
        }
    }

    public static void cambFila(double[][] mat1, double[] vecResultado, int fil1, int fil2) {
        double[] temp = mat1[fil1];
        mat1[fil1] = mat1[fil2];
        mat1[fil2] = temp;

        double tempRes = vecResultado[fil1];
        vecResultado[fil1] = vecResultado[fil2];
        vecResultado[fil2] = tempRes;
    }
}
