package algorithm_practice.LeetCode.code200;

import java.util.Arrays;

public class E283_移动零 {

  /*
  给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/move-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    E283_移动零 o = new E283_移动零();
    int[] nums = new int[]{0,1,0,3,12};
    o.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }
  /*
  解法：双指针法
  左指针 -> 非0数组最右边的数字
  右指针 -> 连续0数组最右端的0，它的左侧全是0，右侧是待扫描数字
  如果右指针下一个是0，移动右指针
  如果右指针下一个不是0，右指针下一个放到左指针下一个的位置，右指针下一个位置设置为0，左指针右移
   */
  public void moveZeroes(int[] nums) {
    int left = 0;
    int right = 0;
    while(right < nums.length) {
      if (nums[right] == 0) {
        right ++;
      } else {
        swap(nums, left, right);
        left++;
        right++;
      }
    }
  }

  private void swap(int[] nums, int left, int right) {
    int tmp = nums[left];
    nums[left] = nums[right];
    nums[right] = tmp;
  }
}
