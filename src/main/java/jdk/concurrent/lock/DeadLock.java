package jdk.concurrent.lock;

public class DeadLock {

  public static void main(String[] args) throws InterruptedException {
    final DeadLock dd1 = new DeadLock();
    final DeadLock dd2 = new DeadLock();
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        //首先获得dd1的锁
        synchronized (dd1) {
          //休眠
          try {
            Thread.sleep(50);
            synchronized (dd2) {
              System.out.println(Thread.currentThread().getName()+"线程。。");
              dd2.notify();
            }
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    },"t1");

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        synchronized (dd2) {
          try {
            dd2.wait();
            synchronized (dd1) {
              System.out.println(Thread.currentThread().getName()+"线程。。");
            }
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    },"t2");
    t1.start();
    t2.start();
  }
}
