package jdk.concurrent.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

  public static void main(String[] args) {
    int N = 4;
    CyclicBarrier barrier = new CyclicBarrier(N);
    for (int i=0; i<N;i++) {
      new Writer(barrier).start();
    }
  }

  static class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(5000); // 模拟写入操作导致的睡眠时间
        System.out.println("线程" + Thread.currentThread().getName() + "写入数据完成，等待其他线程写入数据");
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }


      System.out.println("所有线程处理完毕，继续进行其他操作");
    }
  }
}
