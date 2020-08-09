package algorithm_practice.other.meituan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Q1 {

  public static void main(String[] args) {
    System.out.println(calAvg(new int[5]));
  }

  public static String calAvg(int[] array) {

    int sum = 0;
    int div = 0;

    for(int i=0; i<array.length; i++) {
      sum += (i+1) * array[i];
      div += array[i];
    }
    if(div == 0) {
      div =1;
    }
    float res = (float)sum / div;
    BigDecimal bd = new BigDecimal(res);
    bd = bd.setScale(1, RoundingMode.DOWN);
    String resString = bd.toString();
    return resString;
  }
}
