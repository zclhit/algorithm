package algorithm_practice.other.tencent_ykj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Shuamuban {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

//    System.out.println(Arrays.toString(arr));
    System.out.println(print(arr));
  }

  private static int print(int[] arr) {
    if(arr.length == 1) {
      return 1;
    }
    int min = Integer.MAX_VALUE;
    for(int i=0;i<arr.length;i++) {
      if(arr[i] < min) {
        min = arr[i];
      }
    }
//    System.out.println("min = "  + min);
    //for condition [4, 5, 6] should return 3;
    if(min > arr.length) {
      return arr.length;
    }

    // condition [3,2,1,2,3] convert to [2,1] and [1,2] add to list
    int start = 0;
    List<int[]> split = new ArrayList<>();
    for(int i=0;i<arr.length;i++) {
      arr[i] -= min;
      if(arr[i] == 0) {
        int[] x = Arrays.copyOfRange(arr, start, i);
        start =  i+1;
//        System.out.println(Arrays.toString(x));
        if(x.length ==1 && x[0] == 0 || x.length == 0) {
        } else {
          split.add(x);
        }
      }
    }

    // insert last part of split to list
    int[] x = Arrays.copyOfRange(arr, start, arr.length);
    if(x.length ==1 && x[0] == 0 || x.length == 0) {
    } else {
      split.add(x);
    }

    //递归
    int res = min;
    for(int[] s : split) {
      res += print(s);
    }
    return res;
  }
}
