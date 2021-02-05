package algorithm_practice.other.bytedance2021;

import java.util.ArrayList;
import java.util.Arrays;

public class AllAboveAndAllBelowInArray {

  /*
  无序数组中找到左侧比他小右侧比他大的数
  eg: [1 3 2 4 5]
  result: [1 4 5]

  eg: [2 1 3 5 4]
  result: [3]

  eg: [5 4 6 2 8]
  result: [8]

  eg: [4 3 2 1]
  result: []
   */
  public static void main(String[] args) {
    AllAboveAndAllBelowInArray o = new AllAboveAndAllBelowInArray();
    int[] testCase1 = {1, 3, 2, 4, 5};
    int[] testCase2 = {2, 1, 3, 5, 4};
    int[] testCase3 = {5, 4, 6, 2, 8};
    int[] testCase4 = {4, 3, 2, 1};
    System.out.println("result of method 1, O(N^2)");
    System.out.println(Arrays.toString(o.calTargetMethod1(testCase1)));
    System.out.println(Arrays.toString(o.calTargetMethod1(testCase2)));
    System.out.println(Arrays.toString(o.calTargetMethod1(testCase3)));
    System.out.println(Arrays.toString(o.calTargetMethod1(testCase4)));

    System.out.println("result of method 2, O(N * Log(N))");
    System.out.println(Arrays.toString(o.calTargetMethod2(testCase1)));
    System.out.println(Arrays.toString(o.calTargetMethod2(testCase2)));
    System.out.println(Arrays.toString(o.calTargetMethod2(testCase3)));
    System.out.println(Arrays.toString(o.calTargetMethod2(testCase4)));


    System.out.println("result of method 3, O(N) time + O(N) space");
    System.out.println(Arrays.toString(o.calTargetMethod3(testCase1)));
    System.out.println(Arrays.toString(o.calTargetMethod3(testCase2)));
    System.out.println(Arrays.toString(o.calTargetMethod3(testCase3)));
    System.out.println(Arrays.toString(o.calTargetMethod3(testCase4)));
  }

  /*
  方法一:
  空间复杂度O(1)， 时间复杂度O(N^2)
  对每一个元素，判断是否是当前最大元素，可以维护当前最大元素减少计算量
   */
  public int[] calTargetMethod1(int[] arr) {
    int max = Integer.MIN_VALUE;
    int length = arr.length;
    ArrayList<Integer> resLst = new ArrayList<>();
    for (int idx = 0; idx < length; idx++) {
      if (arr[idx] > max) {
        max = arr[idx];
        if (allAbove(idx, arr)) {
          resLst.add(arr[idx]);
        }
      }
    }
    return getResult(resLst);
  }

  private boolean allAbove(int idx, int[] arr) {
    for (int i = idx + 1; i < arr.length; i++) {
      if (arr[i] < arr[idx]) {
        return false;
      }
    }
    return true;
  }

  private int[] getResult(ArrayList<Integer> resLst) {
    int[] res = new int[resLst.size()];
    int idx = 0;
    for (int i : resLst) {
      res[idx] = i;
      idx++;
    }
    return res;
  }

  /*
  先排序，取对应位置，有bug
   */
  public int[] calTargetMethod2(int[] arr) {
    int[] sortArr = Arrays.copyOf(arr, arr.length);
    Arrays.sort(sortArr);
    ArrayList<Integer> resLst = new ArrayList<>();
    for (int idx = 0; idx < arr.length; idx++) {
      if (arr[idx] == sortArr[idx]) {
        resLst.add(arr[idx]);
      }
    }
    return getResult(resLst);
  }

  /*
  时间复杂度O(N)，空间复杂度O(N)
  正向扫描数组，寻找满足大于所有前向节点的点并维护最大值，正向扫描数组标记该节点位置为1
  反向扫描数组，寻找满足小于所有后向节点的点并维护最小值，反向扫描数组标记该节点位置为1
  将正向反向数组求和，所有为2的idx对应的数组值即为所求
   */
  public int[] calTargetMethod3(int[] arr) {
    int length = arr.length;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int[] forwardMarkArr = new int[length];
    int[] reverseMarkArr = new int[length];
    for (int i = 0; i < length; i++) {
      if (arr[i] > max) {
        forwardMarkArr[i]++;
        max = arr[i];
      }
    }
    for (int i = length - 1; i >= 0; i--) {
      if (arr[i] < min) {
        reverseMarkArr[i]++;
        min = arr[i];
      }
    }
    ArrayList<Integer> resLst = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      if (forwardMarkArr[i] + reverseMarkArr[i] == 2) {
        resLst.add(arr[i]);
      }
    }
    return getResult(resLst);
  }
}
