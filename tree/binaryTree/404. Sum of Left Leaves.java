/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
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
class Solution {
  int res = 0;
  public int sumOfLeftLeaves(TreeNode root) {
      dfs(root, false);
      return res;
  }
  
  public void dfs(TreeNode root, boolean isLeft) {
      if(root ==null)  return;
      dfs(root.left, true);
      if(root.left ==null && root.right== null && isLeft) res+=root.val;
      dfs(root.right, false);
  }
}
