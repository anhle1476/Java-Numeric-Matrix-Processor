package processor;

import java.util.Scanner;

public class Main {
    static boolean isOn = true;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (isOn){
            try {
                System.out.println(
                        "\n1. Add matrices\n" +
                                "2. Multiply matrix to a constant\n" +
                                "3. Multiply matrices\n" +
                                "4. Transpose matrix\n" +
                                "5. Calculate a determinant\n" +
                                "6. Inverse matrix\n" +
                                "0. Exit\n"
                );

                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 0:
                        isOn = false;
                        break;
                    case 1:
                        addHandler();
                        break;
                    case 2:
                        multiplyByConstHandler();
                        break;
                    case 3:
                        multiplyByMatrixHandler();
                        break;
                    case 4:
                        transposeHandler();
                        break;
                    case 5:
                        getDeterminateHandler();
                        break;
                    case 6:
                        inverseHandler();
                        break;
                    default:
                        System.out.println("Action invalid.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Action invalid. Please enter the right number.");
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

    }

    private static Matrix createMatrix(String name) {
        String formattedName = name.equals("") ? name : name + " ";
        System.out.println("Enter size of "+ formattedName + "matrix:");

        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter "+ formattedName + "matrix:");
        return new Matrix(row, col, scanner);
    }

    private static void addHandler() {
        Matrix addMatrix1 = createMatrix("first");
        Matrix addMatrix2 = createMatrix("second");

        Matrix result = addMatrix1.add(addMatrix2);
        System.out.println("The adding result is:");
        result.print();
    }

    private static void multiplyByConstHandler() {
        Matrix matrix = createMatrix("");

        int num = scanner.nextInt();
        scanner.nextLine();

        Matrix result = matrix.multiplyByConst(num);
        System.out.println("The multiplication result is:");
        result.print();
    }

    private static void multiplyByMatrixHandler() {
        Matrix multiplyMatrix1 = createMatrix("first");
        Matrix multiplyMatrix2 = createMatrix("second");

        Matrix result = multiplyMatrix1.multiplyByMatrix(multiplyMatrix2);
        System.out.println("The multiplication result is:");
        result.print();
    }

    private static void transposeHandler() throws NumberFormatException {
        System.out.println(" \n" +
                "1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "Your choice:");
        int action = scanner.nextInt();
        scanner.nextLine();
        if (action > 4 || action < 1) {
            System.out.println("Action invalid.");
            return;
        }
        Matrix matrix = createMatrix("");
        Matrix result = null;
        switch (action) {
            case 1:
                result = matrix.transposeByMainDiagonal();
                break;
            case 2:
                result = matrix.transposeBySideDiagonal();
                break;
            case 3:
                result = matrix.transposeByVerticalLine();
                break;
            case 4:
                result = matrix.transposeByHorizontalLine();
                break;
        }
        if (result != null) result.print();
    }

    private static void getDeterminateHandler() {
        Matrix matrix = createMatrix("");
        Double result = matrix.getDeterminant();
        if (result == null) return;
        System.out.printf("The result is: %n%.2f%n", result);
    }

    private static void inverseHandler() {
        Matrix matrix = createMatrix("");
        Matrix result = matrix.inverse();
        if (result == null) return;
        System.out.println("The result is:");
        result.print();
    }
}
