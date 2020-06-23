package processor;

public class Utils {
    double detCalc(double[][] matrix) {
        int curLen= matrix.length;
        if (curLen == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        int newLen = curLen - 1;
        double det = 0;
        for (int i = 0; i < curLen; i++) {
            double[][] minor = getMinor(matrix, 0, i, curLen, newLen);
            det += matrix[0][i] * Math.pow(-1, i) * detCalc(minor);
        }
        return det;
    }

    double[][] getMinor(double[][] matrix, int dX, int dY, int curLen, int newLen) {
        double[][] minor = new double[newLen][newLen];
        int rowIndex = 0, colIndex = 0;

        for (int x = 0; x < curLen; x++) {
            for(int y = 0; y < curLen; y++) {
                if (x != dX && y != dY) {
                    minor[rowIndex][colIndex] = matrix[x][y];
                    if (++colIndex == newLen) {
                        colIndex = 0;
                        rowIndex++;
                    }
                }
            }
        }
        return minor;
    }
}
