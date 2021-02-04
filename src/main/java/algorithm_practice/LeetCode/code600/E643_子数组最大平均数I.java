package algorithm_practice.LeetCode.code600;

public class E643_子数组最大平均数I {
  /*
  给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

 

示例：

输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 

提示：

1 <= k <= n <= 30,000。
所给数据范围 [-10,000，10,000]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    E643_子数组最大平均数I o = new E643_子数组最大平均数I();
    int[] test = {1, 12, -5, -6, 50, 3};
    System.out.println(o.findMaxAverage(test, 4));
  }

  public double findMaxAverage(int[] nums, int k) {
    int end = 0;
    int sum = 0;
    while (end < k) {
      sum += nums[end];
      end++;
    }
    double max = sum;
    while (end < nums.length) {
      sum += nums[end];
      sum -= nums[end - k];
      max = Math.max(sum, max);
      end++;
    }
    return max / k;
  }
}
