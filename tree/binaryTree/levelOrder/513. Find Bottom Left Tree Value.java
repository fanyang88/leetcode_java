/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
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
  public int findBottomLeftValue(TreeNode root) {
      int res = -1;
      
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll();
              if(i==0) res = cur.val;
              if(cur.left!=null) q.offer(cur.left);
              if(cur.right!=null) q.offer(cur.right); 
          }
      }
      return res;
  }
}
