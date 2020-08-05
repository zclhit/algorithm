package algorithm_practice.LeetCode.code300;

import common.datastruct.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class M337_打家劫舍III {

  /*
  在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {

  }

  /*
  解法：
考虑是二叉树的后根序遍历+动态规划，从二叉树的每个叶子节点开始计算然后向上进行遍历。
对于一个二叉树节点o，我们可以选择o点，也可以不选择o点，
用f(o)表示选择o点的情况下最大的结果，g(o)表示不选择o点的情况下。
那么我们可以知道f(o)应该是不选择左右子节点的值的和，所以有f(o) = g(l) + g(r )。而g(o)的场景下，
就是对于左子节点，可以选可以不选，要看这两种情况的最大值，对于右子节点，也是。
所以g(o) = Math.max(f(l), g(l)) + Math.max(f(r ), g(r ))。采用二叉树后序遍历的方式，
先遍历所有根节点的左右子节点，然后计算根节点，利用dfs遍历，维护f, g两个Map映射表，
最后根节点的Math.max(f(root), g(root))就是我们要找的结果。
   */
  Map<TreeNode, Integer> f = new HashMap<>();
  Map<TreeNode, Integer> g = new HashMap<>();

  public int rob(TreeNode root) {
    dfs(root);
    return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
  }

  private void dfs(TreeNode node) {
    if (node == null) {
      return;
    }
    dfs(node.left);
    dfs(node.right);
    f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
    g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math
        .max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
  }
}
