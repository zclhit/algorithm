package algorithm_practice.LeetCode.code800;

import java.util.Arrays;
import java.util.HashSet;

public class E888_公平的糖果棒交换 {

  /*
  爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。

因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。

如果有多个答案，你可以返回其中任何一个。保证答案存在。



示例 1：

输入：A = [1,1], B = [2,2]
输出：[1,2]
示例 2：

输入：A = [1,2], B = [2,3]
输出：[1,2]
示例 3：

输入：A = [2], B = [1,3]
输出：[2,3]
示例 4：

输入：A = [1,2,5], B = [2,4]
输出：[5,4]


提示：

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
保证爱丽丝与鲍勃的糖果总量不同。
答案肯定存在。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/fair-candy-swap
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    E888_公平的糖果棒交换 o = new E888_公平的糖果棒交换();
    int[] test1_a = {1,1};
    int[] test1_b = {2,2};
    System.out.println(Arrays.toString(o.fairCandySwap(test1_a, test1_b)));
    int[] test2_a = {1,2};
    int[] test2_b = {2,3};
    System.out.println(Arrays.toString(o.fairCandySwap(test2_a, test2_b)));
    int[] test3_a = {2};
    int[] test3_b = {1,3};
    System.out.println(Arrays.toString(o.fairCandySwap(test3_a, test3_b)));
    int[] test4_a = {1,2,5};
    int[] test4_b = {2,4};
    System.out.println(Arrays.toString(o.fairCandySwap(test4_a, test4_b)));
  }

  public int[] fairCandySwap(int[] A, int[] B) {
    HashSet<Integer> bSet = new HashSet<>();
    int sumA = 0;
    int sumB = 0;
    for(int i: A){
      sumA += i;
    }

    for (int i: B) {
      sumB += i;
      bSet.add(i);
    }

    int diff = (sumA - sumB) /2;
    for (int i:A) {
      if (bSet.contains(i - diff)) {
        return new int[]{i, i - diff};
      }
    }
    return new int[]{0,0};
  }
}
