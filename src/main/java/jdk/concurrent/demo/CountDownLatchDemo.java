package jdk.concurrent.demo;

import java.util.concurrent.CountDownLatch;
import lombok.SneakyThrows;

public class CountDownLatchDemo {

  public static void main(String[] args) throws InterruptedException {

    final CountDownLatch latch = new CountDownLatch(2);

    new Thread() {
      @SneakyThrows
      public void run() {
        System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
        Thread.sleep(3000);
        System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");

        //countDown - 1
        latch.countDown();
      }
    }.start();

    new Thread() {
      @SneakyThrows
      public void run() {
        System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
        Thread.sleep(3000);
        System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");

        //countDown - 1
        latch.countDown();
      }
    }.start();

    System.out.println("等待 2 个子线程执行完毕...");

    latch.await();

    System.out.println("2 个子线程已经执行完毕");

    System.out.println("继续执行主线程");
  }
}
