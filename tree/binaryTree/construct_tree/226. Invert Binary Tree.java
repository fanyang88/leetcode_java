/**
 * 
 Invert a binary tree.

Example:

Input:

     4                4
   /   \            /  \
  2     7     ->   7     2
 / \   / \        /  \  / \ 
1   3 6   9      9   6 3   1
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
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
  public TreeNode invertTree(TreeNode root) {
      if(root==null)  return root;
      TreeNode newRoot = new TreeNode(root.val);
      newRoot.left = invertTree(root.right);
      newRoot.right = invertTree(root.left);
      return newRoot;
  }
}

