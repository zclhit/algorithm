package algorithm_practice.LeetCode.code100;

import java.util.HashMap;
import java.util.Map;

public class M138_复制带随机指针的链表 {


  class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public static void main(String[] args) {

  }

  public Node copyRandomList(Node head) {
    Node headSaver = head;
    Map<Integer, Node> nodeMap = new HashMap<>();
    int idx = 0;
    Node curNode = head;
    while(curNode != null) {
      Node nNode = new Node(curNode.val);
      nodeMap.put(idx, nNode);
      if(idx !=0) {
        nodeMap.get(idx-1).next = nNode;
      }
      idx ++;
    }
    return head;
  }
}
