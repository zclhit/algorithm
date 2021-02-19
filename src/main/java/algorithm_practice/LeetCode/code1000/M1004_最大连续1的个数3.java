package algorithm_practice.LeetCode.code1000;

public class M1004_最大连续1的个数3 {
  /*
  给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。

 

示例 1：

输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：

输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 

提示：

1 <= A.length <= 20000
0 <= K <= A.length
A[i] 为 0 或 1 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    M1004_最大连续1的个数3 o = new M1004_最大连续1的个数3();
    int[] testCase1 = new int[]{1,1,1,0,0,0,1,1,1,1,0};
    System.out.println(o.longestOnes(testCase1, 2));
    int[] testCase2 = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
    System.out.println(o.longestOnes(testCase2, 3));
    int[] testCase3 = new int[]{0,0,1,1,1,0,0};
    System.out.println(o.longestOnes(testCase3, 0));
    int[] testCase4 = new int[]{0,0,0,1};
    System.out.println(o.longestOnes(testCase4, 4));
  }

  public int longestOnes(int[] A, int K) {
    int minLength = 0;
    int zeroNum = A[0] == 0 ? 1 : 0;
    int length = 1;
    int start = 0;
    int end = 1;
    while (end <= A.length) {
      if (zeroNum <= K) {
        minLength = Math.max(length, minLength);
        length ++;
        if (end != A.length && A[end] == 0) {
          zeroNum ++;
        }
        end++;
      } else {
        if (A[start] == 0) {
          zeroNum --;
        }
        length --;
        start++;
      }
    }
    return minLength;
  }
}
