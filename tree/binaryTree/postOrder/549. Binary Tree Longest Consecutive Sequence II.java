/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
 

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 

Note: All the values of tree nodes are in the range of [-1e7, 1e7].
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 post order, 
 how we define the dec and inc: 
 from root as start, if root.val > root.left.val . it is dec, 
                     if root.val < root.left.val it is inc
                     if root.val > root.right.val  it is dec, 
                     if root.val < root.right.val it is inc
 we store the [inc, dec] from left branch and right branch
 at root, using above rules to modify inc, dec to get the max inc, dec
 result is inc+dec-1
 */
class Solution {
  int maxV=0;
  public int longestConsecutive(TreeNode root) {
      dfs(root);
      return maxV;
  }
  
  public int[] dfs(TreeNode root) {
      if(root ==null)  return new int[2];
      int[] left = dfs(root.left);
      int[] right = dfs(root.right);
      
      int inc =1, dec=1;
      if(root.left != null) {
          if(root.val == root.left.val +1)  {
              dec = left[1] +1;
          } else if(root.val == root.left.val -1) {
              inc = left[0]+1;
          }
      }
      if(root.right != null) {
           // if it is   2<-3<-4(root)->3  we should get the max increasing, same for decreasing.
          if(root.val == root.right.val +1)  {
              dec = Math.max(right[1]+1, dec);
          } else if(root.val == root.right.val -1) {
              inc = Math.max(right[0] +1, inc);
          }
      }
      // if the left increasing is 2->3 right decreasing is 1->2, path =2+2-1 = 3
      maxV = Math.max(maxV, inc+dec-1);
      return new int[]{inc, dec};
  }  
}
