package algorithm_practice.LeetCode.doubleWeekMatch.week32;

public class Q1 {

  public static void main(String[] args) {
    System.out.println(canConvertString("input", "ouput", 9));
  }

  public static boolean canConvertString(String s, String t, int k) {
    int[] dif = new int[26];
    int total = 0;
    for(int i=0; i<t.length();i++) {
      int d = t.charAt(i) - s.charAt(i);

      if(d < 0) {
        d += 26;
      }
      if(d != 0) {
        dif[d] ++;
        total++;
      }
    }

    for(int i=1;i<=k; i++) {
      int red = i % 26;
      if(dif[red] > 0 && red != 0)  {
        dif[red] --;
        total --;
        if(total == 0) {
          return true;
        }
      }
    }

    for(int i=1; i<26; i++) {
      if(dif[i] > 0) {
        return false;
      }
    }
    return true;
  }
}
