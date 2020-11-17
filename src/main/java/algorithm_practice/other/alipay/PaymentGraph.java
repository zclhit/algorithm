package algorithm_practice.other.alipay;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PaymentGraph {

  /*
  编写程序计算一批转账记录中的用户关系并输出：
  1. TOP N收款次数最多的用户。
  2. 关系图有几个独立无交互关系子图构成。

  数据格式
  payer   payee   amount
  USER1   USER2   10
  USER2   USER3   200
  USER3   USER2   100
  ...
  */

  /*
   Test cases:
   payer   payee   amount
   USER1   USER2   10
   USER2   USER3   200
   USER3   USER2   100
   USER1   USER3   100
   USER4   USER5   100
   USER6   USER5   100
   should return:
   USER3
   USER5
   2
   */
  public static void main(String[] args) {
    List<CashTransfer> cashTransfers = new ArrayList<>();
    cashTransfers.add(new CashTransfer("USER1", "USER2", 10));
    cashTransfers.add(new CashTransfer("USER2", "USER3", 200));
    cashTransfers.add(new CashTransfer("USER3", "USER1", 100));
    cashTransfers.add(new CashTransfer("USER1", "USER3", 100));
    cashTransfers.add(new CashTransfer("USER4", "USER5", 100));
    cashTransfers.add(new CashTransfer("USER6", "USER5", 100));
    PaymentGraph paymentGraph = new PaymentGraph();
    paymentGraph.topNCashReceiver(cashTransfers, 2);
    Set<String> userSet = Stream.concat(cashTransfers.stream().map(CashTransfer::getPayer),
        cashTransfers.stream().map(CashTransfer::getPayee)).collect(Collectors.toSet());
    System.out.printf(
        String.valueOf(paymentGraph.numberOfConnectedSubGraph(cashTransfers, userSet)));
  }

  // main function entrance
  public void mainFunction(int N) {
    List<CashTransfer> cashTransfers = parser();
    Set<String> userSet = Stream.concat(cashTransfers.stream().map(CashTransfer::getPayer),
        cashTransfers.stream().map(CashTransfer::getPayee)).collect(Collectors.toSet());
    topNCashReceiver(cashTransfers, N);
    System.out.println(numberOfConnectedSubGraph(cashTransfers, userSet));
  }

  //Function1: calculate Top N cash receiver
  public void topNCashReceiver(List<CashTransfer> cashTransfer, int N) {
    List<String> receiverOrderByCashAmount = cashTransfer.stream()
        .collect(groupingBy(CashTransfer::getPayee, counting()))
        .entrySet().stream()
        .sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue()))
        .map(Entry::getKey)
        .collect(toList());
    receiverOrderByCashAmount.subList(0, N).forEach(System.out::println);
  }

  //Function2: calculate number of connected sub graph
  public int numberOfConnectedSubGraph(List<CashTransfer> cashTransfer, Set<String> userSet) {
    // init hash map to mark user access
    Map<String, Boolean> userAccessMark = new HashMap<>();
    userSet.forEach(user -> userAccessMark.put(user, false));
    // build graph
    Map<String, Set<String>> cashRelationGraph = new HashMap<>();
    cashTransfer.forEach(transfer -> {
      String payer = transfer.getPayer();
      String payee = transfer.getPayee();
      if (Objects.isNull(cashRelationGraph.get(payer))) {
        Set<String> relatedPayees = new HashSet<>();
        relatedPayees.add(payee);
        cashRelationGraph.put(payer, relatedPayees);
      } else {
        cashRelationGraph.get(payer).add(payee);
      }
      if (Objects.isNull(cashRelationGraph.get(payee))) {
        Set<String> relatedPayers = new HashSet<>();
        relatedPayers.add(payer);
        cashRelationGraph.put(payee, relatedPayers);
      } else {
        cashRelationGraph.get(payer).add(payer);
      }
    });
    // travel graph and mark (bfs)
    int result = 0;
    Queue<String> travelQueue = new LinkedList<>();
    for (String payer : cashRelationGraph.keySet()) {
      if (travelQueue.isEmpty() && !userAccessMark.get(payer)) {
        result++;
        travelQueue.offer(payer);
        userAccessMark.put(payer, true);
        // bfs & mark sub graph
        while (!travelQueue.isEmpty()) {
          String currentPayer = travelQueue.poll();
          Set<String> payees = cashRelationGraph.getOrDefault(currentPayer, new HashSet<>());
          for (String payee : payees) {
            if (!userAccessMark.get(payee)) {
              travelQueue.offer(payee);
              userAccessMark.put(payee, true);
            }
          }
        }
      }
    }
    return result;
  }

  //parser input to cash transfer list
  public List<CashTransfer> parser() {
    Scanner scanner = new Scanner(System.in);
    List<CashTransfer> cashTransfers = new ArrayList<>();
    while (scanner.hasNext()) {
      String[] records = scanner.nextLine().split(" ");
      if (isNumeric(records[2]) && records.length == 2) {
        cashTransfers.add(new CashTransfer(records[0], records[1], Integer.parseInt(records[2])));
      } else {
        //log.error("input invalid");
      }
    }
    return cashTransfers;
  }

  // judge if numeric
  public static boolean isNumeric(String str) {
    for (int i = str.length(); --i >= 0; ) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
}

@AllArgsConstructor
@Getter
class CashTransfer {

  private String payer;
  private String payee;
  private int amount;
}
