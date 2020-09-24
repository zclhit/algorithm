package jdk.concurrent.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierTimeoutDemo {

  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    new Writer1(cyclicBarrier).start();
    new Writer2(cyclicBarrier).start();
    new Writer3(cyclicBarrier).start();
  }

  static class Writer1 extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer1(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(2000); // 模拟写入操作导致的睡眠时间
        System.out.println("线程" + Thread.currentThread().getName() + "写入数据完成，等待其他线程写入数据");
        cyclicBarrier.await(4, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        System.out.println("线程" + Thread.currentThread().getName() + "Timed out");
        e.printStackTrace();
      }

      System.out.println("所有线程处理完毕，继续进行其他操作");
    }
  }

  static class Writer2 extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer2(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(3000); // 模拟写入操作导致的睡眠时间
        System.out.println("线程" + Thread.currentThread().getName() + "写入数据完成，等待其他线程写入数据");
        cyclicBarrier.await(2, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        System.out.println("线程" + Thread.currentThread().getName() + "Timed out");
        e.printStackTrace();
      }

      System.out.println("所有线程处理完毕，继续进行其他操作");
    }
  }


  static class Writer3 extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer3(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(5000); // 模拟写入操作导致的睡眠时间
        System.out.println("线程" + Thread.currentThread().getName() + "写入数据完成，等待其他线程写入数据");
        cyclicBarrier.await(4, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        System.out.println("线程" + Thread.currentThread().getName() + "Timed out");
        e.printStackTrace();
      }

      System.out.println("所有线程处理完毕，继续进行其他操作");
    }
  }
}
