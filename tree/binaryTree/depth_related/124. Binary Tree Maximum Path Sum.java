/**
 Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 Maximum Path Sum can be only the root, or root.left+root, or root.right+root or root.left+root+root.right
 */
class Solution {
  public int res = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
      maxSumSinglePath(root);
      return res;
  }
  
  public int maxSumSinglePath(TreeNode root) {
      if(root == null) return 0;
      // get the max sum from left branch
      int left = maxSumSinglePath(root.left);
      // get the max sum from right branch
      int right = maxSumSinglePath(root.right);
      
      int single = Math.max(root.val, Math.max(left, right) + root.val);
      res = Math.max(res, Math.max(single, left+root.val+right));
      return single;
  }
}
