package algorithm_practice.LeetCode.code600;

import java.util.HashMap;
import java.util.Map;

public class E697_数组的度 {

  /*
  给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。

  你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

   

  示例 1：

  输入：[1, 2, 2, 3, 1]
  输出：2
  解释：
  输入数组的度是2，因为元素1和2的出现频数最大，均为2.
  连续子数组里面拥有相同度的有如下所示:
  [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
  最短连续子数组[2, 2]的长度为2，所以返回2.
  示例 2：

  输入：[1,2,2,3,1,4,2]
  输出：6
   

  提示：

  nums.length 在1到 50,000 区间范围内。
  nums[i] 是一个在 0 到 49,999 范围内的整数。


  来源：力扣（LeetCode）
  链接：https://leetcode-cn.com/problems/degree-of-an-array
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    E697_数组的度 o = new E697_数组的度();
    System.out.println(o.findShortestSubArray(new int[]{1,2,2,3,1}));
    System.out.println(o.findShortestSubArray(new int[]{1,2,2,3,1,4,2}));
    System.out.println(o.findShortestSubArray(new int[]{1,2,2,3,3,3,2}));
    System.out.println(o.findShortestSubArray(new int[]{2, 1}));
  }

  public int findShortestSubArray(int[] nums) {
    Map<Integer, int[]> countingMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (countingMap.containsKey(nums[i])) {
        countingMap.get(nums[i])[0]++;
        countingMap.get(nums[i])[2] = i;
      } else {
        countingMap.put(nums[i], new int[]{1, i, i});
      }
    }

    int maxNum = -1;
    int minLength = Integer.MAX_VALUE;
    for (int idx : countingMap.keySet()) {
      int[] cnt = countingMap.get(idx);
      if (cnt [0] > maxNum) {
        maxNum = cnt[0];
        minLength = cnt[2] - cnt[1] + 1;
      }
      if (cnt[0] == maxNum) {
        minLength = Math.min(cnt[2] - cnt[1] + 1, minLength);
      }

    }
    return minLength;
  }
}
