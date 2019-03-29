/*
We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]
 
Explanation: 
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.

      1                  1
       \                  \
        0       --->       0
      /  \                  \ 
     0    1                  1

*//**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
  public TreeNode pruneTree(TreeNode root) {
      if(root==null)  return null;
      if(getSum(root) ==0) return null; // remove this subTree
      root.left = pruneTree(root.left);
      root.right = pruneTree(root.right);
      return root;
  }
  
  public int getSum(TreeNode root) {
      if(root == null) return 0;
      return root.val+ getSum(root.left) + getSum(root.right);
  }
}