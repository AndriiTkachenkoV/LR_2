import java.util.Scanner;
import java.util.Random;

public class MatrixOperations {
    // Константи для діапазону рандомних чисел
    private static final int RANDOM_MIN = -100;
    private static final int RANDOM_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення розміру матриці
        System.out.println("Введіть висоту матриці (максимум 20):");
        int rows = getValidSize(scanner);

        System.out.println("Введіть ширину матриці (максимум 20):");
        int cols = getValidSize(scanner);

        // Вибір способу створення матриці
        System.out.println("Оберіть спосіб створення матриці:");
        System.out.println("1. Ввести вручну");
        System.out.println("2. Заповнити рандомними значеннями");
        int choice = getValidChoice(scanner);

        int[][] matrix = new int[rows][cols];
        if (choice == 1) {
            fillMatrixManually(scanner, matrix);
        } else {
            fillMatrixRandomly(matrix);
        }

        // Виведення матриці
        System.out.println("Згенерована матриця:");
        printMatrix(matrix);

        // Розрахунки
        int min = findMin(matrix);
        int max = findMax(matrix);
        double avgArithmetic = calculateArithmeticMean(matrix);
        double avgGeometric = calculateGeometricMean(matrix);

        // Виведення результатів
        System.out.println("Мінімальний елемент: " + min);
        System.out.println("Максимальний елемент: " + max);
        System.out.printf("Середнє арифметичне: %.2f%n", avgArithmetic);
        if (avgGeometric != -1) {
            System.out.printf("Середнє геометричне: %.2f%n", avgGeometric);
        } else {
            System.out.println("Середнє геометричне: неможливо розрахувати (від’ємні числа).");
        }

        scanner.close();
    }

    // Метод для отримання коректного розміру матриці
    private static int getValidSize(Scanner scanner) {
        int size;
        do {
            size = scanner.nextInt();
            if (size <= 0 || size > 20) {
                System.out.println("Неправильне значення! Введіть число від 1 до 20:");
            }
        } while (size <= 0 || size > 20);
        return size;
    }

    // Метод для отримання коректного вибору користувача
    private static int getValidChoice(Scanner scanner) {
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Неправильний вибір! Введіть 1 або 2:");
            }
        } while (choice != 1 && choice != 2);
        return choice;
    }

    // Метод для ручного заповнення матриці
    private static void fillMatrixManually(Scanner scanner, int[][] matrix) {
        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    // Метод для заповнення матриці рандомними значеннями
    private static void fillMatrixRandomly(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = RANDOM_MIN + random.nextInt(RANDOM_MAX - RANDOM_MIN + 1);
            }
        }
    }

    // Метод для виведення матриці
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.printf("%4d", elem);
            }
            System.out.println();
        }
    }

    // Метод для пошуку мінімального елемента
    private static int findMin(int[][] matrix) {
        int min = Integer.MAX_VALUE;
        for (int[] row : matrix) {
            for (int elem : row) {
                if (elem < min) {
                    min = elem;
                }
            }
        }
        return min;
    }

    // Метод для пошуку максимального елемента
    private static int findMax(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int elem : row) {
                if (elem > max) {
                    max = elem;
                }
            }
        }
        return max;
    }

    // Метод для обчислення середнього арифметичного
    private static double calculateArithmeticMean(int[][] matrix) {
        double sum = 0;
        int count = 0;
        for (int[] row : matrix) {
            for (int elem : row) {
                sum += elem;
                count++;
            }
        }
        return sum / count;
    }

    // Метод для обчислення середнього геометричного
    private static double calculateGeometricMean(int[][] matrix) {
        double product = 1.0;
        int count = 0;
        for (int[] row : matrix) {
            for (int elem : row) {
                if (elem <= 0) {
                    return -1; // Середнє геометричне неможливо обчислити
                }
                product *= elem;
                count++;
            }
        }
        return Math.pow(product, 1.0 / count);
    }
}
