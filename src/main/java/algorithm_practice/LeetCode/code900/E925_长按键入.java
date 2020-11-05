package algorithm_practice.LeetCode.code900;

public class E925_长按键入 {

  public static void main(String[] args) {
    E925_长按键入 o = new E925_长按键入();
    System.out.println(o.isLongPressedName("vtkgn", "vttkgnn"));
    System.out.println(o.isLongPressedName("alex", "aaleex"));
    System.out.println(o.isLongPressedName("saeed", "ssaaedd"));
    System.out.println(o.isLongPressedName("leelee", "lleeelee"));
    System.out.println(o.isLongPressedName("laiden", "laiden"));
    System.out.println(o.isLongPressedName("", ""));
  }

  //自己第一次做的解答版本，代码复杂
  public boolean isLongPressedName_v1(String name, String typed) {
    //edge case and fast fail
    if (name.length() == 0 && typed.length() == 0) {
      return true;
    }

    if (name.length() > typed.length()) {
      return false;
    }

    char[] nc = name.toCharArray();
    char[] tc = typed.toCharArray();

    int idx1 = 0;
    int idx2 = 0;
    while (idx1 != nc.length && idx2 != tc.length) {
      if(nc[idx1] == tc[idx2]) {
        idx1++;
        idx2++;
      } else {
        if (idx2 == 0) {
          return false;
        } else {
          if (tc[idx2] == tc[idx2 - 1]) {
            idx2++;
          } else {
            return false;
          }
        }
      }
    }
    // roll out
    if (idx1 != nc.length) {
      return false;
    }
    // check tc left
    while(idx2 != tc.length) {
      if (tc[idx2] == tc[idx2 -1 ]) {
        idx2++;
      } else {
        return false;
      }
    }
    return true;
  }

  //根据题解写成的简化版本 - 双指针
  public boolean isLongPressedName(String name, String typed) {
    int i=0, j=0;
    while(j<typed.length()) {
      if(i<name.length() && name.charAt(i) == typed.charAt(j)) {
        i++;
        j++;
      } else if (j>0 && typed.charAt(j) == typed.charAt(j-1)) {
        j++;
      } else {
        return false;
      }
    }
    return i == name.length();
  }

}
