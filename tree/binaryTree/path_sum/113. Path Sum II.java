/**
 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// similar to last one, we need to record the path
class Solution {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
      List<List<Integer>> res = new LinkedList<>();
      if(root == null)  return res;
      
      Stack<Integer> path = new Stack<Integer>();
      dfs(root, sum, path, res);
      return res;
  }
  
  public void dfs(TreeNode root, int sum, Stack<Integer> path, List<List<Integer>> res) {
      path.push(root.val);
      if(root.left ==null && root.right ==null) {
          if(root.val==sum) res.add(new ArrayList<>(path));
      }
      if(root.left != null) {
          dfs(root.left, sum-root.val, path, res);
          
      }
      if(root.right != null) {
          dfs(root.right, sum-root.val, path, res);
      }
      path.pop();
  }
}