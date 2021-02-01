package algorithm_practice.LeetCode.code700;

import java.util.LinkedList;
import java.util.Queue;

public class H778_水位上升的泳池中游泳 {
/*
在一个 N x N 的坐标方格  grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
现在开始下雨了。当时间为  t  时，此时雨水导致水池中任意位置的水位为  t  。
你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台  (N-1, N-1)？

示例 1:

输入: [[0,2],[1,3]]
输出: 3
解释:
时间为0时，你位于坐标方格的位置为 (0, 0)。
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
示例2:

输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
输出: 16
解释:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的

提示:
2 <= N <= 50.
grid[i][j] 是 [0, ..., N*N - 1] 的排列。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/swim-in-rising-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

  public static void main(String[] args) {
    int[][] testCase = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16},
        {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
    System.out.println(swimInWater(testCase) + "");
  }

  /*
  思路：BFS+二分搜索
   */
  static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  static int N;

  public static int swimInWater(int[][] grid) {
    N = grid.length;
    int left = 0;
    int right = N * N - 1;
    while (left < right) {
      int mid = (left + right) / 2;
      System.out.println("mid = " + mid);
      if (grid[0][0] <= mid && bfs(grid, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  // calculate with threshold, if can reach grid[n][n]
  public static boolean bfs(int[][] grid, int threshold) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    boolean[][] visited = new boolean[N][N];
    visited[0][0] = true;
    //begin bfs
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int x = current[0];
      int y = current[1];
      for (int[] dir : dirs) {
        int nx = x + dir[0];
        int ny = y + dir[1];
        if (inArea(nx, ny) && !visited[nx][ny] && grid[nx][ny] <= threshold) {
          if (nx == (N - 1) && ny == (N - 1)) {
            return true;
          }

          queue.offer(new int[]{nx, ny});
          visited[nx][ny] = true;
        }
      }
    }
    return false;
  }

  public static boolean inArea(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < N;
  }
}
