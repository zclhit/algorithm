package algorithm_practice.LeetCode.code400;

public class M424_替换后的最长重复字符 {

  /*
  给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
  在执行上述操作后，找到包含重复字母的最长子串的长度。

  注意：字符串长度 和 k 不会超过 104。



  示例 1：

  输入：s = "ABAB", k = 2
  输出：4
  解释：用两个'A'替换为两个'B',反之亦然。
  示例 2：

  输入：s = "AABABBA", k = 1
  输出：4
  解释：
  将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
  子串 "BBBB" 有最长重复字母, 答案为 4。

  来源：力扣（LeetCode）
  链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    M424_替换后的最长重复字符 o = new M424_替换后的最长重复字符();
    System.out.println(o.characterReplacement("ABAB", 2)+"");
    System.out.println(o.characterReplacement("AABABBA", 1)+"");
    System.out.println(o.characterReplacement("AAAA", 0)+"");
  }

  /*
  双指针，查询某段substring中出现次数少的字符总数小于等于k的情况下的长度
  满足条件时，后指针后移
  不满足条件时，前指针后移
  计数器直接用数组
  维护一下当前长度 和 最长长度，最长长度减少时，重新计算最长长度
   */
  public int characterReplacement(String s, int k) {
    int[] num = new int[26];
    int n = s.length();
    int maxn = 0;
    int left = 0, right = 0;
    while (right < n) {
      num[s.charAt(right) - 'A']++;
      maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
      if (right - left + 1 - maxn > k) {
        num[s.charAt(left) - 'A']--;
        left++;
      }
      right++;
    }
    return right - left;
  }
}
