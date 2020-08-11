package algorithm_practice.LeetCode.code600;

public class E696_计数二进制子串 {

  /*
  给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。

重复出现的子串要计算它们出现的次数。

示例 1 :

输入: "00110011"
输出: 6
解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

请注意，一些重复出现的子串要计算它们出现的次数。

另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
示例 2 :

输入: "10101"
输出: 4
解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
注意：

s.length 在1到50,000之间。
s 只包含“0”或“1”字符。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-binary-substrings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    System.out.println(countBinarySubstrings("00110011")+"");
    System.out.println(countBinarySubstrings("10101")+"");
    System.out.println(countBinarySubstrings("00110")+"");
    System.out.println(countBinarySubstrings("0")+"");
    System.out.println(countBinarySubstrings("11")+"");
  }

  /*
  遍历两边，第一轮做count，按照flag进行区分把00110011转化成2222，然后每两位取min，做和。
  优化之后只需要遍历一轮，原位做运算
   */
  public static int countBinarySubstrings(String s) {
    int[] arr = new int[s.length()];
    int pos = 0;
    int cnt = 1;
    int res = 0;
    char c = s.charAt(0);
    // convert to min
    for(int i=1; i<s.length(); i++) {
      if(s.charAt(i) == c) {
        cnt++;
      } else {
        arr[pos] = cnt;
        pos++;
        cnt = 1;
        c = s.charAt(i);
        if(pos>1) {
          res+=Math.min(arr[pos-2],arr[pos-1]);
        }
      }
    }
    arr[pos] = cnt;
    if(pos>0) {
      res+= Math.min(arr[pos-1],arr[pos]);
    }
    return res;
  }
}
