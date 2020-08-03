package algorithm_practice.LeetCode.code400;

public class E415_字符串相加 {

  /*
  给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 

提示：

num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    E415_字符串相加 obj = new E415_字符串相加();
    System.out.println(obj.addStrings("1", "9"));
  }

  public String addStrings(String num1, String num2) {
    int l1 = num1.length() - 1;
    int l2 = num2.length() - 1;
    int pos1 = 0;
    int pos2 = 0;
    int sum = 0;
    int adder = 0;
    StringBuilder sb = new StringBuilder();
    while (l1 >= 0 && l2 >= 0) {
      pos1 = num1.charAt(l1) - '0';
      pos2 = num2.charAt(l2) - '0';
      sum = pos1 + pos2 + adder;
      sb.append(sum % 10);
      adder = sum > 9 ? 1 : 0;
      l1--;
      l2--;
    }
    while (l1 >= 0) {
      pos1 = num1.charAt(l1) - '0';
      sum = pos1 + adder;
      sb.append(sum % 10);
      adder = sum > 9 ? 1 : 0;
      l1--;
    }
    while (l2 >= 0) {
      pos2 = num2.charAt(l2) - '0';
      sum = pos2 + adder;
      sb.append(sum % 10);
      adder = sum > 9 ? 1 : 0;
      l2--;
    }
    if(adder != 0) {
      sb.append(adder);
    }
    return sb.reverse().toString();
  }
}
