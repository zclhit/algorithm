package jdk.concurrent.demo.shareVar;

public class MyData {

  private int j = 0;

  public synchronized void add() {
    j++;
    System.out.println("线程" + Thread.currentThread().getName() + " j为： " + j);
  }

  public synchronized void desc() {
    j--;
    System.out.println("线程" + Thread.currentThread().getName() + " j为： " + j);
  }

  public int getDate() {
    return j;
  }
}
