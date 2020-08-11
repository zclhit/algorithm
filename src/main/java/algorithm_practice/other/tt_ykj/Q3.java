package algorithm_practice.other.tt_ykj;

import java.util.Scanner;
import java.util.TreeMap;

public class Q3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    for(int i=0; i<n;i++) {
      int k = sc.nextInt();
      int v = sc.nextInt();
      tm.put(k, v);
    }

    int[] arr = new int[n];
    int idx =0;
    for(int v : tm.values()) {
      arr[idx] = v;
      idx++;
    }

    int max = 1;
    int cnt = 1;
    for(int i=1;i<n;i++) {
//      System.out.println("cnt=" + cnt);
      if(arr[i] > arr[i-1]) {
        cnt ++;
      } else {
        max = Math.max(cnt, max);
        cnt = 1;
      }
    }
    max = Math.max(cnt, max);
    System.out.println(max);
  }
}
