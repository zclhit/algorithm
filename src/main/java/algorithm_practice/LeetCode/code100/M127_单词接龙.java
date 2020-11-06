package algorithm_practice.LeetCode.code100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M127_单词接龙 {

  /*
   * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
示例 2:

输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println("5 should equals" + ladderLength(beginWord, endWord, wordList));
  }

  /*
  思路1：
  将beginWord放第一位，endWords放最后一位，然后可以转换的建立边，进行从第一位到最后一位的BFS，维护一个最短距离
  狄杰斯特拉算法
   */
  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;//if end words not in result
    }

    // adjacent list
    List<List<Integer>> adjList = new ArrayList<>();
    List<Integer> start = new ArrayList<>();
    int endIndex = 0;
    for (int i = 0; i < wordList.size(); i++) {
      if (wordList.get(i).equals(endWord)) {
        endIndex = i;
      }
      if (canReach(beginWord, wordList.get(i))) {
        start.add(i);
      }
    }

    for (int i = 0; i < wordList.size(); i++) {
      List<Integer> edge = new ArrayList<>();
      for (int j = 0; j < wordList.size(); j++) {
        if (canReach(wordList.get(i), wordList.get(j))) {
          edge.add(j);
        }
      }
      adjList.add(edge);
    }

    //bfs
    int[] minLength = new int[wordList.size()];
    Queue<Integer> queue = new LinkedList<>();
    for (Integer i : start) {
      queue.offer(i);
      minLength[i] = 2;
      if (i == endIndex) {
        return 2;
      }
    }
    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      List<Integer> edges = adjList.get(currentNode);
      for (Integer node : edges) {
        if (node == endIndex) {
          return minLength[currentNode] + 1;
        } else {
          if (minLength[node] == 0) {
            minLength[node] = minLength[currentNode] + 1;
            queue.offer(node);
          }
        }
      }
    }

    return minLength[endIndex];
  }

  public static boolean canReach(String a, String b) {
    int diff = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        diff++;
      }
    }
    return diff == 1;
  }
}
