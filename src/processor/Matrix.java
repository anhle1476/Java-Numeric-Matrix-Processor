package processor;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
    private double[][] matrix;
    private int row, col;
    private final Utils utils = new Utils();

    public Matrix(int row, int col, Scanner scanner) {
        this.row = row;
        this.col = col;
        matrix = new double[row][col];
        for (double[] thisRow : matrix) {
            String[] line = scanner.nextLine().split(" ");
            for (int i = 0; i < line.length; i++) {
                thisRow[i] = Double.parseDouble(line[i]);
            }
        }
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;
    }

    public Matrix add(Matrix other) {
        int row2 = other.row;
        int col2 = other.col;

        if (row != row2 || col != col2) {
            System.out.println("ERROR");
            return null;
        }

        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = matrix[i][j] + other.matrix[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix multiplyByConst(double constant) {
        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }
        return new Matrix(result);
    }

    public Matrix multiplyByMatrix(Matrix other) {
        int row2 = other.row;
        int col2 = other.col;
        if (col != row2) {
            System.out.println("Error: Can't multiply by this matrix.");
            return null;
        }

        double[][] result = new double[row][col2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col2; j++) {
                double accumulator = 0;
                for (int k = 0; k < col; k++) {
                    accumulator += matrix[i][k] * other.matrix[k][j];
                }
                result[i][j] = accumulator;
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeByMainDiagonal() {
        double[][] result = new double[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeBySideDiagonal() {
        double[][] result = new double[col][row];
        int maxRowIndex = col - 1;
        int maxColIndex = row - 1;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                result[i][j] = matrix[maxColIndex - j][maxRowIndex - i];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeByVerticalLine() {
        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = matrix[i][col - j - 1];
            }
        }
        return new Matrix(result);
    }

    public Matrix transposeByHorizontalLine() {
        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++) {
            result[i] = matrix[row - i - 1];
        }
        return new Matrix(result);
    }


    public void print() {
        if (matrix == null) return;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%.2f ",matrix[i][j]);
            }
            System.out.println();
        }
    }

    public Double getDeterminant() {
        if (row != col) {
            System.out.println("Error: This is not a square matrix.");
            return null;
        }
        if (row == 0) {
            return 0.;
        }
        return utils.detCalc(matrix);
    }

    public Matrix inverse() {
        if (row != col) {
            System.out.println("Error: This is not a square matrix.");
            return null;
        }
        double det = getDeterminant();
        double scalar = 1 / det;
        double[][] cofactors  = new double[row][row];
        int newLen = row - 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                double[][] minor = utils.getMinor(matrix, i, j, row, newLen);
                System.out.println(Arrays.deepToString(minor));
                cofactors[i][j] = Math.pow(-1, i + j) * utils.detCalc(minor);
            }
        }
        return new Matrix(cofactors).transposeByMainDiagonal().multiplyByConst(scalar);
    }
}