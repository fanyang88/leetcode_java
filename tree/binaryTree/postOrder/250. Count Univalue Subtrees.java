/**
 Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
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
  int count=0;
  public int countUnivalSubtrees(TreeNode root) {
      dfs(root);
      return count;
  }
  
  public boolean dfs(TreeNode root) {
      if(root == null)  return true;
      boolean left = dfs(root.left);
      boolean right = dfs(root.right);
      if(left && right) {  // the left subtree and right subtree have uni value, now compare they with the root
          if(root.left != null && root.left.val != root.val)  return false;
          if(root.right != null && root.right.val != root.val)  return false;
          count++;
          return true;
      }
      return false; // not a full tree
  }
}
