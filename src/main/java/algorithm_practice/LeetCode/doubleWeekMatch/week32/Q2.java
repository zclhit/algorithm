package algorithm_practice.LeetCode.doubleWeekMatch.week32;

public class Q2 {

  public static void main(String[] args) {
//    System.out.println(minInsertions("()()()()()("));
//    System.out.println(minInsertions(")))()))))))((()))())))()))))()))()())((()()))()(())()())()()))))))()()((()))("));
//    System.out.println(minInsertions("((()))"));
    char[] c= new char[]{'a', 'b'};
    String s = c.toString();
    System.out.println(String.valueOf(c));
  }

  public static int minInsertions1(String s) {
    int ln = 0;
    int res = 0;
    char[] c = s.toCharArray();
    for(int i=0; i<c.length;i++) {
      if(c[i] == '(') {
        ln++;
      }
      else {
        if(i == c.length-1) {
          // last, add )
          res ++;
          if(ln >0) {
            ln--;
          } else {
            res ++;
          }
        } else if(c[i+1] == ')') {
          i++;
          if(ln>0) {
            ln--;
          } else {
            // add (
            res++;
          }
        } else {
          res++;
          ln--;
        }
      }
    }
    System.out.println("ln = " + ln);

    //process left (
    res += ln *2;
    return res;
  }

  public static int minInsertions(String s) {
    // 切割字符串
    int start = 0;
    int end = 1;
    int res = 0;
    while(end != s.length()) {
      if(s.charAt(end) == '(' && s.charAt(end -1) == ')') {
        res += process(s.substring(start, end));
        start = end;
        System.out.println("res = " + res);
      }
      end ++;
    }
    res+= process(s.substring(start));
    return res;
  }

  private static int process(String substring) {
    System.out.println(substring);
    if(substring.length() == 0) {
      return 0;
    }
    int ln = substring.indexOf(')');
    if(ln == -1) {
      ln = substring.length();
    }
    int rn = substring.length() - ln;
    while(ln >=1 && rn >= 2) {
      ln--;
      rn -=2;
    }
    // (((
    if(ln !=0 && rn==0) {
      return ln * 2;
    }
    // )) )))
    if(ln ==0 && rn !=0) {
      return rn /2 + rn %2 *2;
    }
    // (()
    return ln * 2 - rn;
  }
}
