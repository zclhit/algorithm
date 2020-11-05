package jdk.concurrent.demo.shareVar;

public class Main {

  public static void main(String[] args) {
    MyData data = new MyData();

    Runnable add = new AddRunnable(data);
    Runnable desc = new DescRunnable(data);
    for (int i = 0; i < 2; i++) {
      new Thread(add).start();
      new Thread(desc).start();
    }
  }
}

class AddRunnable implements Runnable {

  MyData data;

  public AddRunnable(MyData data) {
    this.data = data;
  }

  @Override
  public void run() {
    data.add();
  }
}

class DescRunnable implements Runnable {

  MyData data;

  public DescRunnable(MyData data) {
    this.data = data;
  }

  @Override
  public void run() {
    data.desc();
  }
}
