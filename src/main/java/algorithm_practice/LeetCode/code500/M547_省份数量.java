package algorithm_practice.LeetCode.code500;

import java.util.LinkedList;
import java.util.Queue;

public class M547_省份数量 {

  public static void main(String[] args) {
    int[][] testCase1 = new int[][]{new int[]{1,1,0}, new int[]{1,1,0}, new int[]{0,0,1}};
    int[][] testCase2 = new int[][]{new int[]{1,0,0}, new int[]{0,1,0}, new int[]{0,0,1}};
    int[][] testCase3 = new int[][]{new int[]{1,0,0,1},new int[]{0,1,1,0},new int[]{0,1,1,1},new int[]{1,0,1,1}};
    M547_省份数量 o = new M547_省份数量();
//    System.out.println(o.findCircleNum(testCase1));
//    System.out.println(o.findCircleNum(testCase2));
    System.out.println(o.findCircleNum(testCase3));
  }

  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    int[] access = new int[n]; //access table
    Queue<Integer> connectQueue = new LinkedList<>();
    int total = 0;
    for (int i = 0; i < n; i++) {
      if (access[i] == 0) {
        connectQueue.add(i);
        while (!connectQueue.isEmpty()) {
          int current = connectQueue.poll();
          access[current] = 1;
          for (int j = 0; j < n; j++) {
            if (access[j] == 0 && isConnected[current][j] == 1) {
              connectQueue.add(j);
            }
          }
        }
        total ++;
      }
    }
    return total;
  }
}
