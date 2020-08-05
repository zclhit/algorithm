package algorithm_practice.LeetCode.code200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M210_课程表II {

  /*
  现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]]
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    M210_课程表II obj = new M210_课程表II();
    //输入: 2, [[1,0]]
    //输出: [0, 1]
    System.out.println(Arrays.toString(obj.findOrder(2, new int[][]{new int[]{1, 0}})));
    //输入: 4, [[1,0],[2,0],[3,1],[3,2]]
    //输出: [0, 1, 2, 3]
    System.out
        .println(Arrays.toString(obj.findOrder(4,
            new int[][]{new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 1}, new int[]{3, 2}})));
    //输入：3, [[1,0],[1,2],[0,1]]
    //输出:[]

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
  public int[] findOrder(int numCourses, int[][] prerequisites) {
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
    if(travelList.size() != numCourses) {
      return new int[0];
    }
    int[] res = new int[travelList.size()];
    for (int i = 0; i < travelList.size(); i++) {
      res[i] = travelList.get(i);
    }
    return res;
  }
}
