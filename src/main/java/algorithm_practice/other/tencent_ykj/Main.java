package algorithm_practice.other.tencent_ykj;

import java.util.Collections;
import java.util.LinkedList;

public class Main {

  public static void main(String[] args) {
//    LinkedList<Integer> lst = new LinkedList<>();
//    lst.add(3);
//    lst.add(2);
//    lst.add(1);
//    lst.sort(Integer::compareTo);
//    lst.forEach(System.out::print);

    LinkedList<Car> cars = new LinkedList<>();
    cars.add(new Car(3));
    cars.add(new Car(2));
    cars.add(new Car(1));
    Collections.sort(cars);
    cars.forEach(car -> System.out.println(car.size));
  }
}
