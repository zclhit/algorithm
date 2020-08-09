package algorithm_practice.LeetCode.weeklyMatch.week201;

public class Q2 {

  public static void main(String[] args) {
    System.out.println(findKthBit(4, 11));
  }

  public static char findKthBit(int n, int k) {
    String s = "0";
    for(int i=1; i<n; i++) {
      char[] c = s.toCharArray();
      char[] cr = new char[c.length];
      int l = c.length;
      for(int j=0; j<c.length; j++) {
        if(c[j] == '1') {
          cr[l-j-1] = '0';
        } else {
          cr[l-j-1] = '1';
        }
      }
      s = s + "1" + String.valueOf(cr);
    }
//    System.out.println(s);
    return s.charAt(k-1);
  }
}
