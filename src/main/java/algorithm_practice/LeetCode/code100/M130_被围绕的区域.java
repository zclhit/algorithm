package algorithm_practice.LeetCode.code100;

public class M130_被围绕的区域 {

  /*
  给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surrounded-regions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    M130_被围绕的区域 obj = new M130_被围绕的区域();
    char[][] testCase = new char[][]{new char[]{'X', 'X', 'X', 'X'},
        new char[]{'X', 'O', 'O', 'X'},
        new char[]{'X', 'X', 'O', 'X'},
        new char[]{'X', 'O', 'X', 'X'}};
    obj.solve(testCase);
    for (int i = 0; i < testCase.length; i++) {
      for (int j = 0; j < testCase[0].length; j++) {
        System.out.print(testCase[i][j] + " ");
      }
      System.out.println();
    }
    char[][] edgeCase = new char[0][0];
    obj.solve(edgeCase);
  }

  /*
  解法1:广度优先搜索
利用一个同维度矩阵记录是否已经遍历过该点，同时沿着四个边找，如果该点为0，
同时该点没有被访问过，则记录该点被访问，同时迭代访问该点的四周点（注意终止条件）。
这时候就是BFS，然后再去矩阵中把所有访问矩阵被访问的点以外的点都设置成X即可。
   */
  boolean[][] access;

  public void solve(char[][] board) {
    if(board.length <1) {
      return;
    }
    //init access matrix
    access = new boolean[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
      bfs(board, i, 0);
      bfs(board, i, board[0].length - 1);
    }

    for (int j = 0; j < board[0].length; j++) {
      bfs(board, 0, j);
      bfs(board, board.length - 1, j);
    }

    for (int i = 0; i < access.length; i++) {
      for (int j = 0; j < access[0].length; j++) {
        if (!access[i][j]) {
          board[i][j] = 'X';
        }
      }
    }

  }

  private void bfs(char[][] board, int i, int j) {
    if (i < 0 || i >= access.length || j < 0 || j >= access[0].length) {
      return;
    }
    if (access[i][j] == false && board[i][j] == 'O') {
      access[i][j] = true;
      bfs(board, i - 1, j);
      bfs(board, i + 1, j);
      bfs(board, i, j - 1);
      bfs(board, i, j + 1);
    }
  }
}
