import java.util.Random;

public class MatrixProcessor {
    public static void main(String[] args) {
        System.out.println("Розробник: Балатський М. О.");
        System.out.println("---------------------------");

        int rows = 3;
        int cols = 2;
        int[][] matrixA = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixA[i][j] = random.nextInt(100);
            }
        }
        System.out.println("Матриця А:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------");

        System.out.println("Суми елементів матриці за стовпцями:");
        for (int j = 0; j < cols; j++) {
            int columnSum = 0;
            for (int i = 0; i < rows; i++) {
                columnSum += matrixA[i][j];
            }
            System.out.println("Стовпець " + (j + 1) + ": " + columnSum);
        }
    }
}