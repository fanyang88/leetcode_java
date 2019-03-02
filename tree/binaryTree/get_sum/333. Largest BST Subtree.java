/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 first we need to verify if it is a BST, if it is, we can get the total number, otherwise, we go to left and right to check if it is a BST...
 */
class Solution {
  public int largestBSTSubtree(TreeNode root) {
      if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
          return getTotal(root);
      }
      return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
  }
  
  public boolean isBST(TreeNode root, int minV, int maxV) {
      if(root == null)  return true;
      if(root.val <= minV || root.val >= maxV) return false;
      return isBST(root.left, minV, root.val) && isBST(root.right, root.val, maxV);
  }
  
  public int getTotal(TreeNode root) {
      if(root ==null)  return 0;
      return 1 + getTotal(root.left) + getTotal(root.right);
  }
}


