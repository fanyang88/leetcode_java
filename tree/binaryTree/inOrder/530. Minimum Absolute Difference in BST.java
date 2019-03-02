/*
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 minimum diff whould be the min between prev and current node
 */
class Solution {
  int diff = Integer.MAX_VALUE;
  TreeNode prev = null;
  public int getMinimumDifference(TreeNode root) {
      dfs(root);
      return diff;
  }
  
  public void dfs(TreeNode root) {
      if(root == null)  return;
      dfs(root.left);
      if(prev != null) {
          diff = Math.min(diff, Math.abs(prev.val - root.val));
      }
      prev = root;
      dfs(root.right);
  }
}
