package algorithm_practice.other.alipay;

public class Test1 {

  public static void main(String[] args) {
    Test1 test = new Test1();
    System.out.println(test.convertToWords(0));
    System.out.println(test.convertToWords(1));
    System.out.println(test.convertToWords(25));
    System.out.println(test.convertToWords(26));
    System.out.println(test.convertToWords(27));
    System.out.println(test.convertToWords(701));
    System.out.println(test.convertToWords(702));
  }

  public String convertToWords(int n) {
    if (n < 0) {
      return "";
    }
    n++;
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      n--;
      sb.append((char) (n % 26 + 'A'));
      n =n / 26;
    }
    return sb.reverse().toString();
  }
}
