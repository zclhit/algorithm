package jdk.concurrent.demo;


import java.util.concurrent.Semaphore;

/**
 * Semaphore 常用来控制同时访问的线程个数
 */
public class SemaphoreDemo {

  public static void main(String[] args) {
    int N = 8;

    Semaphore semaphore = new Semaphore(5);
    for (int i = 0; i < N; i++) {
      new Worker(i, semaphore).start();
    }
  }

  static class Worker extends Thread {

    private int num;
    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println("工人" + this.num + "占用了一个生产机器");

        Thread.sleep(2000); // 利用sleep模拟工人工作时间长度

        System.out.println("工人" + this.num + "释放了一个机器");

        semaphore.release();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
