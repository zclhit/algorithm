package algorithm_practice.LeetCode.code200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M207_课程表I {

  /*
  你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

 

示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    M207_课程表I obj = new M207_课程表I();
    //输入: 2, [[1,0]]
    //输出: true
    System.out.println(obj.canFinish(2, new int[][]{new int[]{1, 0}}));
    //输入: 2, [[1,0],[0,1]]
    //输出: false
    System.out.println(obj.canFinish(2, new int[][]{new int[]{1, 0}, new int[]{0, 1}}));
  }

  /*
  解法：
1.BFS+入度记录 时间复杂度O(n + m)，遍历每个点和每条边
我们用一个队列来存储广度优先搜索的结果，循环遍历每一个未放入队列中的点，
如果它的入度为0，说明所有先学课程都已经完成，可以直接放入队列，
同时可以把所有这个点指向的点入度分别-1，循环上述过程直到没有任何一个点的入度为0.
每次从队列中pop出一个点，把这个点所有相关的点入度-1，如果相关点入度变成了0，
就把这个点放入队列中。当队列遍历完成后，判断遍历的点是否是所有的点。

   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      edges.add(new ArrayList<>());
    }
    int[] indge = new int[numCourses];
    for (int i = 0; i < prerequisites.length; i++) {
      edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
      indge[prerequisites[i][0]]++;
    }
    Queue<Integer> travelQueue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indge[i] == 0) {
        travelQueue.offer(i);
      }
    }
    List<Integer> travelList = new ArrayList<>();
    while (!travelQueue.isEmpty()) {
      int node = travelQueue.poll();
      travelList.add(node);
      for (int con : edges.get(node)) {
        indge[con]--;
        if (indge[con] == 0) {
          travelQueue.offer(con);
        }
      }
    }
    return travelList.size() == numCourses;
  }
}
