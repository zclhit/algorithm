package algorithm_practice.other.tencent_ykj;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Car implements Comparable{
  int size;

  @Override
  public int compareTo(Object o) {
    return this.size - ((Car) o).size;
  }
}
