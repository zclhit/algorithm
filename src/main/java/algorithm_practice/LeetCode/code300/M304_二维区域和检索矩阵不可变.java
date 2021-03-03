package algorithm_practice.LeetCode.code300;

public class M304_二维区域和检索矩阵不可变 {

  public static void main(String[] args) {
    int[][] testCase = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7},
        {1, 0, 3, 0, 5}};
    NumMatrix mat = new NumMatrix(testCase);
    System.out.println(mat.sumRegion(2,1,4,3));
  }
}

class NumMatrix {

  int[][] sumMat;

  public NumMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    sumMat = new int[m][n];
    sumMat[0][0] = matrix[0][0];
    for (int i = 1; i < m; i++) {
      sumMat[i][0] = matrix[i][0] + sumMat[i-1][0];
    }
    for (int j = 1; j < n; j++) {
      sumMat[0][j] = matrix[0][j] + sumMat[0][j-1];
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        sumMat[i][j] = sumMat[i][j - 1] + sumMat[i - 1][j] - sumMat[i - 1][j - 1] + matrix[i][j];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return sumMat[row2][col2] + sumMat[row1-1][col1-1] - sumMat[row2][col1-1] - sumMat[row1-1][col2];
  }
}
